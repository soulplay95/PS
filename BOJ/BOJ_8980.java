package study.week37;

/**
 * @문제 택배_G3
 * @날짜 211004
 * @분류 그리디, 정렬
 * @조건
 * # 2 <= 마을의 수 N <= 2000
 * # 1 <= 트럭의 용량 C <= 10000
 * # 1 <= 정보 개수 M <= 10000
 * # 1 <= 보내는 박스 개수 <= 10000
 * @풀이
 * # 박스를 오래 들고 있으면서 생길 수 있는 손해를 최소화하기 위해 받는 마을 순으로 오름차순 정렬한다.
 * # 최대한 담을 수 있는만큼 담는다. => [보내는 마을, 받는 마을) 경로 중 각 마을에서 보낼 수 있는 최대 박스 개수의 최소값만큼
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_8980 {

	static class DeliveryInfo implements Comparable<DeliveryInfo> {
		int from, to, box;

		public DeliveryInfo(int from, int to, int box) {
			this.from = from;
			this.to = to;
			this.box = box;
		}

		@Override
		public int compareTo(DeliveryInfo o) {
			return Integer.compare(this.to, o.to); // 받는 마을번호 기준 오름차순 정렬
		}
	}

	static int N, C, M, ans;
	static DeliveryInfo[] info;
	static int[] counts; // 각 마을에서 보낼 수 있는 최대 박스 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		// init
		ans = 0;
		info = new DeliveryInfo[M];
		counts = new int[N];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int box = Integer.parseInt(st.nextToken());
			info[m] = new DeliveryInfo(from, to, box);
		}

		solve();

		// print
		System.out.println(ans);
	}

	static void solve() {
		// 1. 받는 마을 번호 기준 오름차순 정렬
		Arrays.sort(info);

		// 2. 각 마을에서 보낼 수 있는 최대 박스 개수를 트럭의 용량으로 초기화
		Arrays.fill(counts, C);

		// 3. 경로 내에서 실을 수 있는 최대 박스를 싣는다.
		for (int i = 0; i < M; i++) {
			ans += load(info[i]);
		}
	}

	static int load(DeliveryInfo cur) {
		int from = cur.from;
		int to = cur.to;
		int box = cur.box;
		int min = 10001;

		// 실을 수 있는 최대 용량을 구한다.
		for (int i = from; i < to; i++) {
			min = Math.min(min, counts[i]);
		}

		// 최대한 싣기 위해 실을 수 있는 최대 용량과 현재 배송 정보의 박스 개수를 비교한다.
		int loadCounts = min - box >= 0 ? box : min;

		// counts 배열 업데이트
		for (int i = from; i < to; i++) {
			counts[i] -= loadCounts;
		}

		return loadCounts;
	}

}
