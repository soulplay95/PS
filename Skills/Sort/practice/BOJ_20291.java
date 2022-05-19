package algorithm.sort.practice;

/**
 * @문제 파일 정리 [S3]
 * @날짜 220520
 * @분류 자료 구조 / 문자열 / 정렬 / 파싱 / 해시
 * @조건
 * # 1 <= N <= 5만
 * @풀이
 * # 정렬
 * @comments
 * # 정답의 최대치: Integer => max 5만
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_20291 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static String[] extensions;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        extensions = new String[N];

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("\\.");
            extensions[i] = split[1];
        }
    }

    static void solve() {
        Arrays.sort(extensions);

        for (int i = 0; i < N;) {
            int sameCounts = 1;
            int j = i + 1;
            for (; j < N; j++) {
                if (extensions[j].equals(extensions[i])) {
                    sameCounts++;
                } else {
                    break;
                }
            }

            sb.append(extensions[i]).append(" ").append(sameCounts).append("\n");

            i = j;
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}
