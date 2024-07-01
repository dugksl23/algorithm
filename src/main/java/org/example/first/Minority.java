package org.example.first;

public class Minority {

    public static void main(String[] args) {
        int number = 112232224;
        int numberSystem = 3;

        String ternaryResult = intToKBase(number, numberSystem);
        int primeNumber = countPrimesIntKBase(ternaryResult);
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
    private static int countPrimesIntKBase(String kBaseNumber) {
        System.out.println("kBaseNumber : " + kBaseNumber);
        String[] split = kBaseNumber.split("0+");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }

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

        // 소수 1,2
        if (number == 2) {
            return true;
        }

        // 짝수는 2를 제외하고는 소수가 되지 않는다.
        if (number % 2 == 0) {
            return false;
        }
        /**
         * 약수 : 어떤 정수를 나누어 나머지 없이 나누어 떨어지게 하는 자연수
         * ex) 6의 약수 : 1,2,3,6
         *
         *
         * 소수 : 1과 자신 외에는 다른 약수를 가지지 않는 1보다 큰 자연수
         * 즉 단 2개뿐인 약수이다.
         * ex) 2의 약수는 1,2
         *     3의 약수는 1,3
         */

        /**
         * 제곱근을 계산하여 반환하는 Java.math 라이브러리
         *          * 어떤 정수의 제곱근을 통해서 소수 확인 가능하다.
         *          * ex)
         */

        // 이미 위에서 2의 짝수 여부를 판단했기 때문이다.
        for (int i = 3; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


}
