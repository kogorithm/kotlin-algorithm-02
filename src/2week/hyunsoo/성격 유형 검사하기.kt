package `2week`.hyunsoo


val personalityMap = mutableMapOf(
    'R' to 0,
    'C' to 0,
    'J' to 0,
    'T' to 0,
    'A' to 0,
    'F' to 0,
    'M' to 0,
    'N' to 0,
)

class `전현수_성격_유형_검사하기` {
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""

        survey.forEachIndexed { index, personalities ->
            val firPersonality = personalities[0]
            val secPersonality = personalities[1]

            when (choices[index]) {
                1 -> {
                    personalityMap[firPersonality] = personalityMap.getValue(firPersonality) + 3
                }
                2 -> {
                    personalityMap[firPersonality] = personalityMap.getValue(firPersonality) + 2
                }
                3 -> {
                    personalityMap[firPersonality] = personalityMap.getValue(firPersonality) + 1
                }
                5 -> {
                    personalityMap[secPersonality] = personalityMap.getValue(secPersonality) + 1
                }
                6 -> {
                    personalityMap[secPersonality] = personalityMap.getValue(secPersonality) + 2
                }
                7 -> {
                    personalityMap[secPersonality] = personalityMap.getValue(secPersonality) + 3
                }
            }
        }

        answer += if (personalityMap.getOrDefault('R', 0) >=
            personalityMap.getOrDefault('T', 0)
        ) {
            "R"
        } else "T"

        answer += if (personalityMap.getOrDefault('C', 0) >=
            personalityMap.getOrDefault('F', 0)
        ) {
            "C"
        } else "F"

        answer += if (personalityMap.getOrDefault('J', 0) >=
            personalityMap.getOrDefault('M', 0)
        ) {
            "J"
        } else "M"

        answer += if (personalityMap.getOrDefault('A', 0) >=
            personalityMap.getOrDefault('N', 0)
        ) {
            "A"
        } else "N"

        return answer
    }
}

fun main() {
    val testSurvey = arrayOf(
        "AN", "CF", "MJ", "RT", "NA"
    )
    val choices = intArrayOf(5, 3, 2, 7, 5)
    val test = 전현수_성격_유형_검사하기()
    test.solution(testSurvey, choices)
}