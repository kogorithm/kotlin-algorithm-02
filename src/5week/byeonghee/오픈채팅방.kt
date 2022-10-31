package `5week`.byeonghee

class 소병희_오픈채팅방 {
    companion object {
        var answer = mutableListOf<String>()
        val uidMap = hashMapOf<String, String>()

        fun solution(record: Array<String>): Array<String> {
            val log = record.map{ it.split(" ") }

            log.forEach {
                when(it[0]) {
                    "Enter", "Change" -> uidMap.put(it[1], it[2])
                }
            }

            log.forEach {
                when(it[0]) {
                    "Enter" -> answer.add("${uidMap[it[1]]!!}님이 들어왔습니다.")
                    "Leave" -> answer.add("${uidMap[it[1]]!!}님이 나갔습니다.")
                }
            }

            return answer.toTypedArray()
        }
    }
}