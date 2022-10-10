package `1week`.jaewon

import kotlin.math.max
import kotlin.system.exitProcess

fun main(){
    var answer = 0
    val (n,d,k,c) = readln().split(" ").map { it.toInt() }
    val sushi = mutableListOf<Int>()
    repeat(n){ sushi.add(readln().toInt()) }

    for (i in 0..n){
        if (answer == k+1){
            println(answer)
            exitProcess(0)
        }
        var temp = 0
        val eat = sushi.take(k)
        if (!eat.contains(c)){
            temp ++
        }
        temp += eat.distinct().count()
        answer = max(temp,answer)
        sushi.add(sushi.removeFirst())
    }

    println(answer)
}