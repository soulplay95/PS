package study.week36;

/**
 * @문제 연료 채우기_G3
 * @날짜 210918
 * @분류 
 * @조건
 * # 1 <= 주유소의 개수(N) = 10000
 * @풀이
 * # 현재 연료 양으로 갈 수 있는 주유소 중에 필요한 연료의 양(주유소3 위치 - 현재 1위치 - 주유쇼에서 얻을 5수 있는 연료 양)
 * 이 최소가 되는 지점을 선택한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1826 {

	static class Station implements Comparable<Station> {
		int pos, fuel;

		public Station(int pos, int fuel) {
			this.pos = pos;
			this.fuel = fuel;
		}

		@Override
		public int compareTo(Station o) {
			return Integer.compare(this.pos, o.pos); // 거리 순 오름차순 정렬
		}
	}

	static int N, ans, dest, curFuel;
	static PriorityQueue<Station> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		// init
		ans = 0;
		pq = new PriorityQueue<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int pos = Integer.parseInt(st.nextToken());
			int fuel = Integer.parseInt(st.nextToken());
			pq.add(new Station(pos, fuel));
		}
		st = new StringTokenizer(br.readLine(), " ");
		dest = Integer.parseInt(st.nextToken());
		curFuel = Integer.parseInt(st.nextToken());

		ans = solve();

		// print
		System.out.println(ans);
	}

	static int solve() {
		int cur = 0; // 현재 위치

		// 현재 연료의 양으로 갈 수 있는 주유소 중 필요한 연료의 양이 최소가 되는 지점을 선택한다.
		while (true) {
			// 도착 여부 체크
			if (dest - cur <= curFuel) {
				return ans;
			}

			int nextPos = 0; // 다음 위치
			int nextFuel = 0; // 연료
			int min = Integer.MAX_VALUE; // 각 주유소 위치까지 가는데 필요한 연료의 양(음수이면 얻게 되는 연료의 양)

			// 현재 연료의 양으로 갈 수 있는 주유소 탐색
			Station station = null;
			while (!pq.isEmpty()) {
				Station s = pq.peek();

				// 1. 현재 연료의 양으로 갈 수 있는지 판단
				int distance = s.pos - cur; // 주유소 위치 - 현재 위치
				if (distance > curFuel) { // 현재 연료의 양으로 갈 수 없으면
					pq.offer(station);
					break;
				}

				// 2. 다음 주유소(없으면 도착 지점)에 갈 수 있는지 판단
				station = pq.poll();
				if (!check(station, cur)) {
					break;
				}

				int needs = s.pos - cur - s.fuel; // 필요한 연료의 양
				if (needs <= min) { // 최소값 갱신
					nextPos = s.pos;
					nextFuel = needs;
					min = needs;
				}
			}

			// 현재 연료의 양으로 어떤 주유소도 갈 수 없으면
			if (nextPos == 0) {
				return -1;
			}

			cur = nextPos;
			curFuel -= nextFuel;
			ans++;
		}
	}

	static boolean check(Station cur, int org) {
		int next = 0;

		if (pq.isEmpty()) {
			next = dest;
		} else {
			next = pq.peek().pos;
		}

		if (next - cur.pos <= curFuel + cur.fuel - (cur.pos - org)) { // 다음 주유소로 갈 수 있으면
			return true;
		}

		pq.offer(cur);
		return false;
	}

}
