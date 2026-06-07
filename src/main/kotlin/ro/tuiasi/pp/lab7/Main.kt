package ro.tuiasi.pp.lab7

import java.nio.file.Path

fun main() {
    val path = Path.of("history.log")
    if (!path.toFile().exists()) {
        println("Fisierul history.log nu exista in directorul proiectului.")
        println("Copiaza-l local conform instructiunilor din ASSIGNMENT.md.")
        return
    }

    val records = HistoryParser.parseLastEntries(path, count = 50)

    val recordsMap = HistoryParser.toMutableMap(records)

    println("Numar de inregistrari parsate: ${records.size}")
    println("Numar de elemente salvate in map: ${recordsMap.size}")

    if (records.size >= 2) {
        val mostRecent = GenericOps.maxOfTwo(records[0], records[1])
        println("\nCea mai recenta dintre primele doua inregistrari este:")
        println("Timestamp: ${mostRecent.timestamp} | Comanda: ${mostRecent.commandLine}")
    }

    if (records.isNotEmpty()) {
        // Preluam o valoare la intamplare si incercam sa o gasim si sa o modificam cu alta direct din map
        val target = records[0]
        val replacement = target.copy(commandLine = target.commandLine + " [INLOCUIT CU SUCCES]")

        val count = GenericOps.searchAndReplace(target, replacement, recordsMap)
        println("\nAu fost inlocuite $count valori cu succes folosind proiecția generică `out`.")
    }
}