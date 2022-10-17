import kotlin.math.absoluteValue
import kotlin.math.min

/**
 *  고려할 상황
 *  1. 번호로만 이동할 수 있는 경우
 *  2. +-로만 이동할 수 있는 경우
 *  3. 번호 누르고 +-로 이동하는 경우
 *
 *  1번보다는 무조건 3번이 버튼을 많이 누르게 됨
 *
 *  따져야 할 조건 순서
 *  첫번째. 1번이 가능하다면 , 1번과 2번 비교
 *  두번째. 1번이 불가능하다면,  2번과 3번 비교
 *
 *  3번에서 눌러야할 번호를 찾는 방법
 *  입력받은 번호가 4자리 수라면
 *  안 고장난 숫자로 만들 수 있는 3자리 4자리 5자리의 수를 만든다.....
 *  거기 중에서 뺐을 때 제일 작은 수 반환..
 */


class `리모컨` {
    companion object{
        fun getSolution() : Solution{
            return Solution()
        }
    }

    class Solution {
        lateinit var notBroken : MutableList<Int>
        lateinit var broken : List<Int>
        lateinit var goal : String
        var answer = Int.MAX_VALUE

        fun solution() {
            goal = readln()
            val m = readln().toInt().let {
                if (it == 0){
                    broken = listOf()
                    notBroken = mutableListOf(0,1,2,3,4,5,6,7,8,9)
                } else {
                    broken = readln().split(" ").map { it.toInt() }
                    notBroken = mutableListOf()
                    for (i in 0..9){
                        if (!broken.contains(i)){
                            notBroken.add(i)
                        }
                    }
                }
            }

            val moveOnly =(goal.toInt()-100).absoluteValue
            if (goal.toInt() == 100){
                //채널이 100일 경우
                answer = 0
            }else if(broken.count{ !goal.contains(it.toString())} == broken.size){
                //번호로만 이동이 가능한 경우
                val numOnly = goal.length
                answer = min(numOnly,moveOnly)
            }else {
                //번호로만 이동이 불가능할 경우
                // 숫자가 1000이라면 100~10000까지 숫자의 n의 자리 수라면 n-1~ n+1 자리수의 숫자들을 순회
                if (goal.length == 1){
                    makeNum(notBroken,Array(1){0},0,1)
                    makeNum(notBroken,Array(2){0},0,2)
                }else {
                    makeNum(notBroken,Array(goal.length-1){0},0,goal.length-1)
                    makeNum(notBroken,Array(goal.length){0},0,goal.length)
                    makeNum(notBroken,Array(goal.length+1){0},0,goal.length+1)
                }
                answer = min(moveOnly,answer)
            }

            println(answer)
        }


        fun makeNum(arr : MutableList<Int>, output : Array<Int>, depth : Int, r : Int){
            if(depth == r){
                val num = output.joinToString("").toInt()
                val move = (goal.toInt()-num).absoluteValue + num.toString().length
                answer = min(move,answer)
                return
            }
            for (i in arr.indices){
                output[depth] = arr[i]
                makeNum(arr,output,depth+1, r)
            }
        }
    }
}

fun main() {
    `리모컨`.getSolution().solution()
}
