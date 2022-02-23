/**
 * @문제 화살표 그리기_S4
 * @날짜 220223
 * @분류 완전 탐색 / 정렬
 * @조건
 * # 0 <= 위치 (x) <= 10만
 * # 2 <= 점 개수 (N) <= 5000
 * @풀이
 * # 정렬
 * - 색깔 번호 순으로 오름차순 정렬(같은 색끼리 뭉치게 하기 위함)하고 색이 같으면 위치 순으로 오름차순 정렬한다. => O(NlogN)
 * - 앞에서부터 전체 원소를 순회하면서 양 옆으로 같은 색이면서, 더 짧은 거리를 계산하여 정답에 누적한다.
 * @comments
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도: O(N)
 * # 정답의 최대치: < 10만 * 5000 => Integer
 */

import java.io.*;
import java.util.*;

public class BOJ_15970 {

    static StringBuilder sb = new StringBuilder();

    static class Element implements Comparable<Element> {
        int pos, color; // 위치, 색깔 번호

        public Element(int pos, int color) {
            this.pos = pos;
            this.color = color;
        }

        @Override
        public int compareTo(Element o) {
            if (color == o.color) {
                return pos - o.pos;
            }
            return color - o.color;
        }
    }

    static int N, ans;
    static Element[] dots;

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
        ans = 0;
        dots = new Element[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int pos = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());
            dots[i] = new Element(pos, color);
        }
    }

    static void solve() {
        Arrays.sort(dots);

        for (int i = 0; i < N; i++) {
            ans += getMinDistance(i);
        }
    }

    static int getMinDistance(int i) {
        int leftDistance = 100000;
        int rightDistance = 100000;

        if (i > 0 && dots[i].color == dots[i - 1].color) {
            leftDistance = dots[i].pos - dots[i - 1].pos;
        }
        if (i < N - 1 && dots[i].color == dots[i + 1].color) {
            rightDistance = dots[i + 1].pos - dots[i].pos;
        }

        return Math.min(leftDistance, rightDistance);
    }

    static void print() {
        System.out.println(ans);
    }

}
