import kotlin.math.max
import kotlin.system.exitProcess

/**
 *  n개의 키가 비어있고 키마다 스킬을 셋팅해주어야 함.
 *  퀘스트 1개마다 k개의 스킬을 사용할 수 있어야 깰 수 있음.
 *  최대한 많이 퀘스트를 통과할 수 있도록 키에 스킬을 지정해 주어야 한다.
 *
 *  스킬의 갯수 최대 29개.
 *  아이디어 !
 *  1. 퀘스트마다 필요한 스킬들을 전부 모아서
 *  2. 그 중에 n 개를 뽑는 경우의 수를 구하기.
 *  3. 2에서 구한 경우의 수 중에 통과할 수 있는 퀘스트를 구하기
 *  4. 만약!! 통과할 수 있는 경우의 수가 퀘스트 갯수와 같다면 종료.
 *
 *  반례
 *  3 4 2
 *  1 2
 *  1 2
 *  1 2
 *  1 2
 *
 *  퀘스트마다 필요한 스킬들을 전부 모아서 조합할 때,
 *  만약 필요한 스킬의 종류가 n 보다 숫자가 작다면 조합이 불가능... 2개 중에 3개를 뽑는 것은 불가능 하기 때문!!
 *  이 부분에 대한 예외처리가 필요했음.
 */

var can = 0
lateinit var quest : Array<IntArray>

fun main(){
    val (n,m,k) = readln().split(" ").map { it.toInt() }
    quest = Array(m){IntArray(k)}

    repeat(m){
        val want = readln().split(" ").map { it.toInt() }.toIntArray()
        quest[it] = want
    }

    val skills = quest.flatMap { it.asIterable() }.distinct() // 퀘스트마다 필요한 스킬들
    if (skills.size <= n){
        can = m
    }else{
        val canSkill = mutableListOf<List<Int>>()
        combination(canSkill,skills, Array<Boolean>(skills.size){false},0,n)
        canSkill.forEach {com ->
            var count = 0
            quest.forEach { it ->
                // 지금 퀘스트를 꺨 수 있는 지 확인.
                val temp = it.count { com.contains(it) }
                if (temp == it.size){ // 두개 다 통과
                    count++
                }
            }
            if (count == quest.size){
                println(quest.size)
                exitProcess(0)
            }else{
                can = max(can,count)
            }
        }
    }
    println(can)
}

fun <T> combination(answer: MutableList<List<T>>, el: List<T>, ck: Array<Boolean>, start: Int, target: Int) {
    if(target == 0) {
        answer.addAll( listOf(el.filterIndexed { index, t -> ck[index] }) )
    } else {
        for(i in start until el.size) {
            ck[i] = true
            combination(answer, el, ck, i + 1, target - 1)
            ck[i] = false
        }
    }
}

