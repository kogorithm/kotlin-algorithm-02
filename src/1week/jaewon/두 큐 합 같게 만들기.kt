package `1week`.jaewon

class `두 큐 합 같게 만들기` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        fun solution(queue1: IntArray, queue2: IntArray): Long {
            var answer: Long = 0
            val temp1 = queue1.toMutableList()
            val temp2 = queue2.toMutableList()
            val want = (queue1.sum()+queue2.sum())/2

            while(true){
                val sum1 = temp1.sum()
                val sum2 = temp2.sum()

                if(sum1 == want){
                    break
                }else if(sum1 > sum2){
                    val first = temp1.removeFirst()
                    temp2.add(first)
                    answer++
                }else if (sum1 < sum2){
                    val first = temp2.removeFirst()
                    temp1.add(first)
                    answer++
                }
            }
            return answer
        }
    }
}

fun main() {
    println(`두 큐 합 같게 만들기`.getSolution().solution(intArrayOf(3,2,7,2), intArrayOf(4,6,5,1)))
}