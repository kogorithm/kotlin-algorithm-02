import java.lang.StringBuilder
import java.util.LinkedList

fun main(){
    val n = readln().toInt()
    val tree  = Array(n+1){ LinkedList<Int>()}
    val visited = BooleanArray(n+1){ false }
    val parents = IntArray(n+1){-1}
    //인접리스트 만들기
    repeat(n-1){
        val (a,b) = readln().split(" ").map { it.toInt() }
        tree[a].add(b)
        tree[b].add(a)
    }

    fun dfs(n: Int){
        for(num in tree[n]){
            if(!visited[num]) {
                visited[num] = true
                parents[num] = n
                dfs(num)
            }
        }
    }

    dfs(1)
    val answer = StringBuilder()
    for (i in 2..n){
        answer.append("${parents[i]}\n")
    }
    println(answer.toString())
}