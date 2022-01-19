package asd;

import java.util.*;
import java.io.*;

public class BOJ_19236 {
    
    private static class Fish {
        int r, c, direction; // ��ġ, ����
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
    
    private static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // ����� �ݽð�
    private static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        // init
        int[][] map = new int[R][C]; // �� ��ġ�� ����� ��ȣ�� ����
        Fish[] fishes = new Fish[R * C + 1]; // �ε���(����� ��ȣ)�� ����� ���� ����
        
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < C; c++) {
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1; // ���� 0���ͷ� ����
                map[r][c] = num;
                fishes[num] = new Fish(r, c, direction, true);
            }
        }
        
        // ��� (0, 0)���� ����
        fishes[0] = new Fish(0, 0, fishes[map[0][0]].direction, true); // ��� ���� �ʱ�ȭ
        max = map[0][0]; // (0, 0) ��ġ�� ����� �Ա�
        fishes[map[0][0]].isLive = false;
        map[0][0] = 0; // (0, 0) ��ġ�� ��� �ֱ�
        
        dfs(map, fishes, max);
        
        // print
        System.out.println(max);
    }
    
    private static void dfs(int[][] map, Fish[] fishes, int sum) { // sum : �� ���� ����� ��ȣ ��
        // ��Ʈ��ŷ - ����� �̵��ϱ� ���� �� �̵��� ĭ�� ���ų� ��ο� ��ĭ�ۿ� ���� ��� �ִ밪 �����ϰ� ����
        if (!isSharkCanMove(map, fishes)) {
            max = Math.max(max, sum);
            return;
        }

        // ����� �̵� - ��ȣ�� ���� �������� �������
        for (int i = 1, end = R * C; i <= end; i++) {
        	if (!fishes[i].isLive) continue;
            int r = fishes[i].r;
            int c = fishes[i].c;
             int d = fishes[i].direction;
            
            // �̵��� �� �ִ� ĭ�� ������ ���� �ݽð� ȸ��
            for (int offset = 0; offset < 8; offset++) {
            	int nd = (d + offset) % 8;
                int nr = r + dr[nd];
                int nc = c + dc[nd];
                
                if (isOut(nr, nc)) continue; // ��� üũ
                if (map[nr][nc] == 0) continue; // �̵��Ϸ��� ĭ�� ��� �ִ� ĭ���� üũ
                if (map[nr][nc] == -1) { // �̵��Ϸ��� ĭ�� ��ĭ�̸� �̵�
                    map[r][c] = -1;
                } else { // ����Ⱑ �ִ� ĭ�̸� swap
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
        
        // ��� �̵�
        ArrayList<Integer> sharkPath = getEatableList(map, fishes); // �� ���� �� �ִ� ����� ��ȣ ����Ʈ
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
            
            copyMap[copyFishes[0].r][copyFishes[0].c] = -1; // ���� ��� ��ġ ��ĭ����
            copyMap[copyFishes[num].r][copyFishes[num].c] = 0; // ��� ��ġ �̵�
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
            if (map[r][c] == -1) { // ��ĭ�̸� continue
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
            if (map[r][c] == -1) { // ��ĭ�̸� continue
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