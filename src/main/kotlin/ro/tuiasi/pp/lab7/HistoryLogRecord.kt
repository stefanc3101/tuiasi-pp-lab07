package ro.tuiasi.pp.lab7

data class HistoryLogRecord(
    val timestamp: Long,
    val commandLine: String,
) : Comparable<HistoryLogRecord> {
    override fun compareTo(other: HistoryLogRecord): Int {
        // Functia compareTo standard de pe tipul de date primitiv Long isi face treaba perfect
        return this.timestamp.compareTo(other.timestamp)
    }
}