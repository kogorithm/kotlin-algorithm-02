/**
 * 가로 : split 를 사용해서 값을 추가해주기
 * 세로 : 배열에 저장 후 for문을 사용하여 문자를 만들어서 값 추가해주시
 * 리턴 값 : 정렬해서 첫번째 인덱스
 */

fun main(){
    val (r,c) = readln().split(" ").map { it.toInt() }
    val list = mutableListOf<List<String>>()
    val answer = mutableListOf<String>()
    repeat(r){
        val input = readln()
        answer.addAll(input.split("#").filterNot { it.isEmpty() || it.length<2})
        val temp = input.chunked(1).map { it }
        list.add(temp)
    }

    var temp = ""
    for (i in 0 until c) {
        for( j in 0 until r){
            temp += list[j][i]
        }
        answer.addAll(temp.split("#").filterNot { it.isEmpty() || it.length <2})
        temp=""
    }

    println(answer.filter { it.length >= 2 }.sorted()[0])
}