/**
 * @문제 최소 스패닝 트리
 * @날짜 211014
 * @분류 
 * @조건
 * #
 * @풀이
 * #
 * @comment
 * #
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class SWEA_3124 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int T;
	static int V, E; // 많다..
	static LinkNode[] graph;

	public static void main(String[] args) throws IOException {
//		input = new BufferedReader(new StringReader(src));
		T = Integer.parseInt(input.readLine());

		for (int t = 1; t <= T; t++) {
			tokens = new StringTokenizer(input.readLine(), " ");
			V = Integer.parseInt(tokens.nextToken()) + 1; // 인덱스 보정용.
			E = Integer.parseInt(tokens.nextToken());

			graph = new LinkNode[V];
			for (int e = 0; e < E; e++) {
				tokens = new StringTokenizer(input.readLine(), " ");
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				int c = Integer.parseInt(tokens.nextToken());
				// 양방향 그래프로 구성
				graph[a] = new LinkNode(b, c, graph[a]);
				graph[b] = new LinkNode(a, c, graph[b]);
			}
			/*
			// 그래프 확인
			for(LinkNode link: graph) {
				System.out.println(link);
			}
			*/
			// 이제 MST 구성해보기.
			long total = prim();
			output.append('#').append(t).append(' ').append(total).append('\n');
		}
		System.out.println(output);
	}

	// 가중치 절대값: 1_000_000, 정점 개수: 1_000,000 --> int는 넘을 수 있다.
	static long prim() {
		PriorityQueue<LinkNode> pq = new PriorityQueue<>();
		boolean [] visited = new boolean[V];
		// 임의의 한 점에서 출발하자.(어차피 MST는 연결!)
		pq.offer(new LinkNode(1, 0));
		//visited[1]=true;

		long sum = 0;// 총 MST 비용
		int visitedCnt = 0;// 연결된 정점의 개수
		while(!pq.isEmpty()) {
			// 1.맨 처음 녀석 가져오기
			LinkNode head = pq.poll();

			// 2. 사용하기 - 방문 처리??
			if(visited[head.i]) {
				continue;
			}
			visited[head.i]=true;
			sum+=head.cost;
			// MST를 구성하는 간선의 개수는.. V-1
			if(++visitedCnt ==V-1) {
				return sum;
			}

			// 3. 자식 탐색하기
			LinkNode pre = graph[head.i];
			while(pre!=null) {
				if(!visited[pre.i]) {
					pq.offer(new LinkNode(pre.i, pre.cost));
					// PQ는 선입 선출이 아니다 --> PQ는 끄집어 낼때 방문 처리해준다.!!
					//visited[pre.i];
				}
				pre = pre.pre;
			}
		}


		return -1;
	}

	// 그래프 크기가 좀 커요~~
	// P.Q - 비교할 수 있어야 한다.
	static class LinkNode implements Comparable<LinkNode> {
		int i;// 정점 번호,
		int cost;// 그 정점까지 비용
		LinkNode pre; // 이전 노드

		public LinkNode(int i, int cost) {
			this(i, cost, null);
		}

		public LinkNode(int i, int cost, LinkNode pre) {
			super();
			this.i = i;
			this.cost = cost;
			this.pre = pre;
		}

		@Override
		// 비용에 대해서 오름차순
		public int compareTo(LinkNode o) {
			return Integer.compare(this.cost, o.cost);
		}

		@Override
		public String toString() {
			return "[i=" + i + ", cost=" + cost + ", pre=" + pre + "]";
		}
	}
}