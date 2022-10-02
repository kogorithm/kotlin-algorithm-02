package `1week`.byeonghee


/**
 * @접근방법  그래프
 * @시간복잡도  N^2 + a
 * @후기  dfs가 나았을 것 같다.
 */


import java.io.*

const val INF = Int.MAX_VALUE

var answer = 0

val dr = listOf(-1, -1, -1, 0)
val dc = listOf(-1, 0, 1, -1)

data class Pos(val r: Int, val c: Int)
data class Pixel(val v: Int, var parent: Int = INF)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (M, N) = br.readLine().trim().split(" ").map{ it.toInt() }
    val parents = mutableListOf<MutableList<Pos>>()
    val banner = MutableList<MutableList<Pixel>>(M){ mutableListOf() }

    repeat(M) { i ->
        banner[i].add(Pixel(0))
        br.readLine().trim().split(" ").forEach {
            banner[i].add(Pixel(it.toInt()))
        }
        banner[i].add(Pixel(0))
    }
    banner.add(0, MutableList(N + 2){Pixel(0)})
    banner.add(MutableList(N + 2){Pixel(0)})

    for(i in 1..M) for(j in 1..N) {
        if (banner[i][j].v == 1) {
            val localParents = mutableSetOf<Int>()
            for(d in 0..3) {
                if (banner[i+dr[d]][j+dc[d]].v == 1) {
                    localParents.add(banner[i+dr[d]][j+dc[d]].parent)
                }
            }
            if (localParents.isEmpty()) {
                banner[i][j].parent = parents.size
                parents.add(mutableListOf(Pos(i, j)))
            }
            else {
                val minParent = localParents.toList().minOf{ it }
                banner[i][j].parent = minParent
                parents[minParent].add(Pos(i, j))
                localParents.remove(minParent)
                localParents.forEach { par ->
                    parents[par].forEach {
                        banner[it.r][it.c].parent = minParent
                    }
                    parents[minParent].addAll(parents[par])
                    parents[par].clear()
                }
            }
        }
    }

    parents.removeAll{ it.isEmpty() }
    println(parents.size)
}