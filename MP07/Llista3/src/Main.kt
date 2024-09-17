const val N = 5
const val M = 5

fun main() {
    EX16(listOf(1,2,3,4,5,6,7,8,9,10), listOf(2,5,6,8,9,11))
}

fun ListPrinting(list: List<Int>) {
    list.forEach(::println)
}

fun MatrixPrinting(list: List<List<Int>>){
    for(row in list){
        for(col in row){
            print("$col ")
        }
        println()
    }
}

fun FillMatrix2(){
    var matrix = MutableList(N) { MutableList(M) { 0 } }
    for (i in matrix.indices)
        for (j in matrix[i].indices)
            matrix[i][j] = i+1

    MatrixPrinting(matrix)
}

fun FillMatrix3(){
    var matrix = MutableList(N) { MutableList(M) { 0 } }
    for (i in matrix.indices)
        for (j in matrix[i].indices)
            matrix[i][j] = j+1

    MatrixPrinting(matrix)
}
fun FillMatrix4(){
    var matrix = MutableList(N) { MutableList(M) { 0 } }
    var counter = 1
    for (i in matrix.indices)
        for (j in matrix[i].indices) {
            matrix[i][j] = counter
            counter++
        }

    MatrixPrinting(matrix)
}

fun FillMatrix5(){
    var matrix = MutableList(N) { MutableList(M) { 0 } }
    var counter = N*M
    for (i in matrix.indices)
        for (j in matrix[i].indices) {
            matrix[i][j] = counter
            counter--
        }

    MatrixPrinting(matrix)
}

fun FillMatrix6(){
    var matrix = MutableList(N) { MutableList(M) { 0 } }
    for (i in matrix.indices)
        for (j in matrix[i].indices) {
            matrix[i][j] = if(i==j) 1 else 0
        }

    MatrixPrinting(matrix)
}

fun EX15(list1: List<Int>, list2 : List<Int>){
    var cursor1 = 0
    var cursor2 = 0
    var listOfCommon : MutableList<Int> = mutableListOf()
    while(cursor1 < list1.size && cursor2 < list2.size){
        if(list1[cursor1] == list2[cursor2]){
            listOfCommon.add(list1[cursor1])
            cursor1++
            cursor2++
        }
        else if(list1[cursor1] < list2[cursor2]){
            cursor1++
        }
        else
            cursor2++
    }
    ListPrinting(listOfCommon)
}
fun EX16(list1: List<Int>, list2 : List<Int>){
    var cursor1 = 0
    var cursor2 = 0
    var listOfDifferent : MutableList<Int> = mutableListOf()
    while(cursor1 < list1.size && cursor2 < list2.size){
        if(list1[cursor1] == list2[cursor2]){
            cursor1++
            cursor2++
        }
        else if(list1[cursor1] < list2[cursor2]){
            listOfDifferent.add(list1[cursor1])
            cursor1++
        }
        else{
            cursor2++
            listOfDifferent.add(list1[cursor1])
        }
    }
    while(cursor1 < list1.size){
        listOfDifferent.add(list1[cursor1])
        cursor1++
    }
    while (cursor2 < list2.size){
        listOfDifferent.add(list2[cursor2])
        cursor2++
    }
    ListPrinting(listOfDifferent)
}
