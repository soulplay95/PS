/**
 * @문제 나무 재테크_G4
 * @날짜 210614
 * @분류 구현, 시뮬레이션
 * @조건
 * 로봇은 모든 칸에 대해 조사
 * 초기 양분은 모든 칸에 5씩
 * 한 칸에 여러 개의 나무가 심어져 있을 수도 있음
 * 봄 : 자신의 나이만큼 양분을 먹고, 나이가 1증가 => 한 칸에 여러 나무가 있을 시, 어린 나무부터 양분을 먹음. 양분이 부족하면 나무는 죽음
 * 여름 : 봄에 죽은 나무가 양분으로 변함. 죽은 나무의 나이를 2로 나눈 값이 양분으로 추가
 * 가을 : 나무가 번식(나이가 5의 배수만), 인접 8칸에 나이가 1인 나무가 생김
 * 겨울 : 전체 땅에 양분 추가
 * @풀이
 * 
 * @comment
 * Deque 메소드 섞어 쓸때 add, remove / offer, poll 서로 섞어 쓰지 말기
 * 따로 정렬 없이 덱의 앞에서부터 빼서 뒤에서 넣기
 */

import java.util.*;
import java.io.*;

public class BOJ_16235 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static class Tree {
		int r, c, age;
		
		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}
	
	static int N, M, K;
	static int[][] add; // 겨울에 양분 추가
	static int[][] nutrition; // 양분
	static Deque<Tree> trees; // 나무 리스트
	static List<Tree> deadTrees; // 죽은 나무 리스트
	static List<Tree> cloneTrees; // 번식하는 나무 리스트

	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상부터 시계방향
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		add = new int[N + 1][N + 1]; // 좌표 1부터 시작
		nutrition = new int[N + 1][N + 1];
		trees = new ArrayDeque<>();
		deadTrees = new LinkedList<>();
		cloneTrees = new LinkedList<>();
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				add[r][c] = Integer.parseInt(st.nextToken());
				// 양분 맵 초기화
				nutrition[r][c] = 5;
			}
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees.add(new Tree(r, c, age));
		} // input end
		
		solve();
		
		// print
		System.out.println(trees.size());
	}
	
	static void solve() {
		while (K-- > 0) {
			// 봄
			spring();
			
			// 여름
			summer(); 
			
			// 가을
			fall();
			
			// 겨울
			winter();
		}
	}
	
	static void spring() {
		Deque<Tree> temp = new ArrayDeque<>();
		// 나무 리스트 순회하면서 양분 소모
		while (!trees.isEmpty()) {
			Tree cur = trees.poll();
			int r = cur.r;
			int c = cur.c;
			int nut = nutrition[r][c]; // 현재 남아있는 양분
			
			// 양분을 먹을 수 있으면
			if (cur.age <= nut) {
				nutrition[r][c] -= cur.age; // 나이만큼 양분 먹고
				// 나이 1증가 and 나이가 5의 배수인지 체크
				if (++cur.age % 5 == 0) {
					cloneTrees.add(new Tree(r, c, cur.age));
				}
				temp.offer(cur);
			} else { // 먹을 수 없으면
				// 죽은 나무 리스트에 추가
				deadTrees.add(new Tree(r, c, cur.age));
			}
		}
		
		trees = temp;
		
		return;
	}
	
	static void summer() {
		// 모든 죽은 나무를 양분으로 변화시킴
		for (Tree t : deadTrees) {
			nutrition[t.r][t.c] += t.age / 2; 
		}
			
		// 초기화
		deadTrees.clear();
		
		return;
	}
	
	static void fall() {
		// 나무 번식
		for (Tree t : cloneTrees) {
			int r = t.r;
			int c = t.c;
			
			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (isOut(nr, nc)) continue;
				// 나이가 1인 나무 추가
				trees.offerFirst(new Tree(nr, nc, 1));
			}
		}
		
		// 초기화
		cloneTrees.clear();
		
		return;
	}
	
	static void winter() {
		// 맵 전체에 양분 추가
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				nutrition[r][c] += add[r][c];
			}
		}
	}
	
	static boolean isOut(int r, int c) {
		return (r <= 0 || r > N || c <= 0 || c > N);
	}

}