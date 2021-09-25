/**
 * @문제
 * @날짜 210911
 * @분류
 * @조건
 * # 1 <= 화살 개수 n <= 10
 * # info : 10 ~ 0점까지 순서대로 담음
 * # 방법이 여러가지일 경우 가장 낮은 점수를 더 많이 맞힌 경우를 리턴
 * @풀이
 * # 11개(0~10점) 중 n개를 뽑은 경우의 수(중복 조합)에서 각 점수의 count와 점수를 계산한다.
 * @comment
 *
 */

package kakao2022;

import java.io.*;
import java.util.*;

public class P4 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(9, new int[] {0,0,1,2,0,1,1,1,1,1,1})));
	}

	static int[] result;
	static int maxScoreDiff;
	static int[] ryanInfo;
	static int[] answer;

	public static int[] solution(int n, int[] info) {
		// init
		result = new int[n];
		maxScoreDiff = 1;
		answer = new int[11];

		// 1. 11개(0 ~ 10점) 중 n개를 중복해서 뽑아(중복 조합) 점수를 비교한다.
		pick(n, 0, 0, info);

		// answer가 업데이트 되었는지 체크
		for (int i : answer) {
			if (i > 0) {
				return answer;
			}
		}

		return new int[] {-1};
	}

	static void pick(int cnt, int start, int depth, int[] info) {
		// 기저 조건(n개 다 뽑은 경우)
		if (cnt == 0) {
			// 2. 라이언 info 배열 구성
			ryanInfo = new int[11];
			for (int score : result) {
				ryanInfo[10 - score]++;
			}

			// 3. 점수 차 구하기
			int apeachScore = 0;
			int ryanScore = 0;

			for (int i = 0; i < 11; i++) {
				int aCounts = info[i]; // 어피치가 맞힌 개수
				int rCounts = ryanInfo[i]; // 라이언이 맞힌 개수

				if (aCounts == 0 && rCounts == 0) { // 둘다 못맞힌 경우
					continue;
				}

				if (info[i] >= ryanInfo[i]) { // 어피치가 맞힌 개수가 라이언보다 크거나 같다면
					// 어피치 점수 획득
					apeachScore += 10 - i;
				} else {
					ryanScore += 10 - i;
				}
			}

			int diff = ryanScore - apeachScore; // 점수 차
			if (diff >= maxScoreDiff) { // 기존 최대 점수차보다 클경우
				if (diff == maxScoreDiff) { // 점수가 같을 경우 낮은 개수 순으로 정답에 넣기
					for (int i = 10; i >= 0; i--) {
						if (answer[i] > ryanInfo[i]) { // 기존 정답이 맞으면
							return;
						}
					}
				}

				answer = ryanInfo.clone();
				maxScoreDiff = diff;
			}

			return;
		}

		// 0 ~ 10
		if (depth == 11) {
			return;
		}

		result[start] = depth;
		pick(cnt - 1, start + 1, depth, info); // 뽑기
		pick(cnt, start, depth + 1, info); // 안뽑기
	}

}
