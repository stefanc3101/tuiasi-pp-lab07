package ro.tuiasi.pp.lab7

import java.nio.file.Path

fun main() {
    val path = Path.of("history.log")
    if (!path.toFile().exists()) {
        println("Fisierul history.log nu exista in directorul proiectului.")
        println("Copiaza-l local conform instructiunilor din ASSIGNMENT.md.")
        return
    }

    // TODO: Parsează ultimele 50 de intrări din fișier folosind HistoryParser.parseLastEntries()

    // TODO: Construiește HashMap-ul timestamp -> HistoryLogRecord folosind HistoryParser.toMutableMap()

    // TODO: Afișează numărul de intrări parsate și numărul de elemente din map

    // TODO: Dacă există cel puțin două înregistrări, folosește GenericOps.maxOfTwo()
    //       pentru a determina cea mai recentă dintre primele două și afișeaz-o

    // TODO (opțional): Testează GenericOps.searchAndReplace() pe map-ul creat
}
