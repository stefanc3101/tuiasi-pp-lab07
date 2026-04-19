package ro.tuiasi.pp.lab7

import java.nio.file.Path
import java.time.format.DateTimeFormatter

object HistoryParser {
    // Formatul datei din fișierul history.log: "yyyy-MM-dd  HH:mm:ss" (două spații între dată și oră)
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss")

    // TODO: Implementează funcția care citește fișierul de la `path`, împarte conținutul în blocuri
    //       (fiecare bloc începe cu "Start-Date:"), ia ultimele `count` blocuri și returnează
    //       o listă de HistoryLogRecord parsate.
    fun parseLastEntries(path: Path, count: Int = 50): List<HistoryLogRecord> = TODO("De implementat")

    // TODO: Implementează funcția care transformă o listă de HistoryLogRecord într-un
    //       HashMap<Long, HistoryLogRecord> unde cheia este timestamp-ul fiecărui record.
    fun toMutableMap(records: List<HistoryLogRecord>): HashMap<Long, HistoryLogRecord> = TODO("De implementat")

    // TODO: Implementează funcția care primește data ca String (ex: "2026-03-31  10:11:12")
    //       și o convertește în milisecunde (epoch milli) folosind `formatter` și ZoneId.systemDefault().
    internal fun toTimestamp(startDate: String): Long = TODO("De implementat")

    // TODO: Implementează funcția care primește liniile fișierului și le împarte în blocuri.
    //       Un nou bloc începe la fiecare linie care începe cu "Start-Date:".
    //       Liniile goale se ignoră.
    private fun splitBlocks(lines: List<String>): List<List<String>> = TODO("De implementat")

    // TODO: Implementează funcția care primește un bloc (listă de linii) și extrage
    //       linia "Start-Date:" și "Commandline:". Dacă una dintre ele lipsește, returnează null.
    private fun parseBlock(block: List<String>): HistoryLogRecord? = TODO("De implementat")
}
