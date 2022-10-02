import kotlin.math.abs
import kotlin.math.min


val ability : MutableList<List<Int>> = mutableListOf()
var min = 1000

fun main(){
    val n = readln().toInt()
    val temp = MutableList(n){0}
    val answer = mutableListOf<List<Int>>()
    var visited = Array<Boolean>(n, {false});
    repeat(n){x ->
        temp[x] = x+1
        val list = readln().split(" ").map { it.toInt() }
        ability.add(list)
    }

    fun dfs(index : Int, depth : Int){
        if(depth == n/2){
            val team1 : MutableList<Int> = mutableListOf()
            val team2 : MutableList<Int> = mutableListOf()
            visited.forEachIndexed { index, it ->
                if(it) { team1.add(index+1)}
                else { team2.add(index+1)}
            }
            min = min(min, abs(getAbility(team1)-getAbility(team2)))
            if(min == 0) {
                println(min)
                System.exit(0);
            }
            return;
        }
        for (i in index until n){
            if(visited[i]) continue;
            visited[i] = true;
            dfs(i+1, depth + 1);
            visited[i] = false;
        }
    }

    dfs(0,0)
    println(min)
}


fun getAbility(list : List<Int>): Int{
    var sum = 0
    permutation(list).forEach {
        sum += ability[it[0]-1][it[1]-1]
    }
    return sum
}

fun <T> permutation(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el ): List<List<T>> {
    return if(sub.isEmpty()|| fin.size > 1) listOf(fin)
    else {
        sub.flatMap {
            permutation(el, fin + it, sub - it)
        }
    }
}