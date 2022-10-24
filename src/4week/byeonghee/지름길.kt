package `4week`.byeonghee

import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))

var answer = Int.MAX_VALUE
var dist = 0
var D = 0
var N = 0

data class Edge(val s: Int, val e: Int, val d: Int)
val edgeMap = mutableMapOf<Int, MutableList<Edge>>()

fun main() {
    br.readLine().split(" ").map{ it.toInt() }.let {
        N = it[0]
        D = it[1]
    }

    repeat(N) {
        val (s, e, d) = br.readLine().split(" ").map{ it.toInt() }
        if ((e <= D) && (d < (e - s))) {
            if (edgeMap.contains(s)) {
                edgeMap[s]!!.add(Edge(s, e, d))
            }
            else {
                edgeMap[s] = mutableListOf(Edge(s, e, d))
            }
        }
    }

    dfs(0)
    println(answer)
}

fun dfs(node: Int) {
    println(node)

    var nxt = node
    var mv = 0
    while(edgeMap.containsKey(nxt).not() && nxt < D) {
        nxt++
        mv++
    }
    if (nxt == D) {
        answer = Integer.min(answer, dist + mv)
        return
    }
    println(nxt)
    for(i in edgeMap[nxt]!!) {
        dist += i.d + mv
        dfs(i.e)
        dist -= i.d - mv
    }
}