package asd;

import java.util.*;
import java.io.*;

public class BOJ_19236 {
    
    private static class Fish {
        int r, c, direction; // 위치, 방향
        boolean isLive;
        
        public Fish(int r, int c, int direction, boolean isLive) {
            this.r = r;
            this.c = c;
            this.direction = direction;
            this.isLive = isLive;
        }
    }
    
    private static final int R = 4;
    private static final int C = 4;
    private static int max;
    
    private static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상부터 반시계
    private static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        // init
        int[][] map = new int[R][C]; // 각 위치에 물고기 번호를 관리
        Fish[] fishes = new Fish[R * C + 1]; // 인덱스(물고기 번호)로 물고기 정보 관리
        
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < C; c++) {
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1; // 방향 0부터로 보정
                map[r][c] = num;
                fishes[num] = new Fish(r, c, direction, true);
            }
        }
        
        // 상어 (0, 0)에서 시작
        fishes[0] = new Fish(0, 0, fishes[map[0][0]].direction, true); // 상어 정보 초기화
        max = map[0][0]; // (0, 0) 위치의 물고기 먹기
        fishes[map[0][0]].isLive = false;
        map[0][0] = 0; // (0, 0) 위치에 상어 넣기
        
        dfs(map, fishes, max);
        
        // print
        System.out.println(max);
    }
    
    private static void dfs(int[][] map, Fish[] fishes, int sum) { // sum : 상어가 먹은 물고기 번호 합
        // 백트래킹 - 물고기 이동하기 전에 상어가 이동할 칸이 없거나 경로에 빈칸밖에 없는 경우 최대값 갱신하고 리턴
        if (!isSharkCanMove(map, fishes)) {
            max = Math.max(max, sum);
            return;
        }

        // 물고기 이동 - 번호가 작은 물고기부터 순서대로
        for (int i = 1, end = R * C; i <= end; i++) {
        	if (!fishes[i].isLive) continue;
            int r = fishes[i].r;
            int c = fishes[i].c;
             int d = fishes[i].direction;
            
            // 이동할 수 있는 칸이 있을때 까지 반시계 회전
            for (int offset = 0; offset < 8; offset++) {
            	int nd = (d + offset) % 8;
                int nr = r + dr[nd];
                int nc = c + dc[nd];
                
                if (isOut(nr, nc)) continue; // 경계 체크
                if (map[nr][nc] == 0) continue; // 이동하려는 칸이 상어 있는 칸인지 체크
                if (map[nr][nc] == -1) { // 이동하려는 칸이 빈칸이면 이동
                    map[r][c] = -1;
                } else { // 물고기가 있는 칸이면 swap
                    int tempNum = map[nr][nc];
                    map[r][c] = tempNum;
                    fishes[tempNum].r = r;
                    fishes[tempNum].c = c;
            
                }
                map[nr][nc] = i;
                fishes[i].r = nr;
                fishes[i].c = nc;
                fishes[i].direction = nd;
                break;
            }
        }
        
        // 상어 이동
        ArrayList<Integer> sharkPath = getEatableList(map, fishes); // 상어가 먹을 수 있는 물고기 번호 리스트
        for (int num : sharkPath) {
            int[][] copyMap = new int[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    copyMap[r][c] = map[r][c];
                }
            }
            Fish[] copyFishes = new Fish[R * C + 1];
            for (int i = 0, end = R * C; i <= end; i++) {
                copyFishes[i] = new Fish(fishes[i].r, fishes[i].c, fishes[i].direction, fishes[i].isLive);
            }
            
            copyMap[copyFishes[0].r][copyFishes[0].c] = -1; // 기존 상어 위치 빈칸으로
            copyMap[copyFishes[num].r][copyFishes[num].c] = 0; // 상어 위치 이동
            copyFishes[0].r = copyFishes[num].r;
            copyFishes[0].c = copyFishes[num].c;
            copyFishes[0].direction = copyFishes[num].direction;
            copyFishes[num].isLive = false;
            
            dfs(copyMap, copyFishes, sum + num);
        }
    }
    
    private static boolean isSharkCanMove(int[][] map, Fish[] fishes) {
        int r = fishes[0].r;
        int c = fishes[0].c;
        int d = fishes[0].direction;
        
        while (true) {
            r += dr[d];
            c += dc[d];
            
            if (isOut(r, c)) return false;
            if (map[r][c] == -1) { // 빈칸이면 continue
                continue;
            } else {
            	return true;
            }
        }
    }
    
    private static ArrayList<Integer> getEatableList(int[][] map, Fish[] fishes) {
        int r = fishes[0].r;
        int c = fishes[0].c;
        int d = fishes[0].direction;
        ArrayList<Integer> list = new ArrayList<>();
        
        while (true) {
            r += dr[d];
            c += dc[d];
            
            if (isOut(r, c)) break;
            if (map[r][c] == -1) { // 빈칸이면 continue
                continue;
            } else {
                list.add(map[r][c]);
            }
        }
        
        return list;
    }
    
    private static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }
    
}