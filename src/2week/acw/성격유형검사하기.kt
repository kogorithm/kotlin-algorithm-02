package `2week`.acw

class Solution {

    val indicator = listOf<Pair<String, String>>(
        Pair("R", "T"),
        Pair("C", "F"),
        Pair("J", "M"),
        Pair("A", "N")
    )

    //3 2 1 0 1 2 3
    var arr = Array(4) { Array(2) { 0 } }

    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""

        for (i in survey.withIndex()) {
            when (i.value) {
                "RT" -> {
                    if (choices[i.index] > 4) {
                        arr[0][1] += choices[i.index] - 4
                    } else {
                        arr[0][0] += (4 - choices[i.index])
                    }
                }

                "TR" -> {
                    if (choices[i.index] > 4) {
                        arr[0][0] += choices[i.index] - 4
                    } else {
                        arr[0][1] += (4 - choices[i.index])
                    }
                }

                "CF" -> {
                    if (choices[i.index] > 4) {
                        arr[1][1] += choices[i.index] - 4
                    } else {
                        arr[1][0] += (4 - choices[i.index])
                    }
                }

                "FC" -> {
                    if (choices[i.index] > 4) {
                        arr[1][0] += choices[i.index] - 4
                    } else {
                        arr[1][1] += (4 - choices[i.index])
                    }
                }

                "JM" -> {
                    if (choices[i.index] > 4) {
                        arr[2][1] += choices[i.index] - 4
                    } else {
                        arr[2][0] += (4 - choices[i.index])
                    }
                }

                "MJ" -> {
                    if (choices[i.index] > 4) {
                        arr[2][0] += choices[i.index] - 4
                    } else {
                        arr[2][1] += (4 - choices[i.index])
                    }
                }

                "AN" -> {
                    if (choices[i.index] > 4) {
                        arr[3][1] += choices[i.index] - 4
                    } else {
                        arr[3][0] += (4 - choices[i.index])
                    }
                }

                "NA" -> {
                    if (choices[i.index] > 4) {
                        arr[3][0] += choices[i.index] - 4
                    } else {
                        arr[3][1] += (4 - choices[i.index])
                    }
                }
            }


        }
        for (i in arr.withIndex()) {
            if (i.value[0] < i.value[1]) {
                answer += (indicator[i.index].second)
            } else {
                answer += (indicator[i.index].first)
            }
        }





        return answer
    }
}