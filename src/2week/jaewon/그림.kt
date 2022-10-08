import kotlin.math.max

/**
 *  사용할 알고리즘 : BFS
 *
 *  visited (grapg가 전부 0인지 확인하는 걸로 대체 가능 ) 함수 외부에 선언하기
 *  bfs 함수가 반환해 주어야 할 값 : 1의 갯수
 *  bfs 함수 호출 갯수 = 그림의 갯수(num)
 *  bfs 함수가 반환한 값의 최대값 = 가장 넓은 넓이(area)
 *
 *  1. 메모리 초과 -> List를 Array로 변경
 *  2. 시간 초과 -> forEach를 for로 변경
 */

lateinit var canvas : Array<IntArray>
val dx = intArrayOf(0,1,0,-1)
val dy = intArrayOf(1,0,-1,0)

fun main(){
    var num = 0
    var area = 0
    val (n,m) = readln().split(" ").map{ it.toInt()}
    canvas = Array(n){ IntArray(m) }
    repeat(n){i ->
        val list = readln().split(" ").map { it.toInt()}.toMutableList()
        list.forEachIndexed { j, n ->
            canvas[i][j] = n
        }
    }

    fun bfs(x: Int, y : Int) : Int {
        var count = 1
        val queue = mutableListOf<Pair<Int,Int>>()
        queue.add(Pair(x,y))
        canvas[x][y] = 0

        while (queue.isNotEmpty()){
            repeat(queue.size){
                val front = queue.removeLast()
                repeat(4){time ->
                    val nx = front.first+dx[time]
                    val ny = front.second+dy[time]
                    if(nx in canvas.indices && ny in 0 until canvas[nx].size ){
                        if(canvas[nx][ny] == 1){
                            canvas[nx][ny] = 0
                            queue.add(Pair(nx,ny))
                            count++
                        }
                    }

                }
            }
        }
        return count
    }

    for (i in 0 until n) for(j in 0 until m){
        if(canvas[i][j] == 1){
            area = max(area, bfs(i,j))
            num++
        }
    }

    println(num)
    println(area)
}
