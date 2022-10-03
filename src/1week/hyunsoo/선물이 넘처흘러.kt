package `1week`.hyunsoo

/**
 * B * B 크기의 격자 모양 공장에는 역ㄷ 모양의 벨트가 있고 좌상단이 시작, 좌하딘이 종료 지점.
 * 1초 간격으로 움직임.
 * 욱제는 공장에서 M개의 선물을 포장하기 위해 N명의 직원을 고용,
 * 직원은 벨트와 인접한 칸에서만 일을 함. - 한 칸에는 한 명 이하의 사람이 존재.
 * i 번째 직원은 선물 하나를 포장하는데 Ti 초가 걸림.
 * 인접한 선물이 2개 이상이라면 벨트 위에 더 오래 올려져 있던 선물을 포장
 * - 윗 라인은 오른쪽, 아랫 라인은 왼쪽 선물
 * 선물 M개가 모두 오르고 나면, 시작 지점에는 더 이상 선물이 놓이지 않음.
 * 포장이 끝나면 선물 바로 포장 가능, 포장되지 않고 지나가면 폐기가 되어짐.
 *
 * 아이디어
 * - 그냥 구현해서 반복문으로 1개씩 시뮬레이션?
 * - 객체화 해서 풀어볼까?
 * - 1차원 배열로 생각함.
 */

data class Employee(
    val possiblePackPosition: MutableList<Int>,
    val time: Int,
    var leftTime: Int,
    var packaging: Boolean,
)

data class Gift(var pos: Int, val id: Int)

fun main() {

    val employeeList = mutableListOf<Employee>()
    val (factorySize, employeeCnt, giftCnt) = readln().split(" ").map { it.toInt() }
    val maxRailRow = factorySize - 2
    val railLength = (factorySize * 2) + factorySize - 2
    val giftList = mutableListOf<Gift>()
    var packedGiftCnt = 0

    // 사람들의 정보를 받아오기
    repeat(employeeCnt) {
        val (x, y, time) = readln().split(" ").map { it.toInt() }

        // y가 maxRailRow과 같다면 +2 한 것도 포장할 수 있음.
        val canPackPosList = mutableListOf<Int>()
        canPackPosList.add(y)

        if (y == maxRailRow) canPackPosList.add(y + 2)
        employeeList.add(
            Employee(canPackPosList, time, time, false)
        )
    }


    /*
    while(giftList.size < giftCnt ||
            giftList.any{ it.pos != -1})
     */
    repeat(100) {

        // 선물 한칸이동.
        giftList.forEach {
            it.pos++
            if (it.pos >= railLength) it.pos == -1
        }
        // 선물 계속 생성
        if (giftList.size < giftCnt) {
            giftList.add(Gift(0, giftList.size + 1))
        }

        // 사람들이 선물을 선택함
        employeeList.forEach { employee ->

            // 포장 중이 아닌 경우만
            if (employee.packaging.not()) {

                giftList.find { gift ->
                    employee.possiblePackPosition.contains(gift.pos)
                    // 선물을 포장할 수 있으면 포장하기
                }?.let { selectedGift ->
                    employee.packaging = true
                    employee.leftTime--
                    giftList.forEach{
                        if(it.id == selectedGift.id) it.pos == -1
                    }
                    packedGiftCnt++
                }

                // 포장 중이라면..
            } else {
                // 다음 턴에 포장이 끝난다면?
                if (employee.leftTime == 1) {
                    employee.leftTime = employee.time
                    employee.packaging = false
                }
            }
        }

    }

    println(packedGiftCnt)

}