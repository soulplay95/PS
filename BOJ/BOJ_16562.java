/**
 * @문제 친구비_G3
 * @날짜 210720
 * @분류
 * @조건
 *
 * @풀이
 *
 * @comment
 *
 */

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_16562 {
	
	static int N, M, K;
	static int parents[]; // 부모
	static int[] charge; // 비용
	static int ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		charge = new int[N + 1]; // 1~
		parents = new int[N + 1]; // 1~
		for (int i = 1; i <= N; i++) {
			charge[i] = sc.nextInt();
		}
		Arrays.fill(parents, -1); // 부모 배열을 -1로 채움
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			union(from, to);
		}
		ans = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == -1) {
				ans += charge[i];
			}
		}
		
		if (K < ans) { // 비용 초과하면
			System.out.println("Oh no");
		} else {
			System.out.println(ans);
		}
		
		sc.close();

	}
	
	static int findSet(int a) {
		// 내가 그 집합의 대표자이면
		if (parents[a] < 0) return a;
//		return findSet(parents[a]); // path compression 전
		return parents[a] = findSet(parents[a]); // path compression 후
	}
	
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return; // 이미 같은 조직이므로 합칠 수 없음.
		if (charge[aRoot] > charge[bRoot]) {
			parents[aRoot] = bRoot;
		} else {
			parents[bRoot] = aRoot;
		}
		return;
	}

}
