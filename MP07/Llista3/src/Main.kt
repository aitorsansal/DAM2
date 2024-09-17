fun main() {
    ListPrinting(listOf(1,5,8,6,3,41,8))
}

fun ListPrinting(list: List<Int>) {
    list.forEach(::println)
}