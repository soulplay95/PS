/**
 * @문제 풍선 터뜨리기_S3
 * @날짜 210901
 * @분류 
 * @조건
 * # 1 <= N <= 1000
 * @풀이
 * # 리스트의 get, remove 인덱스로 시뮬레이션
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2346 {

	static class Balloon {
		int num, next;

		public Balloon(int num, int next) {
			this.num = num;
			this.next = next;
		}
	}

	static int N;
	static List<Balloon> list;
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// init
		list = new ArrayList<>();
		sb = new StringBuilder();
		for (int n = 1; n <= N; n++) {
			list.add(new Balloon(n, sc.nextInt()));
		}
		sc.close();

		solve();

		// print
		System.out.println(sb.toString());
	}

	static void solve() {
		int index = 0; // 1번 풍선부터 시작

		while (list.size() > 1) {
			Balloon cur = list.get(index); // 현재 풍선
			int next = cur.next; // 종이에 적힌 수
			
			sb.append(cur.num).append(" "); // 풍선 번호
			list.remove(index); // 풍선 터뜨리기
			int size = list.size();

			if (next > 0) {
				// 오른쪽으로 이동 시 풍선을 터뜨린 후 이므로 next 1 감소
				next--;
			}

			// index 갱신
			index = (index + next) % size;
			if (index < 0) {
				index += size;
			}
		}

		// / by zero Exception 처리
		sb.append(list.remove(0).num);
	}
	
}
