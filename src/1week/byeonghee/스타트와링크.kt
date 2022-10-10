package `1week`.byeonghee

/**
 * @스타트팀  0번 선수가 속한 팀
 * @후기  너무 힘들게 풀어서 현타오네요...
 */

import java.io.*
import kotlin.math.abs

class `스타트와 링크_소병희` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        private val synergies = hashMapOf<Set<Int>, Int>()
        private var N = 0
        private var teamSize = 0
        private var teamStartCap = 0
        private var teamLinkCap = 0
        private var capGapList = mutableListOf<Int>()
        private var pair = setOf<Int>()

        private val teamStartCases = mutableListOf<List<Int>>()
        private var tmpTeamStart = mutableListOf<Int>()
        private var tmpTeamLink = mutableListOf<Int>()

        private val br = BufferedReader(InputStreamReader(System.`in`))

        fun solution(): Unit = with(br) {
            N = readLine().toInt()

            repeat(N) { i ->
                readLine().split(" ").map { it.toInt() }
                    .forEachIndexed { j, sy ->
                        pair = setOf(i, j)
                        synergies[pair] = synergies.getOrDefault(pair, 0) + sy
                    }
            }

            teamSize = N / 2
            makeCases()

            val players = (0 until N)
            teamStartCases.forEach { team ->
                teamStartCap = 0
                teamLinkCap = 0
                tmpTeamLink = players.toMutableList().also{ it.removeAll(team) }

                for(i in 0 until teamSize) for(j in i+1 until teamSize) {
                    teamStartCap += synergies[setOf(team[i], team[j])]!!
                    teamLinkCap += synergies[setOf(tmpTeamLink[i], tmpTeamLink[j])]!!
                }

                capGapList.add(abs(teamStartCap - teamLinkCap))
            }

            println(capGapList.minOf { it })
        }

        private fun makeCases() {
            tmpTeamStart.addAll((0 until teamSize).toList())
            teamStartCases.add(tmpTeamStart.toList())

            caseMaking@ while(true) {
                tmpTeamStart[teamSize - 1]++

                while(tmpTeamStart.last() >= N) {
                    var idx = tmpTeamStart.lastIndex
                    while(idx > 1 && tmpTeamStart[idx] >= N) {
                        tmpTeamStart[--idx]++
                    }
                    if (tmpTeamStart[1] > N - teamSize + 1) break@caseMaking

                    while(++idx < teamSize) {
                        tmpTeamStart[idx] = tmpTeamStart[idx - 1] + 1
                    }
                }

                teamStartCases.add(tmpTeamStart.toList())
            }
        }
    }
}

fun main() {
    `스타트와 링크_소병희`.getSolution().solution()
}