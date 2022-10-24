package `3week`.byeonghee

import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))

var m = 0
var s = 0
var r = 0
var c = 0
var d = 0

data class Pos(var r: Int, var c: Int) {
    operator fun plus(add: Pos): Pos {
        return Pos(r + add.r, c + add.c)
    }

    fun isIn(): Boolean {
        return r in 0 until 4 && c in 0 until 4
    }

    fun flatIdx() : Int {
        return r * 4 + c
    }
}
data class Fish(var p: Pos, var d: Int)

val di = arrayOf(
    Pos(1, -1),
    Pos(1, 0), //하
    Pos(1, 1),
    Pos(0, 1), //우
    Pos(-1, 1),
    Pos(-1, 0), //상
    Pos(-1, -1),
    Pos(0, -1) //좌
)

var prevFishCnt = 0
var curFishCnt = 0
val prevGrid = IntArray(16) //flatten
val smells = IntArray(16)
val curGrid = IntArray(16)
val fishs = ArrayDeque<Fish>()
var shark = Pos(0, 0)
val sharkMv = Array(64) { Array(3) { Pos(0, 0) }}
lateinit var newFish: Fish
var canGo = true


fun main() {
    br.readLine().split(" ").map { it.toInt() }.let {
        m = it[0]
        s = it[1]
    }

    repeat(m) {
        br.readLine().split(" ").map { it.toInt() }.let {
            fishs.addLast(Fish(Pos(it[0]-1, it[1]-1), 8 - it[2]))
            prevGrid[Pos(it[0]-1, it[1]-1).flatIdx()]++
        }
    }

    br.readLine().split(" ").map { it.toInt() }.let {
        shark.r = it[0] - 1
        shark.c = it[1] - 1
    }

    val dPriority = listOf(5, 7, 1, 3)
    var cnt = 0

    for(f in dPriority) for(s in dPriority) for (t in dPriority) {
        sharkMv[cnt++] = arrayOf(di[f], di[f] + di[s], di[f] + di[s] + di[t])
    }


    var maxCnt = -1
    var tmpCnt: Int
    var maxMv = -1

    for(t in 0 until s) {
        // 1, 2번 같이 수행
        prevFishCnt = curFishCnt
        curFishCnt = fishs.size
        for(i in 0 until fishs.size) {
            while (i >= prevFishCnt && smells[fishs[i].p.flatIdx()] == 2) {
                fishs.removeAt(i)
                curFishCnt--
                if (i == fishs.size) break
            }
            newFish = fishs[i].copy()
            canGo = true
            while((newFish.p + di[newFish.d]).isIn().not()
                || smells[(newFish.p + di[newFish.d]).flatIdx()] > 0
                || shark == (newFish.p + di[newFish.d])) {
                newFish.d = (newFish.d + 1) % 8
                if (newFish.d == fishs[i].d) {
                    canGo = false
                    break
                }
            }
            if(canGo) newFish.p += di[newFish.d]
            fishs.add(newFish)
            curGrid[newFish.p.flatIdx()]++
        }

        //3-1. 상어 이동순서 구하기
        maxCnt = -1
        maxMv = -1
        for(mv in sharkMv.indices) {
            if (sharkMv[mv].any { (shark + it).isIn().not() }) continue

            sharkMv[mv].let {
                tmpCnt = curGrid[(it[0] + shark).flatIdx()]
                tmpCnt += curGrid[(it[1] + shark).flatIdx()]
                if (it[2] != it[0]) {
                    tmpCnt += curGrid[(it[2] + shark).flatIdx()]
                }
            }

            if (tmpCnt > maxCnt) {
                maxCnt = tmpCnt
                maxMv = mv
            }
        }

        //3-2. 상어 잡아먹기 + 냄새 흐려지기
        for(i in 0 until 16) {
            if (smells[i] > 0) smells[i]--
            prevGrid[i] += curGrid[i]
        }
        sharkMv[maxMv].map{ (shark + it).flatIdx() }.forEach {
            if (prevGrid[it] > 0) {
                smells[it] += 2
                prevGrid[it] -= curGrid[it]
            }
        }
        shark += sharkMv[maxMv][2]
        curGrid.fill(0)
    }

//    tmpCnt = 0
//    for(i in 0 until 16) {
//        if (smells[i] == 2) tmpCnt += prevGrid[i]
//    }

//    println(fishs.size - tmpCnt)
    println(prevGrid.sumOf { it })
}