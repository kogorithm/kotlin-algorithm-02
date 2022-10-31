package `5week`.byeonghee

/**
 * 1. array + slice -> list + subList : -20ms
 * 2. if (all 1) else if (all 0) else -> when(all 1) : -10ms
 * 3. subList * 2 + all -> for(r)for(c)if(1) : -40ms
 * 4. list+ for -> array + for : -20ms
 */

import java.io.*

class  `소병희_쿼드트리` {
    companion object {
        data class Pos(val r: Int, val c: Int) {
            operator fun times(int: Int) : Pos {
                return Pos(r * int, c * int)
            }

            operator fun plus(pos: Pos) : Pos {
                return Pos(r + pos.r, c + pos.c)
            }
        }

        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))

        val quad = listOf(
            Pos(0, 0),
            Pos(0, 1),
            Pos(1, 0),
            Pos(1, 1)
        )

        lateinit var screen : Array<CharArray>

        fun solve() {
            val n = br.readLine().toInt()
            screen = Array(n) { br.readLine().toCharArray() }

            dfsQuadTree(Pos(0, 0), n)
            bw.flush()
        }

        fun dfsQuadTree(p: Pos, size: Int) {
            var ones = 0
            for(r in p.r until p.r + size) for(c in p.c until p.c + size) {
                if (screen[r][c] == '1') ones++
            }

            when(ones) {
                size * size -> bw.append('1')
                0 -> bw.append('0')
                else -> {
                    bw.append('(')
                    for(mv in quad) {
                        dfsQuadTree(p + (mv * (size / 2)), size / 2)
                    }
                    bw.append(')')
                }
            }
        }
    }
}

fun main() {
    `소병희_쿼드트리`.solve()
}