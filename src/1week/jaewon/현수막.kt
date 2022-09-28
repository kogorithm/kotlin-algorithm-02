/**
 *  사용할 알고리즘 : BFS
 *  변수
 *  - graph : 현수막 정보 <MutableList<List<Int>>
 *  - count : 반환할 글자 수 <answer>
 *  함수
 *  - bfs(시작인덱스 <Pair()>)
 *
 *  1. graph가 전부 0일 때까지 반복
 *  2. graph가 1인 좌표를 bfs에 전달하여 순회
 *  3. 함수를 호출한 횟수 = 글자 수
 */


val `1weekjaewon01graph` : MutableList<MutableList<Int>> = mutableListOf()
// 전역변수여서 변수명 겹칠까봐 안겹치도록 길게 만들어서 커밋찍었습니다..! 백준 코드 제출은 다르게 했어요!!

fun main(){
    val (n,m) = readln().split(" ").map { it.toInt() }
    var count = 0
    repeat(n){
        val list = readln().split(" ").map { it.toInt() }.toMutableList()
        `1weekjaewon01graph`.add(list)
    }

    run loop@{
        `1weekjaewon01graph`.forEachIndexed { x, list ->
            list.forEachIndexed { y, i ->
                if (`1weekjaewon01graph`.count {it.all { it == 0} } == n){
                    return@loop
                }
                if(`1weekjaewon01graph`[x][y] == 1){
                    bfs(Pair(x,y))
                    count++
                }
            }
        }
    }

    println(count)
}

fun bfs(start : Pair<Int,Int>) {
    val queue = mutableListOf<Pair<Int, Int>>()
    val dx = intArrayOf(-1, 0, 1, 0, -1, -1, 1, 1)
    val dy = intArrayOf(0, -1, 0, 1, -1, 1, -1, 1)

    queue.add(start) // 초기 정점 큐에 추가
    `1weekjaewon01graph`[start.first][start.second] = 0

    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val front = queue.removeLast()
            val x = front.first
            val y = front.second
            repeat(8) { time ->
                val nx = x + dx[time]
                val ny = y + dy[time]
                if (nx in `1weekjaewon01graph`.indices && ny in 0 until `1weekjaewon01graph`[0].size && `1weekjaewon01graph`[nx][ny] == 1) {
                    if (`1weekjaewon01graph`[nx][ny] != 0) {
                        `1weekjaewon01graph`[nx][ny] = 0
                        queue.add(Pair(nx, ny))
                    }
                }
            }
        }
    }
}