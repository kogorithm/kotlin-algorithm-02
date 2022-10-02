import kotlin.math.abs
import kotlin.math.min


val ability : MutableList<List<Int>> = mutableListOf()

fun main(){
    var min = 1000
    val n = readln().toInt()
    val temp = MutableList(n){0}
    val answer = mutableListOf<List<Int>>()
    repeat(n){x ->
        temp[x] = x+1
        val list = readln().split(" ").map { it.toInt() }
        ability.add(list)
    }

    combination(answer,temp,Array<Boolean>(n){false},0,n/2)
    var i = 0
    var j = answer.lastIndex
    while (true){
        if (min == 0 || i > j ) break
        min = min(min, abs(getAbility(answer[i])-getAbility(answer[j])))
        i++
        j--
    }
    println(min)
}

fun getAbility(list : List<Int>): Int{
    var sum = 0
    permutation(list).forEach {
        sum += ability[it[0]-1][it[1]-1]
    }
    return sum
}

fun <T> combination(answer: MutableList<List<T>>, el: List<T>, ck: Array<Boolean>, start: Int, target: Int) {
    if(target == 0) {
        answer.addAll( listOf(el.filterIndexed { index, t -> ck[index] }) )
    } else {
        for(i in start until el.size) {
            ck[i] = true
            combination(answer, el, ck, i + 1, target - 1)
            ck[i] = false
        }
    }
}

fun <T> permutation(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el ): List<List<T>> {
    return if(sub.isEmpty()|| fin.size > 1) listOf(fin)
    else {
        sub.flatMap {
            permutation(el, fin + it, sub - it)
        }
    }
}