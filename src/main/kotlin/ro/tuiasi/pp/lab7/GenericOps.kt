package ro.tuiasi.pp.lab7

object GenericOps {
    // TODO: Implementează o funcție generică mărginită superior cu Comparable<T>
    //       care returnează maximul dintre două obiecte (cel cu timestamp-ul mai recent).
    fun <T : Comparable<T>> maxOfTwo(first: T, second: T): T = TODO("De implementat")

    // TODO: Implementează o funcție generică de căutare și înlocuire.
    //       Parcurge map-ul, înlocuiește toate valorile egale cu `target` cu `replacement`
    //       și returnează numărul de înlocuiri efectuate.
    //       Observație: folosește proiecție de tip `out` sau proiecție stea pentru parametrul map.
    fun <K, V : Any> searchAndReplace(
        target: V,
        replacement: V,
        map: MutableMap<K, out V>,
    ): Int = TODO("De implementat")
}
