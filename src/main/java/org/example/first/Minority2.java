package org.example.first;

public class Minority2 {

    public static void main(String[] args) {
        int number = 112232224;
        int numberSystem = 3;

        String kBaseResult = intToKBase(number, numberSystem);
        int primeNumber = countPrimesInKBase(kBaseResult);
        System.out.println("Number of prime numbers: " + primeNumber);
    }

    // 주어진 정수를 n진수로 변환
    private static String intToKBase(int number, int base) {
        if (number == 0 && base < 1) {
            return "0";
        }

        StringBuilder resultHolder = new StringBuilder();
        while (number > 0) {
            int remainder = number % base;
            number /= base;
            resultHolder.append(remainder);
        }

        // 역순으로 반환
        return resultHolder.reverse().toString();
    }

    // 변환된 n 진수 문자열에서 소수의 개수를 세는 함수
    private static int countPrimesInKBase(String kBaseNumber) {
        System.out.println("kBaseNumber : " + kBaseNumber);
        String[] split = kBaseNumber.split("0+");

        int primeCount = 0;
        for (String str : split) {
            if (!str.isEmpty() && isPrime(Integer.parseInt(str))) {
                primeCount++;
            }
        }
        return primeCount;
    }

    // 소수 판별 함수
    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
