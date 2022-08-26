package practice.sort;

/**
 * @문제 국영수 [S4]
 * @날짜 220826
 * @분류 정렬
 * @조건
 * 1 <= N <= 100_000
 * @풀이
 * # 정렬
 * @복잡도
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10825 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static Score[] scores;

    static class Score implements Comparable<Score> {
        String name;
        int korean, english, math;

        public Score(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Score other) {
            if (korean == other.korean) {
                if (english == other.english) {
                    if (math == other.math) {
                        return name.compareTo(other.name);
                    }
                    return Integer.compare(other.math, math);
                }
                return Integer.compare(english, other.english);
            }
            return Integer.compare(other.korean, korean);
        }
    }

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
        scores = new Score[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            scores[i] = new Score(name, korean, english, math);
        }
    }

    static void solve() {
        Arrays.sort(scores, 0, N);

        // append
        for (int i = 0; i < N; i++) {
            sb.append(scores[i].name).append("\n");
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}
