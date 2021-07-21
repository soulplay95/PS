/**
 * @문제 종교
 * @날짜 210721
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUNGOL_1863 {
	static int N, M;
	static int[] parents, R; // 부모, 랭크
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1]; // 1~
		R = new int[N + 1];
		
		make();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from, to);
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == i) {
				ans++;
			}
		}
		
		// print
		System.out.println(ans);
	}
	
	// 크기가 1인 단위 집합을 만든다.
	static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	// 대표자를 찾아서 반환해주는 메소드
	private static int find(int x) {
		if (x == parents[x]) return parents[x]; // 이미 대표자이면 그냥 리턴
		else return parents[x] = find(parents[x]); // 부모 타고 올라가면서 루트(대표자) 찾음, path compression
	}
	
	// 두 매개변수가 속한 집합을 합쳐주는 메소드
	private static void union(int x, int y) {
		int px = find(x); // x의 대표자
		int py = find(y); // y의 대표자
		if (px == py) return; // 이미 같은 대표자를 같는(= 같은 소속이면) return
		
		// rank가 작은 집합을 rank가 크거나 같은 쪽에 붙인다
		if (R[px] >= R[py]) {
			parents[py] = px; // y가 속한 집합을 x가 속한 집합에 붙임
			R[px] += R[py]; // 붙였으므로 rank 누적
		} else {
			parents[px] = py;
			R[py] += R[px];
		}
	}
	
}