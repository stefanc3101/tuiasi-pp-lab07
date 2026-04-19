package ro.tuiasi.pp.lab7

// TODO: Definește clasa HistoryLogRecord cu câmpurile timestamp (Long) și commandLine (String).
//       Clasa trebuie să implementeze interfața Comparable<HistoryLogRecord>.
//       Metoda compareTo trebuie să compare obiectele după timestamp.
data class HistoryLogRecord(
    val timestamp: Long,
    val commandLine: String,
) : Comparable<HistoryLogRecord> {
    override fun compareTo(other: HistoryLogRecord): Int = TODO("De implementat: compară după timestamp")
}
