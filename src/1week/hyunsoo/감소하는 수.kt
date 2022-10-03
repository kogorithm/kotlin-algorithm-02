package `1week`.hyunsoo

/**
 * 음이 아닌 정수 X의 자릿수가 가장 큰 자릿수부터 작은 자릿수까지 감소한다면 감소하는 수이다.
 * N번째 감소하는 수를 출력
 *
 *
 * 아이디어
 * - 0 ~ 9까지 중 하나씩 뽑아서 붙이기(DFS?)
 * - N번째 감소하는 수가 없다는 것을 어떻게 판단?
 *      최대 9자리이며, 9까지밖에 없음 수가
 * - 9 , 98, 987... 이런식으로 수를 뽑고 감소하는 수를 찾아보자.
 */

var target = readln().toInt()
val descendNum = mutableListOf<Long>()
fun main() {

    if (target in 0..10) {
        println(target)
        return
    }

    for (startNum in 0..9) {
        findDescendNum(startNum.toLong())
    }

    if (target >= descendNum.size) println(-1)
    else println(descendNum.sorted()[target])

}

fun findDescendNum(num: Long) {
    descendNum.add(num)

    val lastNum = num % 10
        .also {
            if (it == 0) return
        }


    for (newDigit in lastNum - 1 downTo 0) {
        val newNum = num * 10 + newDigit
        findDescendNum(newNum)
    }
}