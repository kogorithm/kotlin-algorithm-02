package `2week`.byeonghee

/**
 * @접근방식 :  알파벳순으로 빠른 4개를 posType, 그 반대 유형을 같은 인덱스로 negType에 저장
 *
 */

class `소병희_성격 유형 검사하기` {
    private val scores = IntArray(4)
    private val posType = arrayOf('R', 'C', 'J', 'A')
    private val negType = arrayOf('T', 'F', 'M', 'N')

    private fun getType(c: Char): Int {
        return when(c) {
            'R', 'T' -> 0
            'F', 'C' -> 1
            'M', 'J' -> 2
            'A', 'N' -> 3
            else -> -1
        }
    }

    private fun getTypeSign(c: Char): Int {
        return when(c) {
            in posType -> 1
            in negType -> -1
            else -> 0
        }
    }

    private fun getPoint(i: Int) : Int {
        return if (i > 4) i - 4 else 4 - i
    }

    private fun getPointSign(i : Int): Int {
        return if (i < 4) 1
        else if (i > 4) -1
        else 0
    }

    fun solution(surveys: Array<String>, choices: IntArray): String {
        for(i in surveys.indices) {
            val c = surveys[i].first()
            val op = choices[i]

            scores[getType(c)] += getTypeSign(c) * getPointSign(op) * getPoint(op)
        }

        val sb = StringBuilder()
        for(i in 0 until 4) {
            sb.append(
                if (scores[i] >= 0) posType[i]
                else negType[i]
            )
        }

        return sb.toString()
    }
}