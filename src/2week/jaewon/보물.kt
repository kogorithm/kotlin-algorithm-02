package `2week`.jaewon

fun main(){
    var answer = 0
    val n = readln().toInt()
    val arrA = readln().split(" ").map { it.toInt() }.sorted()
    val arrB = readln().split(" ").map { it.toInt() }.sortedDescending()

    answer = arrA.foldIndexed(0) { index, total , i ->  total + (i*arrB[index]) }

    println(answer)
}