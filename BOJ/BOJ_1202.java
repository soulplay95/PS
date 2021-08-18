/**
 * @문제 보석 도둑_G2
 * @날짜 210819
 * @분류 자료 구조, 그리디, 정렬, 우선순위 큐
 * @조건
 * # 1 <= N, K <= 30만
 * @풀이
 * # 무게 제한이 작은 가방부터 이 가방에 넣을 수 있는 보석들 중 제일 비싼 보석을 넣는다.
 * # 가방에 넣을 수 있는 보석들 중 최대 가격의 보석을 찾기 위해 pq 사용
 * @comment
 * # 보석보다 가방 개수가 더 많은 경우 조심
 */

import java.io.*;
import java.util.*;

public class BOJ_1202 {

	static class Jewelry implements Comparable<Jewelry> {
		int m, v; // 무게, 가격

		public Jewelry(int m, int v) {
			this.m = m;
			this.v = v;
		}

		@Override
		public int compareTo(Jewelry o) {
			return Integer.compare(this.m, o.m);
		}
	}

	static int N, K, bags[];
	static Jewelry[] jewelries;
	static long ans;
	static PriorityQueue<Integer> pq;
	static int index;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// init
		bags = new int[K];
		jewelries = new Jewelry[N];
		ans = 0L;
		pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // 최대 힙
		index = 0;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewelries[n] = new Jewelry(m, v);
		}
		for (int k = 0; k < K; k++) {
			bags[k] = Integer.parseInt(br.readLine());
		} // input end

		solve();

		// print
		System.out.println(ans);
	}

	static void solve() {
		// 1. 가방과 보석 배열을 각각 무게제한, 무게 기준 오름차순 정렬한다.
		Arrays.sort(bags);
		Arrays.sort(jewelries);

		// 2. 무게 제한이 작은 가방부터 그 가방에 들어갈 수 있는 보석들의 가격을 pq에 집어넣는다.(가격 기준 최대 힙)
		for (int k = 0; k < K; k++) {
			ans += getMaxPrice(bags[k]);
		}
	}

	static int getMaxPrice(int limit) {
		while (index < N && jewelries[index].m <= limit) {
			// pq에 들어간 보석들은 제외(보석 배열의 인덱스로 처리)
			pq.offer(jewelries[index++].v);
		}

		// 3. pq에서 poll하여 최대 가격에 누적한다.
		if (!pq.isEmpty()) {
			return pq.poll();
		}

		return 0;
	}

}
