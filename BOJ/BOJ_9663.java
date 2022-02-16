/**
 * @문제 N-Queen_G5
 * @날짜 220217
 * @분류 완전탐색 / 백트래킹
 * @조건
 * # 1 <= N < 15
 * @풀이
 * # dfs + 백트래킹
 * @comments
 * #
 */

import java.util.*;

public class BOJ_9663 {

    private static int totalCaseCounts = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        dfs(N, new ArrayList<Integer>(), 0);

        // print
        System.out.println(totalCaseCounts);
    }

    private static void dfs(Integer N, ArrayList<Integer> selectedColumns, Integer currentRow) {
        // 기저 조건
        if (currentRow == N) {
            totalCaseCounts++;
            return ;
        }

        for (int column = 0; column < N; column++) {
            if (isAvailable(selectedColumns, column)) {
                selectedColumns.add(column);
                dfs(N, selectedColumns, currentRow + 1);
                selectedColumns.remove(selectedColumns.size() - 1); // 가지치기
            }
        }
    }

    private static boolean isAvailable(ArrayList<Integer> selectedColumns, Integer currentColumn) {
        // 이전에 선택된 행 전부를 순회하면서 수직, 대각선 조건을 따져본다.
        for (int row = 0; row < selectedColumns.size(); row++) {
            Integer column = selectedColumns.get(row); // 현재 행의 컬럼
            if (currentColumn == column || Math.abs(currentColumn - column) == selectedColumns.size() - row) { // 수직 또는 대각선 조건을 만족하지 못하면
                return false;
            }
        }

        return true;
    }

}
