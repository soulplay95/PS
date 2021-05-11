/**
 * @문제 오큰수_G4
 * @날짜 210511
 * @분류 
 * @조건
 * N <= 100만
 * @풀이
 * 스택에 차례대로 넣기.
 * 넣은 횟수 count하면서 스택에 넣기
 * 넣을 수가 스택의 top에 있는 수와 비교해서 큰 수이면 큰수가 나올떄까지 pop and 넣을 수로 답 배열 채우기  
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_17298 {
	
	// input, output handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N;
	static int[] ans;
	static Stack<int[]> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		ans = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			push(n, Integer.parseInt(st.nextToken()));
		}
		
		// 마지막 원소 처리
		stack.pop();
		ans[N - 1] = -1;
		if (stack.size() > 0) { // 마무리 했는데 스택에 찌꺼기가 남아있으면
			while (!stack.isEmpty()) {
				ans[stack.pop()[1]] = -1;
			}
		}
		
		// 정답 배열 StringBuilder에 append
		for (int n = 0; n < N; n++) {
			sb.append(ans[n]).append(" ");
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	static void push(int index, int num) {
		if (stack.isEmpty() || stack.peek()[0] >= num) { // 스택이 비었거나, top이 넣으려는 수보다 크거나 같으면
			stack.push(new int[] {num, index}); // 스택에 넣기
		} else {
			while (!stack.isEmpty() && stack.peek()[0] < num) { // pop할때마다 스택의 top이 작으면 계속 반복
				ans[stack.pop()[1]] = num; // pop
			}
			stack.push(new int[] {num, index});
		}
		
		return;
	}

}