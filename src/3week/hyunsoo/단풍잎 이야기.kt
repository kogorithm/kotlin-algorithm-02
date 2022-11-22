package `3week`.hyunsoo

/**
 * <문제>
 * [단풍잎 이야기](https://www.acmicpc.net/problem/16457)
 *
 * 키셋팅을 하면 원하는 키를 눌러서 원하는 스킬을 쓸 수 있음.
 * 리유나는 레벨 225, 라가는 레벨이 202
 * 라가가 키보드에 있는 키를 n개 부숨
 * 리유나는 그래도 퀘스트를 해야함. 2n개의 스킬들 중 n개를 적절히 배치해서 해야함
 *
 * m개의 퀘스트가 주어지고 k개의 스킬을 사용해야함.
 * 스킬을 사용할 수 없으면 퀘스트 수행 불가
 *
 * 입/출력
 * - 첫째 줄
 *   - 키의 개수 n, 퀘스트의 개수 m, 퀘스트 당 사용해야하는 스킬의 수 k
 * - 둘째 줄
 *   - 각각의 퀘스트에서 사용해야하는 스킬들이 나옴.
 *
 * 아이디어
 * - 완탐 드가자
 *
 *
 */

class `전현수_단풍잎_이야기`{

    val questDataMap = mutableListOf<List<Int>>()
    val pickedKey = mutableListOf<Int>()
    var canClearQuestCnt = 0

    fun solution() {

        val (keyCnt, questCnt, skillPerQuest) = readln().split(" ").map { it.toInt() }
        val skillList = (1..2 * keyCnt).toList()

        repeat(questCnt) {
            val quests = readln().split(" ").map { it.toInt() }
            questDataMap.add(quests)
        }
        keySet(0, keyCnt, 0, skillList)
        println(canClearQuestCnt)
    }

    fun keySet(cnt: Int, depth: Int, startWith: Int, skillList: List<Int>) {
        if (cnt == depth) {

            var curCanClearQuestCnt = 0
            questDataMap.forEach { needKeyList ->
                if (needKeyList.all { it in pickedKey }) {
                    curCanClearQuestCnt++
                }
            }
            if (curCanClearQuestCnt >= canClearQuestCnt) {
                canClearQuestCnt = curCanClearQuestCnt
            }

            return
        }

        for (index in startWith until skillList.size) {
            pickedKey.add(skillList[index])
            keySet(cnt + 1, depth, index + 1, skillList)
            pickedKey.removeAt(pickedKey.lastIndex)
        }
    }
}

fun main(){
    val myClass = `전현수_단풍잎_이야기`()
    myClass.solution()
}