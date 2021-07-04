/**
 * @문제 [모의 SW 역량테스트] 벌꿀채취
 * @날짜 210704
 * @분류 
 * @풀이
 * map 상에서 M크기의 영역을 잡을 수 있는 경우의 수 각각에서 가능한 최대 이익을 구한다.
 * 겹치지 않는 영역의 2개 조합 중 최대를 구한다.
 * @comment
 * M이 1일때는 N이 10이므로 100C2 = 4950
 * M이 5일때는 한 행에 (1~5, 2~6, ..., 5~10) 6가지이므로 60C2 = 1770
 * 즉, 충분히 조합으로 풀이 가능
 */

import java.util.*;
import java.io.*;

public class SWEA_2115 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	// M크기의 벌통 영역을 나타내는 클래스
	static class Area implements Comparable<Area> {
		int startR, startC; // 시작 r, c좌표
		int max; // 최대 수익

		public Area(int startR, int startC, int max) {
			this.startR = startR;
			this.startC = startC;
			this.max = max;
		}

		@Override
		public int compareTo(Area o) {
			return Integer.compare(o.max, this.max); 
		}
	}
	
	static int N, M, C;
	static int[][] map;
	static List<Area> list;
	static int maxPowerSum; // 최대 이익

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			// -- input
			
			list = new ArrayList<>();
			
			// M크기의 영역을 잡을 수 있는 모든 경우를 구해서 area list에 추가한다.
			for (int r = 0; r < N; r++) {
				for (int c = 0, end = N - M; c <= end; c++) {
					list.add(getArea(r, c));
				}
			}
			
			// 비용 순으로 list 내림차순 정렬
			Collections.sort(list);
			
			// TC append
			sb.append('#').append(t).append(' ').append(getMax()).append('\n');
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	static int getMax() {
		int ans = 0;
		
		for (int i = 0; i < list.size(); i++) {
			Area a = list.get(i);
			for (int j = i + 1; j < list.size(); j++) {
				Area b = list.get(j);
				if (a.startR != b.startR || Math.abs(a.startC - b.startC) >= 3) {
					ans = a.max + b.max;
					return ans;
				}
			}
		}
		
		return ans;
	}

	// 매개변수 r, c를 시작점으로 하는 M크기 영역에 최대 이익을 담은 Area 객체를 반환하는 메소드
	static Area getArea(int r, int c) {
		Area area = new Area(r, c, 0);
		
		// 부분집합을 이용해 C를 넘지 않는 최대 이익을 구한다.
		maxPowerSum = 0; // 초기화
		subs(area, 0, 0, 0);
		area.max = maxPowerSum;
		
		return area;
	}
	
	static void subs(Area area, int cnt, int sum, int powerSum) { // powerSum : 선택된 원소들의 제곱의 합
		if (cnt == M) {
			maxPowerSum = Math.max(maxPowerSum, powerSum);
			return;
		}
		
		int amount = map[area.startR][area.startC + cnt];
		// 벌꿀 양이 C를 안넘으면 선택
		if (sum + amount <= C) {
			int power = (int)Math.pow(amount, 2);
			subs(area, cnt + 1, sum + amount, powerSum + power);
		}
		// 비선택
		subs(area, cnt + 1, sum, powerSum);
	}
	
}
