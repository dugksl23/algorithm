package org.example.first.day2;

import java.util.Stack;


public class Delivery {

    public static void main(String[] args) {

//        int[] order = new int[]{4, 3, 1, 2, 5};
//        int[] order = new int[]{5, 4, 3, 2, 1};
        int[] order = new int[]{4, 5, 1, 2, 3};

        Solution solution = new Solution();
        int count = solution.solution(order);
        System.out.println("count : " + count);

    }


    static class Solution {
        public Solution() {
        }

        public int solution(int[] order) {


            //1. 여비 컨테이너 벨트 stack 생성 및 반환 상자 count++
            Stack<Integer> extraBelt = new Stack<>();
            int index = 0;
            // 2. 컨테이너 벨트는 for　문으로 생성
            for (int box = 1; box <= order.length; box++) {
                if (order[index] != box) {
                    extraBelt.push(box);
                } else {
                    index++;
                }

//                // 3. extraBelt 에서 후입 선출로 다음 필요한 상자와 일치 여부 확인
//                // stack.peak(); 마지막 요소만 반환, 값 제거 x
//                while (!extraBelt.isEmpty() && extraBelt.peek() == order[index]) {
//                    extraBelt.pop();
//                    index++;
//                }
            }


            return index;
        }
    }

}

