/**
 * 어떻게 풀어야 할지 감이 안잡힘...
 * ㅇㅏ이디어 : 이분탐색
 *
 */

fun main(){
    val (k,n) = readln().split(" ").map { it.toInt() }
    val size = IntArray(k)
    repeat(k){ size[it] = readln().toInt()}

    var answer : Long = 0
    var high : Long = size.maxOf { it }+1.toLong()
    var row : Long = 1
    while (row <= high){
        val mid : Long = (high+row)/2
        var temp : Long = 0
        size.forEach {
           temp += it/mid
        }
        if (temp >= n){
            row = mid+1
            answer = mid
        }else{
            high = mid-1
        }
    }
    println(answer)
}