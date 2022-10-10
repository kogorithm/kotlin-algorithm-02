/**
 *
 */
class `성격 유형 검사하기` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        fun solution(survey: Array<String>, choices: IntArray): String {
            var answer: String = ""
            val type = mutableMapOf<Char,Int>('R' to 0,'T' to 0 ,'C' to 0 ,'F' to 0 ,'J' to 0 ,'M' to 0 ,'A' to 0 ,'N' to 0 )

            choices.zip(survey).forEach {
                when(it.first){
                    1 -> type[it.second[0]]  = type[it.second[0]]!! + 3
                    2 -> type[it.second[0]]= type[it.second[0]]!! + 2
                    3 -> type[it.second[0]]= type[it.second[0]]!! + 1
                    in 5..7 -> type[it.second[1]]= type[it.second[1]]!! + it.first-4
                }
            }
            println(type.toString())
            answer += if (type['R']!! >= type['T']!!){ "R" }else "T"
            answer += if (type['C']!! >= type['F']!!){ "C" }else "F"
            answer += if (type['J']!! >= type['M']!!){ "J" }else "M"
            answer += if (type['A']!! >= type['N']!!){ "A" }else "N"

            return answer
        }
    }
}

fun main() {
    println(`성격 유형 검사하기`.getSolution().solution(arrayOf("AN","CF","MJ","RT","NA"), intArrayOf(5,3,2,7,5)))
}