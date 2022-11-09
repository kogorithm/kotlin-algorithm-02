import java.util.LinkedList

class Solution {

    val recordsArr = Array(10000) { LinkedList<String>() }
    val feeArr = Array(10000) { 0 }

    data class Time(var hour: Int, var min: Int) {

        constructor(hourAndMin: List<Int>) : this(hourAndMin[0], hourAndMin[1]) {
            this.hour = hourAndMin[0]
            this.min = hourAndMin[1]
        }

        companion object {
            fun calcUsingTime(inTime: Time, outTime: Time): Int {
                return (outTime.hour * 60 + outTime.min - inTime.hour * 60 - inTime.min)
            }
        }
    }

    fun String.timeSplit() = split(":").map { it.toInt() }

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()

        for (i in records) {
            val (time, num, inout) = i.split(" ")
            if (inout == "IN") {
                recordsArr[num.toInt()].add(time)
            } else {
                //out이 나올경우에는 바로 해당번호에 대해 처리를 해준다.
                val inTime = Time((recordsArr[num.toInt()].removeLast().timeSplit()))
                val outTime = Time(time.timeSplit())

                val usingTime = Time.calcUsingTime(inTime, outTime)
                feeArr[num.toInt()] += usingTime
            }
        }

        for (i in 0 until 10000) {
            while (recordsArr[i].isNotEmpty()) {
                val now = Time(recordsArr[i].removeFirst().timeSplit())
                val usingTime = Time.calcUsingTime(Time(23, 59), now)
                feeArr[i] += usingTime
            }
        }//In은 했는데 Out은 안한경우 위에서 처리가 안되므로 처음부터 돌면서 23:59에서 빼준다.


        //요금 계산하기
        val arr = mutableListOf<Int>()
        for (i in 0 until 10000) {

            if (feeArr[i] == 0) {
                continue
            } else {

                if (feeArr[i] <= fees[0]) {
                    arr.add(fees[1])
                    //기본요금 부과
                } else {
                    val ceilCheck = (feeArr[i] - fees[0]) % fees[2] != 0//
                    //올림하라했으므로 올림해야하는지 check해주기

                    var remain = if (ceilCheck) {
                        (feeArr[i] - fees[0]) / fees[2] + 1
                    } else {
                        (feeArr[i] - fees[0]) / fees[2]
                    }


                    arr.add(fees[1] + remain * fees[3])

                }
            }

        }
        answer = arr.toIntArray()


        return answer
    }


}