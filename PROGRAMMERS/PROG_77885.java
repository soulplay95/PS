package programmers; /**
 * @문제
 * @날짜 220428
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PROG_77885 {

    // 1. 짝수인 경우
    // 마지막 비트가 0이므로, +1만 해주면 비트가 1개 다른 가장 작은 수가 된다.
    // 2. 홀수인 경우
    // 2-1. 모든 비트가 1인 경우
    // +1한 수부터 시작하므로 모든 비트가 다른 수부터 시작한다. ex) 11111 => 100000
    // 이 때, +1한 수의 최상위 비트는 무조건 1로써 다르므로 나머지 비트를 모두 1로 만들어 1개 차이가 나도록 하거나 두 번째 비트를 제외한 나머지 비트를 1로 만들어 2개 차이가 나도록 한다.
    // 당연히 더 작은 수는 두 번째 비트(최상위 비트의 다음 비트)가 0이 되는 2개 차이가 나는 수다.
    // 2-2. 모든 비트가 1이 아닌 경우
    // +1을 하면 최하위 비트에 제일 가까운 0이 1이 되고 그 뒤의 비트는 0이 된다.
    // 즉, 최하위 비트에 제일 가까운 0이 있는 위치부터 최하위 비트까지의 길이 만큼 개수 차이가 난다.
    // 이때도 마찬가지로 개수 차이가 2가 되도록 최하위 비트부터 1로 채운다.
    public long[] solution(long[] numbers) {
        // init
        long[] answer = numbers.clone();

        for (int i = 0; i < numbers.length; i++) {
            answer[i]++;
            answer[i] += (answer[i] ^ numbers[i]) >> 2; // answer[i] ^ numbers[i]: 비트가 다른 개수 차이 만큼의 길이를 가진 모든 비트가 1인 수, >> 2: 앞의 두 비트를 제외한 나머지 비트를 기존 수에 더해줌으로써 비트 개수 차이가 2가 되게 한다.
        }

        return answer;
    }

}
