package algorithm.topological_sort.practice;

/**
 * @문제 영우는 사기꾼? [G3]
 * @날짜 220614
 * @분류 구현 / 위상 정렬
 * @조건
 * 1 <= N, M, K <= 10만
 * @풀이
 * - 건물 건설 내역에는 그래프에서 해당 노드를 삭제함으로써 새롭게 건설할 수 있는 노드들을 체크한다. + 건물 건설 횟수 증가
 * - 건물 파괴 내역에는 건물 건설 횟수에서 1이상인지 체크하고 감소시킨다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(K)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_14676 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, K;
    static ArrayList<Integer>[] adjList;
    static int[] indegree, buildCounts;
    static boolean[] isBuildable;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        indegree = new int[N + 1];
        buildCounts = new int[N + 1];
        isBuildable = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            indegree[to]++;
        }

        // solve
        // 초기에 지을 수 있는 건물을 체크 => indegree가 0인
        for (int i = 1; i <= N; i++) { // O(N)
            if (indegree[i] == 0) {
                isBuildable[i] = true;
            }
        }

        while (K-- > 0) { // O(K)
            st = new StringTokenizer(br.readLine(), " ");
            int type = Integer.parseInt(st.nextToken());
            int buildingNumber = Integer.parseInt(st.nextToken());

            if (type == 1) {
                // 건물을 지을 수 있는지 체크
                if (isBuildable[buildingNumber] && indegree[buildingNumber] == 0) {
                    buildCounts[buildingNumber]++;

                    // 해당 건물을 그래프에서 삭제 => 새롭게 지을 수 있게 되는 건물 갱신
                    for (int adjVertex : adjList[buildingNumber]) { // O(M)
                        indegree[adjVertex]--;
                        if (indegree[adjVertex] == 0) {
                            // 지을 수 있게 됨
                            isBuildable[adjVertex] = true;
                        }
                    }
                } else {
                    System.out.println("Lier!");
                    return;
                }
            } else {
                if (buildCounts[buildingNumber] == 0) { // 지은 건물이 없는데 파괴하는 경우
                    System.out.println("Lier!");
                    return;
                } else if (buildCounts[buildingNumber] == 1) { // 건물이 없어지는 순간
                    // 이 시점부터 바로 인접한 건물들을 지을 수 없어짐
                    for (int adjVertex : adjList[buildingNumber]) {
                        isBuildable[adjVertex] = false;
                        indegree[adjVertex]++;
                    }
                }

                buildCounts[buildingNumber]--; // 파괴
            }
        }

        System.out.println("King-God-Emperor");
    }

}
