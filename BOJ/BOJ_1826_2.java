package study.week36;

/**
 * @문제 연료 채우기_G3
 * @날짜 210918
 * @분류 
 * @조건
 * # 1 <= 주유소의 개수(N) = 10000
 * @풀이
 * # 현재 연료 양으로 갈 수 있는 주유소 중에 필요한 연료의 양(주유소 위치 - 현재 위치 - 주유쇼에서 얻을 수 있는 연료 양)
 * 이 최소가 되는 지점을 선택한다.
 * @comment
 * #
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1826_2 {

	static class Station {
		int pos, fuel;

		public Station(int pos, int fuel) {
			this.pos = pos;
			this.fuel = fuel;
		}
	}

	static int N, ans, dest, curFuel;
	static Station[] input;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		// init
		ans = 0;
		pq = new PriorityQueue<>(Collections.reverseOrder());
		input = new Station[N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int pos = Integer.parseInt(st.nextToken());
			int fuel = Integer.parseInt(st.nextToken());
			input[n] = new Station(pos, fuel);
		}
		st = new StringTokenizer(br.readLine(), " ");
		dest = Integer.parseInt(st.nextToken());
		curFuel = Integer.parseInt(st.nextToken());

		Arrays.sort(input, new Comparator<Station>() {
			@Override
			public int compare(Station o1, Station o2) {
				return Integer.compare(o1.pos, o2.pos);
			}
		});
		ans = solve();

		// print
		System.out.println(ans);
	}

	static int solve() {
		int i = 0;

		while (curFuel < dest) {
			// 현재 연료 양으로 갈 수 있는 주유소 pq에 넣기
			while (i < N && curFuel >= input[i].pos) {
				pq.offer(input[i].fuel);
				i++;
			}

			if (pq.isEmpty()) {
				return -1;
			}

			curFuel += pq.poll();
			ans++;
		}

		return ans;
	}

}
