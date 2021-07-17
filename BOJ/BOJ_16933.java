/**
 * @문제 벽 부수고 이동하기 3_G1
 * @날짜 210716
 * @분류 BFS
 * @조건
 * 
 * @풀이
 * # 낮과 밤을 나누고 k에 대한 상태를 만든다.
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_16933 {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static class Pair {
        int r, c, moves, k;
        
        public Pair(int r, int c, int moves, int k) {
            this.r = r;
            this.c = c;
            this.moves = moves;
            this.k = k;
        }
    }
    
    static int R, C, K, time;
    static char[][] map;
    static boolean[][][] visited;
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // init
        map = new char[R][C];
        visited = new boolean[K + 1][R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        } // input end
        
        // print
        System.out.println(solve());
    }
    
    static int solve() {
        time = 0; // even number => day
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0, 1, 0));
        visited[0][0][0] = true;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            while (size-- > 0) {
                Pair cur = q.poll();
                
                // end point check
                if (cur.r == R - 1 && cur.c == C - 1) {
                    return cur.moves;
                }
                
                if (time % 2 == 0) {
                    for (int d = 0; d < 4; d++) {
                        int nr = cur.r + dr[d];
                        int nc = cur.c + dc[d];
                        
                        if (isOut(nr, nc)) continue;
                        if (cur.k < K && !visited[cur.k + 1][nr][nc] && map[nr][nc] == '1') {
                            q.offer(new Pair(nr, nc, cur.moves + 1, cur.k + 1));
                            visited[cur.k + 1][nr][nc] = true;
                        } else if (!visited[cur.k][nr][nc] && map[nr][nc] == '0') {
                            q.offer(new Pair(nr, nc, cur.moves + 1, cur.k));
                            visited[cur.k][nr][nc] = true;
                        }
                    }
                } else {
                    for (int d = 0; d < 4; d++) {
                        int nr = cur.r + dr[d];
                        int nc = cur.c + dc[d];
                        
                        if (isOut(nr, nc)) continue;
                        if (!visited[cur.k][nr][nc] && map[nr][nc] == '0') {
                            q.offer(new Pair(nr, nc, cur.moves + 1, cur.k));
                            visited[cur.k][nr][nc] = true;
                        }
                    }
                    
                    // stand
                    q.offer(new Pair(cur.r, cur.c, cur.moves + 1, cur.k));
                }
            }
            
            time++; // night shift
        }
        
        return -1;
    }
    
    static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }
    
}
