package org.example.first.day2;

import java.util.Arrays;

public class CountQuadCompression {

    static int[] result = {};
    static int[][] grid = {
            {1, 1, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
    };

    public static void main(String[] args) {

        int size = 2;
        // result 배열 크기를 grid 배열의 길이로 초기화
        result = new int[grid.length];

        dp(0, 0, size, grid);

        System.out.println("Result: " + Arrays.toString(result));
    }

    /**
     * Step 1 - 모든 수 확인
     * x, y 시작지점에서부터 size 까지 조회
     * 매개변수로 전달된 2중 배열의 x, y 값을 바탕으로 2중 배열을 교차 검증하는 로직
     * size 는 2중 배열의 크기, 즉 배열의 갯수, 4x4 = size 4
     * 이 호출에서 size는 4로 설정되어 (0, 0)에서 시작하는 4x4 영역을 처리합니다.
     * 이후 재귀적으로 영역을 분할하면서 각 하위 영역에 대해 같은 크기의 size를 사용하여 처리할 수 있습니다.
     * 모두 일치하면 true, 아니면 false 반환
     */
    private static boolean checkSameValue(int[][] grid, int x, int y, int size) {
        // (x, y)의 값을 시작점으로 하여 size 범위 내의 모든 값을 조회
        int target = grid[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (grid[i][j] != target) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Step 2 - 실질적인 분할 정복을 수행하는 함수인 DP
     * 시작지점의 x, y 값과 범위의 size, 그리고 배열 grid 을 매개 변수로 받아온다.
     * checkSameValue() 를 통해 모든 수가 같은 값인지 확인한다.
     * 만약 모든 수가 같은 값이라면 result 배열에서 해당하는 index 값을 증가시킨다.
     * 그리고 4등분으로 구분된 영역에 dp를 재귀 함수로 호출한다.
     */
    private static void dp(int x, int y, int size, int[][] grid) {
        // 현재 영역이 압축 가능하다면, result 배열에 값 추가하고 반환
        if (checkSameValue(grid, x, y, size)) {
            // 모든 영역의 값이 같다면, 하나의 값으로 압축 가능
            result[grid[x][y]]++;
            return;
        }

        // 각 사분면에 대해 재귀적으로 호출
        dp(x, y, size / 2, grid); // 좌측 상단 사분면
        dp(x, y + size / 2, size / 2, grid); // 좌측 하단 사분면
        dp(x + size / 2, y, size / 2, grid); // 우측 상단 사분면
        dp(x + size / 2, y + size / 2, size / 2, grid); // 우측 하단 사분면
    }
}
