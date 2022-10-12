
import kotlin.math.max

data class HOMEWORK (val d : Int, val w : Int)
var answer = 0

fun main(){
    val n = readln().toInt()
    var maxDay = 0
    val work : MutableList<HOMEWORK> = mutableListOf()
    repeat(n){
        val list = readln().split(" ").map { it.toInt() }
        work.add(HOMEWORK(list[0],list[1]))
        maxDay = max(maxDay,list[0])
    }

    for (i in maxDay downTo 1){
        var temp = HOMEWORK(0,0)

        work.filter { it.d >= i }.forEach {
            if(temp.w < it.w){
                temp = it
            }
        }
        answer += temp.w
        work.remove(temp)
    }

    println(answer)
}