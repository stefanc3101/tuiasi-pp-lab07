package ro.tuiasi.pp.lab7

import java.nio.file.Path
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.io.path.readLines

object HistoryParser {
    // Formatul datei din fișierul history.log: "yyyy-MM-dd  HH:mm:ss" (două spații între dată și oră)
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss")

    fun parseLastEntries(path: Path, count: Int = 50): List<HistoryLogRecord> {
        // Citim toate liniile si delegam impartirea metodei splitBlocks.
        val lines = path.readLines()
        val blocks = splitBlocks(lines)

        // Luam doar ultimele blocuri si le mapam. Functia mapNotNull sterge automat
        // eventualele valori null (blocurile incomplete care pica parsarea).
        return blocks.takeLast(count).mapNotNull { parseBlock(it) }
    }

    fun toMutableMap(records: List<HistoryLogRecord>): HashMap<Long, HistoryLogRecord> {
        // associateBy genereaza automat un dictionar luand timestamp-ul ca si cheie
        return HashMap(records.associateBy { it.timestamp })
    }

    internal fun toTimestamp(startDate: String): Long {
        // Parsam data folosind tiparul prestabilit, ii asignam fusul orar local, apoi convertim in milisecunde
        val localDateTime = LocalDateTime.parse(startDate, formatter)
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    private fun splitBlocks(lines: List<String>): List<List<String>> {
        val blocks = mutableListOf<List<String>>()
        var currentBlock = mutableListOf<String>()

        for (line in lines) {
            // Ignoram complet liniile goale sau spatiile albe
            if (line.isBlank()) continue

            if (line.startsWith("Start-Date:")) {
                // Cand dam de o noua data de start, salvam blocul anterior (daca exista) si o luam de la capat
                if (currentBlock.isNotEmpty()) {
                    blocks.add(currentBlock)
                    currentBlock = mutableListOf()
                }
            }
            currentBlock.add(line)
        }

        // Nu uitam sa salvam si ultimul bloc in care se afla executia cand iese din for
        if (currentBlock.isNotEmpty()) {
            blocks.add(currentBlock)
        }

        return blocks
    }

    private fun parseBlock(block: List<String>): HistoryLogRecord? {
        var startDateStr: String? = null
        var commandLineStr: String? = null

        for (line in block) {
            if (line.startsWith("Start-Date:")) {
                // Eliminam cuvantul "Start-Date:" si curatam restul de spatii
                startDateStr = line.removePrefix("Start-Date:").trim()
            } else if (line.startsWith("Commandline:")) {
                commandLineStr = line.removePrefix("Commandline:").trim()
            }
        }

        // Conditia principala de fail: un bloc valid de apt-history are ambele atribute
        if (startDateStr == null || commandLineStr == null) {
            return null
        }

        return HistoryLogRecord(toTimestamp(startDateStr), commandLineStr)
    }
}