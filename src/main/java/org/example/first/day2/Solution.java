package org.example.first.day2;

import java.util.Stack;

import static java.lang.System.out;

public class Solution {

    public static void main(String[] args) {

        int[] order = new int[]{4, 3, 1, 2, 5};
//        int[] order = new int[]{5, 4, 3, 2, 1};

        Solution solution = new Solution();
        int count = solution.solution(order);

    }


    public static int solution(int[] order) {

        int answer = 0;

        //1. 여비 컨테이너 벨트 stack 생성 및 반환 상자 index++
        Stack<Integer> extraBelt = new Stack<>();
        int index = 0;

        // 2. 컨테이너 벨트는 for　문으로 생성
        for (int box = 1; box <= order.length; box++) {
            // box 를 0번으로 설정할 경우, 5번부터 넣게 될 경우, 0,1,2,3,4 에서 맞지 않기에 length 가 5가 된다. 결국 4개로 남아야 하기에...
            if (order[index] != box) {
                extraBelt.push(box);
            } else {
                index++;
            }

            // 3. extraBelt 에서 후입 선출로 다음 필요한 상자와 일치 여부 확인
//             stack.peak(); 마지막 요소만 반환, 값 제거 x
            while (!extraBelt.isEmpty() && extraBelt.peek() == order[index]) {
                extraBelt.pop();
                index++;
            }

        }

        // 3. extraBelt 에서 후입 선출로 다음 필요한 상자와 일치 여부 확인, stack.peak(); 마지막 요소만 반환, 값 제거 x
//        for (int i = 0; i < order.length; i++) {
//            if (!extraBelt.isEmpty() && extraBelt.peek() == order[i]) {
//                extraBelt.pop();
//                index++;
//            }
//        }

        answer = index;

        out.println("count : " + answer);
        return answer;
    }

}
