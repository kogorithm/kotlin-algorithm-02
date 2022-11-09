package `4week`.byeonghee

import java.io.*

class `트리의 부모 찾기` {
    val br = BufferedReader(InputStreamReader(System.`in`))

    lateinit var parents : IntArray
    val edges = mutableMapOf<Int, MutableList<Int>>()
    val q = ArrayDeque<Pair<Int, Int>>()

    fun solution() {
        val n = br.readLine().toInt()
        parents = IntArray(n + 1)

        repeat(n-1) { i ->
            br.readLine().split(" ").map{ it.toInt() }.run {
                edges.getOrPut(first()) { mutableListOf() }
                edges[first()]!!.add(last())
                edges.getOrPut(last()) { mutableListOf() }
                edges[last()]!!.add(first())
                if (first() == 1) q.add(Pair(first(), last()))
                else if (last() == 1) q.add(Pair(last(), first()))
            }
        }

        lateinit var cur: Pair<Int, Int>
        while(q.isNotEmpty()) {
            cur = q.removeFirst()
            parents[cur.second] = cur.first
            for(i in edges[cur.second]!!) {
                if (parents[i] == 0) q.add(Pair(cur.second, i))
            }
        }

        println(parents.drop(2).joinToString("\n"))
    }
}

fun main() {
    `트리의 부모 찾기`().solution()
}