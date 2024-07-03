package org.example.first.day3;

import java.util.*;

public class Card {

    private static List<Integer> remainders = new ArrayList<>();

    public static void main(String[] args) {
        int[] arrayA = new int[]{10, 17};
        int[] arrayB = new int[]{5, 20};
        int solution = solution(arrayA, arrayB);
        System.out.println(solution);
    }

    public static int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        // 1. ArrayA와 ArrayB의 모든 숫자를 나눌 수 있는 약수 구하기
        findAllRemainders(arrayA);
        findAllRemainders(arrayB);

        // 2. 조건을 만족하는 가장 큰 양의 정수 a 찾기
        for (int n : remainders) {
            boolean allDivisibleByA = true;
            boolean noneDivisibleByB = true;
            boolean allDivisibleByB = true;
            boolean noneDivisibleByA = true;

            // arrayA의 모든 요소가 n으로 나누어 떨어지는지 확인
            for (int numA : arrayA) {
                if (numA % n != 0) {
                    allDivisibleByA = false;
                }
                if (numA % n == 0) {
                    noneDivisibleByA = false;
                }
            }

            // arrayB의 모든 요소가 n으로 나누어 떨어지는지 확인
            for (int numB : arrayB) {
                if (numB % n != 0) {
                    allDivisibleByB = false;
                }
                if (numB % n == 0) {
                    noneDivisibleByB = false;
                }
            }

            // 철수의 모든 카드가 n으로 나누어 떨어지고, 영희의 모든 카드가 n으로 나누어 떨어지지 않으면
            if (allDivisibleByA && noneDivisibleByB) {
                answer = Math.max(answer, n);
            }

            // 영희의 모든 카드가 n으로 나누어 떨어지고, 철수의 모든 카드가 n으로 나누어 떨어지지 않으면
            if (allDivisibleByB && noneDivisibleByA) {
                answer = Math.max(answer, n);
            }

        }

        return answer;
    }

    private static void findAllRemainders(int[] arr) {
        for (int n : arr) {
            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    remainders.add(i);
                    if (i != n / i) {
                        remainders.add(n / i);
                    }
                }
            }
        }
    }


}
