/**
 *  컨테이너 벨트를 일자로 풀어서 생각하기. 컨테이너의 길이 = (3B-2)
 *
 *  컨테이너 벨트 표현 : contatiner : MutableList<Int> (3b-2)
 *  현재 상태 업데이트 를 위한 배열 : dxdy : List<Pair(Int,Int)>
 *
 *  (반복문) 1초마다 컨테이너 변경
 *      1-1. 컨테이너에 뒤에다가 선물 추가
 *      2-1. 직원을 순회
 *
 */


fun main(){
    val (b,n,m) = readln().split(" ").map { it.toInt() }
    val employee = mutableListOf<List<Int>>()
    val com = MutableList(b){MutableList(b){false}}
    val dxdy = listOf(Pair(0,1),Pair(1,0),Pair(-1,0))
    repeat(n){ employee.add(readln().split(" ").map { it.toInt() })}

    var x = 0
    var y = 0
    var i = 0
    while (true){

    }
}