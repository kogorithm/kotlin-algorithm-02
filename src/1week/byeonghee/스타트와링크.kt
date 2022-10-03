package `1week`.byeonghee

/**
 * @스타트팀  0번 선수가 속한 팀
 * @후기  너무 힘들게 풀어서 현타오네요...
 */

import java.io.*
import kotlin.math.abs

val synergies = hashMapOf<Set<Int>, Int>()
var N = 0
var teamSize = 0
var teamStartCap = 0
var teamLinkCap = 0
var capGapList = mutableListOf<Int>()
var pair = setOf<Int>()

val teamStartCases = mutableListOf<List<Int>>()
var tmpTeamStart = mutableListOf<Int>()
var tmpTeamLink = mutableListOf<Int>()

val br = BufferedReader(InputStreamReader(System.`in`))

fun main(): Unit = with(br) {
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

fun makeCases() {
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