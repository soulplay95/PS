/**
 * @문제 에디터_S3
 * @날짜 220215
 * @분류 자료 구조 / 스택 / 연결 리스트
 * @조건
 * # 초기 문자열 < 10만
 * # 1 <= 명령어의 개수 <= 50만
 * @풀이
 * # 특정 index 위치의 원소를 삽입/삭제 하기 용이한 LinkedList로 문자열을 구성한다.
 * # 명령어에 따라 커서 위치(인덱스)를 이동시킨다.
 * # ListIterator 사용
 * @comments
 * # O(M), M: 명령어 개수
 * # Java의 List에서 add, remove 메소드는 최악의 경우, O(n)의 시간복잡도를 가진다. 즉, head에서부터 해당 위치또는 인덱스까지 탐색해야 함
 * # ListIterator
 * - 기존 Iterator 인터페이스는 컬렉션 요소에 접근할 때 한 방향으로만 이동할 수 있었다.
 * - ListIterator는 컬렉션 요소의 대체, 추가, 인덱스 검색 등을 위한 작업에 양방향으로 이동하는 것을 지원
 */

import java.util.*;
import java.io.*;

public class BOJ_1406 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
//        solve();
        solve2();
    }

    private static void solve() throws Exception {
        LinkedList<Character> answer = new LinkedList<Character>();

        String input = br.readLine();
        int M = Integer.parseInt(br.readLine());

        // init
        // 초기 문자열 LinkedList에 add
        for (int i = 0, end = input.length(); i < end; i++) {
            answer.add(input.charAt(i));
        }
        ListIterator<Character> cursor = answer.listIterator(answer.size());

        // 명령어 수행
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();

            // 명령어에 따라 분기
            switch (command) {
                case "L":
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                    }
                    break;
                case "D":
                    if (cursor.hasNext()) {
                        cursor.next();
                    }
                    break;
                case "B":
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                        cursor.remove();
                    }
                    break;
                case "P":
                    Character c = st.nextToken().charAt(0);
                    cursor.add(c);
                    break;
            }
        }

        // StringBuilder로 문자열 구성하기
        for (char c : answer) {
            sb.append(c);
        }

        // print
        System.out.println(sb.toString());
    }

    // Stack을 활용한 풀이
    // cursor 기준 왼쪽 문자들을 leftStack에서, 오른쪽 문자들을 rightStack에서 관리한다.
    // 전체 문자열 순서 : (left 스택의 bottom -> top) (cursor) (right 스택의 top -> bottom)
    private static void solve2() throws Exception {
        String input = br.readLine();
        int M = Integer.parseInt(br.readLine());
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        // init
        for (int i = 0, end = input.length(); i < end; i++) {
            left.push(input.charAt(i));
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();

            // 명령어에 따라 분기
            switch (command) {
                case "L":
                    // left 스택의 top 문자(커서 기준 바로 왼쪽 문자)를 right 스택으로 push
                    if (!left.isEmpty()) {
                        right.push(left.pop());
                    }
                    break;
                case "D":
                    if (!right.isEmpty()) {
                        left.push(right.pop());
                    }
                    break;
                case "B":
                    if (!left.isEmpty()) {
                        left.pop();
                    }
                    break;
                case "P":
                    Character c = st.nextToken().charAt(0);
                    left.push(c);
                    break;
            }
        }

        // left 스택에 있는 모든 문자를 pop하여 right 스택에 push
        while (!left.isEmpty()) {
            right.push(left.pop());
        }

        // StringBuilder로 문자열 구성하기
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }

        // print
        System.out.println(sb.toString());
    }

}
