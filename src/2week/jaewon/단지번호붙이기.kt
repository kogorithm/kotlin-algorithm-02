
// 프로그래머스 문제를 풀 때 아래처럼 코드를 짜게되면 같은 이름의 클래스가 존재할 수 있습니다!!

class `단지번호붙이기` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        var count = 0
        val answer = mutableListOf<Int>()
        lateinit var map : Array<IntArray>
        val dx = intArrayOf(0,1,0,-1)
        val dy = intArrayOf(1,0,-1,0)

        fun solution() {
            val n = readln().toInt()
            map = Array(n){ IntArray(n)}
            repeat(n){i ->
                readln().forEachIndexed { j, num ->
                    map[i][j] = num.digitToInt()
                }
            }

            for (i in 0 until n)for(j in 0 until n){
                if (map[i][j] == 1){
                    answer.add(findGroup(i,j))
                    count++
                }
            }

            println(count)
            answer.sorted().forEach { println(it) }
        }

        fun findGroup(x : Int, y : Int) : Int{
            var home = 1
            val queue = mutableListOf<Pair<Int,Int>>()
            queue.add(Pair(x,y))
            map[x][y] = 0

            while (queue.isNotEmpty()){
                repeat(queue.size){
                    val front = queue.removeFirst()
                    repeat(4){time ->
                        val nx = front.first + dx[time]
                        val ny = front.second + dy[time]
                        if(nx in map.indices && ny in 0 until map[nx].size){
                            if (map[nx][ny] == 1){
                                map[nx][ny] = 0
                                queue.add(Pair(nx,ny))
                                home++
                            }
                        }
                    }
                }
            }
            return home
        }
    }
}

fun main() {
   `단지번호붙이기`.getSolution().solution()
}