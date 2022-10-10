import java.util.LinkedList

class 그림 {

    val q = LinkedList<Pair<Int, Int>>()
    lateinit var size: List<Int>
    lateinit var map: Array<Array<Int>>
    lateinit var visit: Array<Array<Boolean>>

    val direction = listOf<Pair<Int, Int>>(Pair(0, 1), Pair(1, 0), Pair(-1, 0), Pair(0, -1))

    fun bfs(): Int {
        var count = 0

        while (!q.isEmpty()) {
            val (y, x) = q.remove()
            count++
            map[y][x] = 0

            for (i in 0 until 4) {
                val ny = y + direction[i].first
                val nx = x + direction[i].second

                if (ny < 0 || ny > size[0] - 1 || nx > size[1] - 1 || nx < 0) {
                    continue
                }
                if (map[ny][nx] == 0 || visit[ny][nx]) {
                    continue
                }

                q.add(Pair(ny, nx))
                visit[ny][nx] = true
            }
        }

        return count

    }


    fun solution() {
        val arr = arrayListOf<Int>()
        size = readln().split(" ").map { it.toInt() }
        visit = Array(size[0]) { Array(size[1]) { false } }
        map = Array(size[0]) {
            readln().split(" ").map { it.toInt() }.toTypedArray()
        }

        for (i in 0 until size[0]) {
            for (j in 0 until size[1]) {
                if (map[i][j] != 0) {
                    q.add(Pair(i, j))
                    visit[i][j] = true
                    arr.add(bfs())
                }
            }
        }


        val numOfPaint = arr.size
        println(numOfPaint)
        if (numOfPaint == 0) {
            println(0)
        } else {
            arr.sort()
            println(arr.last())
        }

    }
}


fun main() {
    val sol = 그림()
    sol.solution()
}
