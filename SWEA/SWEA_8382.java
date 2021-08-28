import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_8382_prof_solve {
	static int TC;
	static int x1,y1,x2,y2;
	static int [] dr= {-1,0,1,0};// 상좌하우 0123 -> 0101 상하 움직이면 좌우로
	static int [] dc= {0,-1,0,1};
	static int[][][] visited;
	
	public static void main(String[] args) {
		
		Scanner scann=new Scanner(System.in);
		TC=scann.nextInt();		
		
		for (int t = 1; t <= TC; t++) {
			x1=scann.nextInt()+100;
			y1=scann.nextInt()+100;
			x2=scann.nextInt()+100;
			y2=scann.nextInt()+100;
			
			visited=new int[201][201][2];
			
			Queue<int[]> que=new LinkedList<>();
			que.offer(new int[] {x1,y1,0,0});
			que.offer(new int[] {x1,y1,0,1});
			visited[x1][y1][0]=1;  //짝수
			visited[x1][y1][1]=1;  //홀수
			int val=-1;
			while(!que.isEmpty()) {
				int[] cu=que.poll();
				int r=cu[0];
				int c=cu[1];
				int cnt=cu[2];
				int dir=cu[3];
				if(r==x2 && c==y2) {
					val=cnt;
					break;
				}
				// dir 0, 2=> 홀수
				// dir 1 3 => 짝수 (dir+d)%4
				for (int d = 1; d < 4; d+=2) {
					int s=(dir+d)%4;
					int u=(dir+d)%2;
					int nr=r+dr[s];
					int nc=c+dc[s];
					if(!check(nr,nc))continue;
					if(visited[nr][nc][u]==0) {
						visited[nr][nc][u]=1;
						que.offer(new int[] {nr,nc,cnt+1,s});
					}
				}
				

			}
			System.out.println("#"+t+" "+val);
		}
	}

	static boolean check(int n, int c) {
		return n>=0 && n<201 && c>=0 && c<201;
	}
	
}