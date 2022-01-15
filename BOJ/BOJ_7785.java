/**
 * @문제 회사에 있는 사람_S5
 * @날짜 220115
 * @분류 자료 구조, 해시
 * @조건
 * # 로그 수 <= 10^6
 * # 이름 길이 <= 5
 * @풀이
 * # O(1)로 검색할 수 있는 HashSet을 이용하여 회사에 있는 사람을 관리
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_7785 {

    private static int N;
    private static HashSet<String> peopleInCompany;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        // init
        peopleInCompany = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            String state = st.nextToken();

            if ("leave".equals(state)) {
                peopleInCompany.remove(name);
            } else {
                peopleInCompany.add(name);
            }
        }

        solve();

        // print
        System.out.print(sb.toString());
    }

    private static void solve() {
        ArrayList<String> names = new ArrayList<>();
        Iterator<String> it = peopleInCompany.iterator();
        while (it.hasNext()) {
            names.add(it.next());
        }

        Collections.sort(names, Collections.reverseOrder()); // 역순으로 정렬

        for (String name : names) {
            sb.append(name).append("\n");
        }
    }

}