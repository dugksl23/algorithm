package org.example.first.day1;

import java.util.*;

public class ParkingLotCalculation {

    static int[] fees = {180, 5000, 10, 600};

    static String[] records = {
            "05:34 5961 IN",
            "06:00 0000 IN",
            "06:34 0000 OUT",
            "07:59 5961 OUT",
            "07:59 0148 IN",
            "18:59 0000 IN",
            "19:09 0148 OUT",
            "22:59 5961 IN",
            "23:00 5961 OUT"};


    public static void main(String[] args) {
        solution(fees, records);

    }

    public static int[] solution(int[] fees, String[] records) {
        // 주차 요금 설정
        int basicTime = fees[0]; // 기본 시간 (분)
        int basicFee = fees[1]; // 기본 요금
        int perTime = fees[2]; // 단위 시간 (분)
        int perFee = fees[3]; // 단위 시간당 요금

        // 입차 기록과 출차 기록을 저장할 맵 초기화
        Map<String, Integer> entryTimes = new HashMap<>();
        Map<String, Integer> totalParkingTimes = new HashMap<>();

        // records 배열 순회하여 입차와 출차 기록 분류
        for (String record : records) {
            String[] parts = record.split(" ");
            String time = parts[0];
            String carNumber = parts[1];
            String status = parts[2];

            if (status.equals("IN")) {
                entryTimes.put(carNumber, convertTimeToMinutes(time));
            } else if (status.equals("OUT")) {
                int entryTime = entryTimes.remove(carNumber);
                int exitTime = convertTimeToMinutes(time);
                int parkingTime = exitTime - entryTime;
                totalParkingTimes.put(carNumber, totalParkingTimes.getOrDefault(carNumber, 0) + parkingTime);
            }
        }

        // 출차 기록이 없는 차량 처리 (23:59 출차로 간주)
        for (String carNumber : entryTimes.keySet()) {
            int entryTime = entryTimes.get(carNumber);
            int exitTime = convertTimeToMinutes("23:59");
            int parkingTime = exitTime - entryTime;
            totalParkingTimes.put(carNumber, totalParkingTimes.getOrDefault(carNumber, 0) + parkingTime);
        }

        // 각 차량별 주차 요금 계산
        List<String> sortedCarNumbers = new ArrayList<>(totalParkingTimes.keySet());
        sortedCarNumbers.sort(Comparator.naturalOrder());

        int[] result = new int[sortedCarNumbers.size()];
        for (int i = 0; i < sortedCarNumbers.size(); i++) {
            int totalParkingTime = totalParkingTimes.get(sortedCarNumbers.get(i));
            result[i] = calculateParkingFee(totalParkingTime, basicTime, basicFee, perTime, perFee);
        }

        // 결과 출력
        for (int fee : result) {
            System.out.println(fee);
        }

        return result;
    }

    // 시간을 분 단위로 변환하는 유틸리티 메서드
    private static int convertTimeToMinutes(String time) {
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3));
        return hours * 60 + minutes;
    }

    // 주차 요금을 계산하는 메서드
    private static int calculateParkingFee(int totalParkingTime, int basicTime, int basicFee, int perTime, int perFee) {
        if (totalParkingTime <= basicTime) {
            return basicFee;
        } else {
            int additionalTime = totalParkingTime - basicTime;
            int additionalFee = ((additionalTime + perTime - 1) / perTime) * perFee; // 올림 처리
            return basicFee + additionalFee;
        }
    }
}
