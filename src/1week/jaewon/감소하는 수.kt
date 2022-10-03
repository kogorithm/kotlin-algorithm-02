/**
 * 최대 감소하는 수 : 9876543210
 * 최대 개수 : 1023
 */

fun main(){
    val n = readln().toInt()
    val list = ArrayList<Long>()
    var answer = 0

    fun findDecrease(index : Int, t : Long, result : Long){
        var num = result
        for (i in index until 10){
            num += i*t
            list.add(result)
            findDecrease(i+1, t*10, result)
            num -= i*t
        }
    }

    answer = if(n <= 10){ n }
    else if( n >= 1023 ){ -1 }
    else {
        findDecrease(0,1,0)
        list.sort()
        list[n].toInt()
    }

    println(answer)
}