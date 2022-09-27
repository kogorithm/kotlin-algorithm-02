/**
 *  사용할 알고리즘 : BFS
 *  변수
 *  - graph : 현수막 정보 <MutableList<List<Int>>
 *  - visited : 방문 정보 <MutableList<List<Bolean>>
 *  - count : 반환할 글자 수 <answer>
 *  함수
 *  - bfs(시작인덱스 <Pair()> , visited , graph ) : 방문 정보 반환
 *
 *  1. graph에서 1인 부분 visited에서 전부 false 로 변경
 *  2. visited가 전부 true일 때까지 반복
 *  3. visited에서 true인 인덱스 bfs에 전달하며 반복
 *  4. 함수를 호출한 횟수 = 글자 수
 */


val graph : MutableList<List<Int>> = mutableListOf()

fun main(){
    val (n,m) = readln().split(" ").map { it.toInt() }
    var visited = MutableList(n){ MutableList(m){true} }
    var count = 0
    repeat(n){ x->
        val list = readln().split(" ").map { it.toInt() }
        list.forEachIndexed { y , it ->
            if(it == 1){ visited[x][y] = false}
        }
        graph.add(list)
    }



    while (true){
        if (visited.count { it.all { it} } == n){
            break
        }

        var x = 0
        var y = 0
        run loop@{
            visited.forEachIndexed { xx, it ->
                y = it.indexOf(false)
                if (y != -1){
                    x = xx
                    return@loop
                }
            }
        }

        visited = bfs(Pair(x,y),visited)
        count++
    }

    println(count)
}

fun bfs(start : Pair<Int,Int>,visited : MutableList<MutableList<Boolean>>)
: MutableList<MutableList<Boolean>> {
    val queue = mutableListOf<Pair<Int, Int>>()
    val dx = intArrayOf(-1, 0, 1, 0, -1, -1, 1, 1)
    val dy = intArrayOf(0, -1, 0, 1, -1, 1, -1, 1)

    queue.add(start) // 초기 정점 큐에 추가
    visited[start.first][start.second] = true

    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val front = queue.removeLast()
            val x = front.first
            val y = front.second
            repeat(8) { time ->
                val nx = x + dx[time]
                val ny = y + dy[time]
                if (nx in graph.indices && ny in 0 until graph[0].size && graph[nx][ny] == 1) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true
                        queue.add(Pair(nx, ny))
                    }
                }
            }
        }
    }

    return visited
}