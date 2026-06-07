package ro.tuiasi.pp.lab7

object GenericOps {
    fun <T : Comparable<T>> maxOfTwo(first: T, second: T): T {
        // Folosim compareTo (care este apelat implicit de operatorii >, <, >=) pentru a gasi maximul
        return if (first >= second) first else second
    }

    @Suppress("UNCHECKED_CAST")
    fun <K, V : Any> searchAndReplace(
        target: V,
        replacement: V,
        map: MutableMap<K, out V>,
    ): Int {
        var count = 0

        // Deoarece map-ul are tipul 'out V' (covarianta), compilatorul ne interzice din motive de siguranta
        // sa modificam valorile in mod direct (deoarece nu garanteaza ca introducem un subtip valid).
        // Pentru a putea suprascrie cu replacement, facem un cast la MutableMap<K, V>.
        val writableMap = map as MutableMap<K, V>

        for ((key, value) in writableMap.entries) {
            if (value == target) {
                writableMap[key] = replacement
                count++
            }
        }

        return count
    }
}