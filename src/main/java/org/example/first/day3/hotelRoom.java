package org.example.first.day3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class hotelRoom {


    public static void main(String[] args) {
        String[][] bookTime = new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};


        int solution = solution(bookTime);
        System.out.println(solution);

    }


    public static int solution(String[][] book_time) {
        int answer = 0;


        /**
         * 1.예약 시간을 분으로 변환하여 이벤트 리스트 생성
         * 시간 -> 분 변환　/ 시작 시간과 종료 시간 분리
         */
        int n = book_time.length; // 2중 배열의 length -> 배열의 갯수

        int[][] times = new int[n][2]; // 에약 시간과 종료 시간을을 분으로 변환화여 저장하는 2차원 배열
        for (int i = 0; i < n; i++) {
            // i 행의 0번
            times[i][0] = toMinutes(book_time[i][0]); // 시간 시간을 분으로 변환하여 저장.
            // i 행의 1번
            times[i][1] = toMinutes(book_time[i][1]) + 10; // 종료 시간을 분으로 변환하여 저장
        }

        /**
         * 2. 예약 시간을 시작 시간 기준으로 오름차순 정렬
         */
//        Arrays.sort(times,(a,b)-> a[0] - b[0]);
        Arrays.sort(times, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        /**
         * 3. 최소 객실 수 계산
         */
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] time : times) {

            pq.add(time[1]);

            if (!pq.isEmpty() && pq.peek() <= time[0]) {
                pq.poll();
            }

        }

        answer = pq.size();

        return answer;
    }

    // "HH::MM" 형태의 시간을 분으로 변환하는 메서드
    private static int toMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours * 60 + minutes;
    }


}
