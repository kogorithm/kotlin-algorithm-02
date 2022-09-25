
// 프로그래머스 문제를 풀 때 아래처럼 코드를 짜게되면 같은 이름의 클래스가 존재할 수 있습니다!!

class `Q1` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        fun solution(): Int {
           var answer = 0


            return answer
        }
    }
}

fun main() {
    println(Q1.getSolution().solution())
}