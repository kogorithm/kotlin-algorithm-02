package `1week`.byeonghee


/**
 * 접근방법: 파스칼의 삼각형 + 하키스틱 패턴
 *
 * @pascal 이라는 이름의 이차원 배열을 만들고 [0][0]부터 파스칼의 삼각형을 구현한다.
 * @pascal [i][j] = (j+1)자리의 숫자 중 (i)로 시작하는 숫자의 개수가 된다.
 *
 * @하키스틱패턴  0~i행의 j번째 열의 값을 모두 더한 값이 (j+1)열의 (i+1)행에 위치한다.
 *               pascal[i][j]의 값은 j자리 수 숫자 중 i 미만의 숫자로 시작하는 '감소하는 수'의 개수로 볼 수 있다.
 *
 * @후기  11*11 행렬이라 더 빠를 것 같아서 꾸역꾸역 풀었는데
 *       어차피 1023개밖에 안나오니 100개나 1000개나 수준이라
 *       감소하는 수의 리스트를 만들어 정렬하는 게 나았을 것 같다..
 */


import kotlin.system.exitProcess

class `감소하는수_소병희` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        private val pascal = List(11) { IntArray(11) }

        private fun initPascal() {
            for(i in 0..10) {
                pascal[i][0] = 1

                for(j in 1..i) {
                    pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j]
                }
            }
        }

        var order = 0
        var answer = 0L
        var row = 0
        var col = 0

        fun solution() {
            order = readln().toInt().let {
                if (it <= 0) { println(0); exitProcess(0) }
                else it + 1
            }

            initPascal()

            with(pascal[10]) {
                this[0] = 0
                while(col < 11 && this[col] < order) {
                    order -= this[col++]
                }
            }
            if (col == 11 && order > 0) { println(-1); exitProcess(0) }

            while(col > 0) {
                row = (9 downTo 0).first { pascal[it][col] < order }
                order -= pascal[row][col--]
                answer = answer * 10 + row
            }

            println(answer)
        }
    }
}

fun main() {
    `감소하는수_소병희`.getSolution().solution()
}