/**
 * @문제 비밀번호 찾기_S4
 * @날짜 220117
 * @분류 자료 구조, 해시
 * @조건
 * # 1 <= 저장된, 찾으려는 사이트 수 <= 10만
 * @풀이
 * # 해싱
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_17219 {

    private static int N, M;
    private static HashMap<String, String> passwords;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        passwords = new HashMap<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            String url = st.nextToken();
            String password = st.nextToken();

            passwords.put(url, password);
        }

        for (int m = 0; m < M; m++) {
            String url = br.readLine();

            sb.append(passwords.get(url)).append("\n");
        }

        // print
        System.out.print(sb.toString());
    }

}
