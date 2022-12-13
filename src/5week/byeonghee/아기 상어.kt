package `5week`.byeonghee

import java.io.*
import java.util.PriorityQueue
import kotlin.system.exitProcess

class `소병희_아기 상어` {
    companion object {
        data class Pos(val r: Int, val c: Int) {
            operator fun plus(pos: Pos) : Pos {
                return Pos(r + pos.r, c + pos.c)
            }

            fun inSpace(x: Int) : Boolean {
                return r in 0 until x && c in 0 until x
            }
        }

        val mv = listOf(
            Pos(-1, 0),
            Pos(0, -1),
            Pos(0, 1),
            Pos(1, 0)
        )

        val br = BufferedReader(InputStreamReader(System.`in`))

        var n = 0

        lateinit var space: Array<IntArray>
        lateinit var visited: Array<BooleanArray>

        val pq = PriorityQueue(Comparator<Pair<Pos, Int>> { a, b ->
            if (a.second == b.second) {
                if (a.first.r == b.first.r) a.first.c - b.first.c
                else a.first.r - b.first.r
            }
            else a.second - b.second
        })

        var shark = Pos(0, 0)
        var size = 2
        var eat = 0
        var mom = true
        var answer = 0

        fun solve() {
            n = br.readLine().toInt()
            space = Array(n) {  r ->
                br.readLine().split(" ").mapIndexed{ c, it ->
                    it.toInt().also { if (it == 9) shark = Pos(r, c) }
                }.toIntArray()
            }
            space[shark.r][shark.c] = 0

            while(true) {
                pq.clear()
                pq.add(Pair(shark, 0))
                visited = Array(n) { BooleanArray(n) }
                mom = true

                while(pq.isNotEmpty()) {
                    val (pos, dist) = pq.poll()
                    if (pos.inSpace(n).not()) continue
                    if (visited[pos.r][pos.c]) continue
                    visited[pos.r][pos.c] = true

                    if (space[pos.r][pos.c] > size) continue

                    if (space[pos.r][pos.c] in listOf(0, size) ) {
                        for (d in mv) {
                            pq.add(Pair(pos + d, dist + 1))
                        }
                    }
                    else  {
                        answer += dist
                        if ((++eat) == size) {
                            eat = 0
                            size++
                        }
                        space[pos.r][pos.c] = 0
                        shark = pos.copy()
                        mom = false
                        break
                    }
                }
                if (mom) {
                    println(answer)
                    exitProcess((0))
                }
            }
        }
    }
}

fun main() {
    `소병희_아기 상어`.solve()
}