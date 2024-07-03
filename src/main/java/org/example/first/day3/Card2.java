package org.example.first.day3;

import java.util.*;

public class Card2 {

    private static final Set<Integer> remainders = new HashSet<>();

    public static void main(String[] args) {
        int[] arrayA = new int[]{10, 20};
        int[] arrayB = new int[]{5, 17};

        int solution = solution(arrayA, arrayB);
        System.out.println(solution);
    }

    public static int solution(int[] arrayA, int[] arrayB) {
        int answer = -1; // 양의 정수를 찾기 위해 초기값을 -1로 설정

        // 1. ArrayA 의 모든 숫자를 나눌 수 있는 약수 구하기
        findAllRemainders(arrayA);
        // 2. ArrayB 의 모든 숫자를 나눌 수 있는 정수 구하기
        findAllRemainders(arrayB);

        // 3. 철수가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고, 영희가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a 찾기
        for (int n : remainders) {
            boolean allDivisibleByA = true;
            boolean noneDivisibleByB = true;

            // arrayA의 모든 요소가 n으로 나누어 떨어지지 않는지 확인
            for (int numA : arrayA) {
                if (numA % n != 0) {
                    allDivisibleByA = false;
                    break;
                }
            }

            // arrayB의 모든 요소 중에서 n으로 나누어 떨어지는 숫자가 있는지 확인
            for (int numB : arrayB) {
                if (numB % n == 0) {
                    noneDivisibleByB = false;
                    break;
                }
            }

            // 철수의 모든 카드가 n으로 나누어 떨어지고, 영희의 모든 카드가 n으로 나누어 떨어지지 않으면
            // 정답 후보로 설정 (가장 큰 양의 정수를 찾기 위해 Math.max 사용)
            if (allDivisibleByA && noneDivisibleByB) {
                answer = Math.max(answer, n);
            }
        }

        return answer;
    }

    private static void findAllRemainders(int[] arr) {
        for (int n : arr) {
            // 주어진 숫자 n의 모든 약수를 찾아서 remainders에 추가
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    remainders.add(i);
                }
            }
        }
    }
}
