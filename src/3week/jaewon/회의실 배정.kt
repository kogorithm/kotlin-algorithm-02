
class jaewon회의실배정 {

    data class TimeTable(val start : Int, val end : Int)

    fun solution(){
        val n = readln().toInt()
        val timeTable = mutableListOf<TimeTable>()
        var endTime = 0
        var count = 0

        repeat(n){
            val (start,end) = readln().split(" ").map { it.toInt() }
            timeTable.add(TimeTable(start, end))
        }

        timeTable
            .sortedWith( compareBy<TimeTable> { it.end }.thenBy { -(it.end - it.start) })
            .forEach {
                if (it.start >= endTime){
                    count++
                    endTime = it.end
                }
            }

        println(count)
    }
}

fun main(){
    jaewon회의실배정().solution()
}

