package `1week`.byeonghee

class `Byeonghee_두큐합같게만들기` {
    class Solution {
        var diff = 0
        var len = 0
        var answer = 0
        var p1 = 0
        var p2 = 0
        var pop = 0

        fun solution(queue1: IntArray, queue2: IntArray): Int {
            diff = (queue1.sum() - queue2.sum()) / 2
            if (diff == 0) return 0

            len = queue1.size

            while(diff != 0) {
                pop = when(diff > 0) {
                    true -> { (-1) *
                            if((p1 / len) % 2 == 0) queue1[(p1++ % len)]
                            else queue2[(p1++ % len)]
                    }
                    false -> {
                        if ((p2 / len) % 2 == 0) queue2[(p2++ % len)]
                        else queue1[(p2++ % len)]
                    }
                }

                answer++
                diff += pop
                if (p1 >= len * 2 || p2 >= len * 2) return -1
            }
            return answer
        }
    }
}