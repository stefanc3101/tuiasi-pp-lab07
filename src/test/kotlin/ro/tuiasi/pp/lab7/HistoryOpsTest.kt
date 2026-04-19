package ro.tuiasi.pp.lab7

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HistoryOpsTest {
    @Test
    fun maxOfTwoReturnsRecordWithNewestTimestamp() {
        val older = HistoryLogRecord(timestamp = 1_000L, commandLine = "apt install git")
        val newer = HistoryLogRecord(timestamp = 2_000L, commandLine = "apt upgrade")

        val max = GenericOps.maxOfTwo(older, newer)

        assertEquals(newer, max)
    }

    @Test
    fun searchAndReplaceReplacesMatchingValues() {
        val a = HistoryLogRecord(1_000L, "cmd-a")
        val b = HistoryLogRecord(2_000L, "cmd-b")
        val c = HistoryLogRecord(3_000L, "cmd-c")
        val map = mutableMapOf(
            1L to a,
            2L to b,
            3L to b,
        )

        val replaced = GenericOps.searchAndReplace(b, c, map)

        assertEquals(2, replaced)
        assertTrue(map.values.contains(c))
    }

    @Test
    fun toTimestampParsesExpectedFormat() {
        val ts = HistoryParser.toTimestamp("2026-03-31  10:11:12")
        assertTrue(ts > 0)
    }
}
