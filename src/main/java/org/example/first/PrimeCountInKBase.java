package org.example.first;

import java.util.ArrayList;
import java.util.List;

public class PrimeCountInKBase {

    public static void main(String[] args) {
        int n = 437674;
        int k = 3;

        int primeCount = countPrimesInKBase(n, k);
        System.out.println("Number of prime numbers: " + primeCount);
    }

    // 주어진 정수를 n진수로 변환
    private static String intToKBase(int number, int base) {
        if (number == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(number % base);
            number /= base;
        }
        return sb.reverse().toString();
    }

    // 변환된 n진수 문자열에서 소수의 개수를 세는 함수
    private static int countPrimesInKBase(int number, int base) {
        String kBaseNumber = intToKBase(number, base);
        String[] parts = kBaseNumber.split("0+"); // 0을 기준으로 분할

        int primeCount = 0;
        for (String part : parts) {
            if (!part.isEmpty() && isPrime(Long.parseLong(part))) {
                primeCount++;
            }
        }
        return primeCount;
    }

    // 소수 판별 함수
    private static boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        for (long i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
