package study.week44;

/**
 * @문제 토너먼트 만들기_G5
 * @날짜 211128
 * @분류 그리디
 * @조건
 * # 1 <= 사람 수 (N) <= 256
 * @풀이
 * # 랭킹이 제일 낮은 사람부터 경기를 치뤄(이때 양 옆의 사람 중 랭킹 차이가 적은 쪽의 사람과 경기) 빠르게 떨군다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2262 {

	private static int N;
	private static ArrayList<Integer> rank;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// init
		rank = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			rank.add(sc.nextInt());
		}

		// print
		System.out.println(solve());

		sc.close();
	}

	private static int solve() {
		int ans = 0;
		int max = N;

		// 1명 남기 전까지 경기
		while (rank.size() != 1) {
			int targetIndex = rank.indexOf(max); // 랭킹이 가장 낮은 선수의 index를 찾는다.
			int leftRank = (targetIndex > 0) ? rank.get(targetIndex - 1) : -255;
			int rightRank = (targetIndex < rank.size() - 1) ? rank.get(targetIndex + 1) : -255;

			// 랭크 차이가 적은 선수와 경기 후 랭크 차이값 누적
			if (max - leftRank < max - rightRank) {
				ans += max - leftRank;
			} else {
				ans += max - rightRank;
			}

			rank.remove(targetIndex);
			max--;
		}

		return ans;
	}

}
