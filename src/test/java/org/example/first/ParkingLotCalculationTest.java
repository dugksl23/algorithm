package org.example.first;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotCalculationTest {


    @Test
    void calculateParkingLot() {
        int[] fees = {180, 5000, 10, 600};

        String[] records = {
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"};


        // HashMap 초기화
        Map<String, List<String>> inMap = new HashMap<>();
        Map<String, List<String>> outMap = new HashMap<>();


        // 주차 요금
        int bHours = fees[0];
        int bPrice = fees[1];
        int extraMinutes = fees[2];
        int pricePerExtraMinutes = fees[3];

        // in, out 별로 car, time 분류
        for (String record : records) {
            String[] split = record.split(" ");
            String time = split[0];
            String carNumber = split[1];
            String status = split[2];

            // 상태에 따라 다른 HashMap에 저장
            if (status.equals("IN")) {
                inMap.computeIfAbsent(carNumber, k -> new ArrayList<>()).add(time);
            } else if (status.equals("OUT")) {
                outMap.computeIfAbsent(carNumber, k -> new ArrayList<>()).add(time);
            }

        }

        // 출차된 내역이 없다면, 23:59로 규정
        for (String carNumber : inMap.keySet()) {
            List<String> inList = inMap.get(carNumber);
            System.out.println("inList size :" + inList.size());
            List<String> outList = outMap.get(carNumber);
            System.out.println("outList size :" + outList.size());
            if (inList.size() > outList.size()) {
                outList.add("23:59");
            }
        }

        // IN Map 출력
        System.out.println("IN Map:");
        for (Map.Entry<String, List<String>> entry : inMap.entrySet()) {
            System.out.println("Car Number: " + entry.getKey() + ", Times: " + entry.getValue());
        }

        // OUT Map 출력
        System.out.println("OUT Map:");
        for (Map.Entry<String, List<String>> entry : outMap.entrySet()) {
            System.out.println("Car Number: " + entry.getKey() + ", Times: " + entry.getValue());
        }


    }
}

