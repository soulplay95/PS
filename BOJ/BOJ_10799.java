/**
 * @문제 쇠막대기_S3
 * @날짜 220215
 * @분류 자료 구조 / 스택
 * @조건
 * # 괄호 문자 개수 <= 10만
 * @풀이
 * # 입력으로 주어진 괄호 표현을 순서대로 Stack에 push한다.
 * - 직전에 push한 괄호를 저장한다.
 * - 현재 push하려는 괄호가 닫는 괄호 이면 2가지 case로 나뉜다.
 * 1. 직전에 push한 괄호가 여는 괄호인 경우, 레이저 발사 => 직전의 여는 괄호를 pop한 후, 스택에 남아있는 괄호 개수(레이저에 의해 잘리게되는 쇠 막대기의 왼쪽 부분)를 정답에 누적한다.
 * 2. 직전에 push한 괄호가 닫는 괄호인 경우, 최상단의 쇠막대기가 끝나는 지점 => 직전의 여는 괄호를 pop하고, 정답에 +1 (최상단의 막대가 레이저에 의해 잘리게되는 오른쪽 부분)
 * @comments
 * # O(n), n: 입력으로 주어지는 괄호 개수
 */

import java.util.*;
import java.io.*;

public class BOJ_10799 {

    private static String input; // 입력으로 주어지는 괄호 표현
    private static int totalCutOffPiecesCounts = 0; // 정답 - 잘려진 조각의 총 개수
    private static char previousBracket; // 이전 괄호

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

        solve();

        // print
        System.out.println(totalCutOffPiecesCounts);
    }

    private static void solve() {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0, end = input.length(); i < end; i++) {
            Character bracket = input.charAt(i);

            if (bracket == '(') {
                // 스택에 push한다.
                stack.push(bracket);
            } else if (bracket == ')') {
                stack.pop();

                if (previousBracket == '(') {
                    // 레이저 발사 => 레이저 시작을 나타내는 여는 괄호를 스택에서 pop한 후, 스택의 사이즈만큼을 정답에 누적
                    totalCutOffPiecesCounts += stack.size();
                } else if (previousBracket == ')') {
                    // 최상단의 막대가 끝나는 지점 => 잘려진 최상단의 막대 오른쪽 부분을 정답에 누적
                    totalCutOffPiecesCounts += 1;
                }
            }

            previousBracket = bracket; // 이전 괄호 갱신
        }
    }

}
