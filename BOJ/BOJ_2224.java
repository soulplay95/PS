package study.w220214;

/**
 * @문제 명제 증명_G5
 * @날짜 220213
 * @분류 그래프 이론, 플로이드-와샬
 * @조건
 * # 1 <= 참인 명제 개수 <= 1만
 * @풀이
 * # 조건 명제를 나타내는 Custom Class를 만든다.(전건, 후건을 포함한)
 * # 입력을 받으면서 정답 리스트에 add하고, 인접 리스트를 구성한다.
 * - 인덱스 번호는 명제를 나타내는 대소문자에 -'A' 연산하여 구한다.
 * # 인접 리스트의 첫 번째 원소부터 depth 2까지 dfs한다.
 * - 탐색 과정에서 from(전건)과 같은 문자는 탐색하지 않는다.
 * - from(전건), to(후건)을 정답 리스트에 add한다.
 * # 정답 리스트를 정렬하고 중복된 명제는 버린다.
 * @comments
 * # ASCII CODE
 * - A : 65
 * - Z : 65 + 26 - 1 = 90
 * - a : 97
 * - z : 97 + 26 - 1 = 122
 */

import java.util.*;
import java.io.*;

public class BOJ_2224 {

    // 조건 명제를 나타내는 클래스
    private static class Implication implements Comparable<Implication> {
        int from, to; // 전건, 후건을 나타내는 인덱스 번호(알파벳 대소문자 - 'A'한 값)

        public Implication(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Implication o) {
            if (this.from == o.from) {
                return Integer.compare(this.to, o.to);
            }
            return Integer.compare(this.from, o.from);
        }

        @Override
        public boolean equals(Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (!(o instanceof Implication)) {
//                return false;
//            }

            Implication other = (Implication) o;

            return this.from == other.from &&
                    this.to == other.to;
        }
    }

    private static int N;
    private static ArrayList<Integer>[] adjList; // 인접 리스트
    private static ArrayList<Implication> answer; // 정답 리스트
    private static StringBuilder sb = new StringBuilder();

    private static boolean[][] dp = new boolean['z' - 'A' + 1]['z' - 'A' + 1];

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        // init
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList['z' - 'A' + 1]; // A ~ z
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        answer = new ArrayList<Implication>();

        // 참인 명제들을 입력 받으면서 정답 리스트에 포함시키고, 인접 리스트를 구성한다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = st.nextToken().charAt(0) - 'A'; // 전건
            st.nextToken();
            int to = st.nextToken().charAt(0) - 'A'; // 후건

            // P => P의 꼴을 제외한 명제들만 처리
            if (from != to) {
                // 정답 리스트에 포함 시킴
                answer.add(new Implication(from, to));

                // 인접 리스트 구성
                adjList[from].add(to);

                dp[from][to] = true;
            }
        }

//        solve();
//        solve2();
        solve3();

        // print
        System.out.print(sb.toString());
    }

    private static void solve() {
        boolean[][] dp = new boolean[adjList.length][adjList.length];
        // 모든 인접 리스트에서 depth 2까지 dfs하여 정답 리스트에 add
        for (int from = 0; from < adjList.length; from++) {
            for (int mid : adjList[from]) {
                for (int to : adjList[mid]) {
                    if (from != to) { // P => P의 꼴이 아니면
                        Implication newAnswer = new Implication(from, to);

                        if (!answer.contains(newAnswer)) { // 중복되지 않으면
                            // 정답 리스트에 추가
                            answer.add(new Implication(from, to));
                        }
//                        dp[from][to] = true;
                        answer.add(new Implication(from, to));
                    }
                }
            }
        }

        for (int from = 0; from < adjList.length; from++) {
            for (int to = 0; to < adjList.length; to++) {
                if (from == to) {
                    continue;
                }
                if (dp[from][to]) {
                    answer.add(new Implication(from, to));
                }
            }
        }

        // 정렬
        Collections.sort(answer);

//        sb.append(answer.size()).append("\n");
        // StringBuilder에 출력 형식대로 append

        int counts = 0;
        int beforeFrom = -1;
        int beforeTo = -1;
        for (Implication implication : answer) {
            if (implication.from == beforeFrom && implication.to == beforeTo) {
                continue;
            }
            sb.append((char)(implication.from + 'A')).append(" => ").append((char)(implication.to + 'A')).append("\n");
            beforeFrom = implication.from;
            beforeTo = implication.to;
            counts++;
        }
        System.out.println(counts);
    }

    private static void solve2() {
        for (int from = 0; from < adjList.length; from++) {
            dfs(from, from, 0);
        }

        // 정렬
        Collections.sort(answer);

//        sb.append(answer.size()).append("\n");
        // StringBuilder에 출력 형식대로 append

        int counts = 0;
        int beforeFrom = -1;
        int beforeTo = -1;
        for (Implication implication : answer) {
            if (implication.from == beforeFrom && implication.to == beforeTo) {
                continue;
            }
            sb.append((char)(implication.from + 'A')).append(" => ").append((char)(implication.to + 'A')).append("\n");
            beforeFrom = implication.from;
            beforeTo = implication.to;
            counts++;
        }
        System.out.println(counts);
    }

    private static void dfs(int first, int from, int depth) {
        if (depth >= 2) {
            answer.add(new Implication(first, from));
        }

        for (int to : adjList[from]) {
            if (first != to) {
                dfs(first, to, depth + 1);
            }
        }
    }

    private static void solve3() {
        int size = 'z' - 'A' + 1;
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                if (i == k) continue;
                for (int j = 0; j < size; j++) {
                    if (j == i || j == k) continue;

                    if (dp[i][k] && dp[k][j]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        int counts = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (dp[i][j]) {
                    counts++;
                    sb.append((char)(i + 'A')).append(" => ").append((char)(j + 'A')).append("\n");
                }
            }
        }

        System.out.println(counts);
    }

}