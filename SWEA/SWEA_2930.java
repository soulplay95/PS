/**
 * @문제 힙 D3
 * @날짜 210711
 * @분류 
 * @풀이
 * 힙 구현
 * @comment
 * new PriorityQueue<>(Comparator.reverseOrder());
 */

import java.util.*;
import java.io.*;

public class SWEA_2930 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');

			N = Integer.parseInt(br.readLine());
			
			// 최대힙 - 내림차순 --> 정렬의 방향을 바꿔주자.
			PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 내림차순 => 최대힙
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");
				if (st.nextToken().equals("1")) {
					pq.offer(Integer.parseInt(st.nextToken()));
				} else {
					// pq에서 빼려는데 값이 없으면 --> -1 반환
					sb.append(pq.isEmpty() ? -1 : pq.poll()).append(' ');
				}
			}
			
			// TC append
			sb.append('\n');
		}
		
		// print
		System.out.println(sb.toString());
	}

}
