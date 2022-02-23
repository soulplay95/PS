/**
 * @문제 파일 정리_S3
 * @날짜 220223
 * @분류
 * @조건
 * # 1 <= 파일 개수 (N) <= 5만
 * # 3 <= 각 파일 이름 길이 <= 100
 * @풀이
 * # 확장자를 사전순으로 정렬한다.
 * @comments
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도: O(N)
 * # 정답의 최대치: Integer
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
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        extensions = new String[N];

        for (int i = 0; i < N; i++) {
            String[] file = br.readLine().split("\\.");
            extensions[i] = file[1]; // 확장자만 담기
        }
    }

    static void solve() {
        Arrays.sort(extensions);
    }

    static void print() {
        String before = extensions[0];
        int counts = 1;

        for (int i = 1; i < N; i++) {
            if (extensions[i].equals(before)) {
                counts++;
            } else {
                sb.append(before).append(" ").append(counts).append("\n");
                counts = 1;
                before = extensions[i];
            }
        }
        sb.append(before).append(" ").append(counts).append("\n");

        System.out.println(sb.toString());
    }

}
