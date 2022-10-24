package `4week`.byeonghee

class `소병희_주차 요금 계산` {

    companion object {

        const val BASE_TIME = 0
        const val BASE_FEE = 1
        const val UNIT_TIME = 2
        const val UNIT_FEE = 3

        fun String.toMinutes() = this.split(":").map{ it.toInt() }.let{ it.first() * 60 + it.last() }
    }

    val closingT = "23:59".toMinutes()
    val checkMap = hashMapOf<String, Int>()
    val timeMap = hashMapOf<String, Int>()
    lateinit var answer: IntArray

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        for((t, c, s) in records.map{ it.split(" ")}.sortedBy{ it.first() }) {
            when (s) {
                "IN" -> checkMap.put(c, t.toMinutes())
                "OUT" -> {
                    timeMap.put(c, timeMap.getOrDefault(c, 0) + t.toMinutes() - checkMap[c]!!)
                    checkMap.put(c, -1)
                }
            }
        }

        answer = IntArray(checkMap.keys.size)
        var fee = 0
        var i = 0
        for(c in checkMap.keys.sorted()) {
            if (checkMap[c]!! != -1) {
                timeMap.put(c, timeMap.getOrDefault(c, 0) + closingT - checkMap[c]!!)
            }

            fee = fees[BASE_FEE]
            fee += Integer.max(0, timeMap[c]!! - fees[BASE_TIME]).let {
                (it / fees[UNIT_TIME]) + (if (it % fees[UNIT_TIME] == 0) 0 else 1)
            } * fees[UNIT_FEE]

            answer[i++] = fee
        }

        return answer
    }
}