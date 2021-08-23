/**
 * @문제 단어 수학_G4
 * @날짜 210824
 * @분류 그리디, 브루트포스
 * @조건
 * @풀이
 * @comment
 */

import java.util.*;
import java.io.*;

public class BOJ_2206 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	 static int N;
   	 static String[] word;
  	 static int[] arr;
   	 static int res;
	
	public static void main(String[] args) throws IOException {
		  N = Integer.parseInt(br.readLine());
		  word = new String[N];
		  arr = new int[26];

		  for(int i = 0; i < N; i++) {
		      word[i] = br.readLine();
          }

		  for(int i = 0; i < word.length; i++) {
		      int pow = word[i].length() - 1;
		      for(int j = 0; j < word[i].length(); j++) {
		          arr[word[i].charAt(j) - 65] += Math.pow(10, pow--);
		      }
		  } // input end

        Arrays.sort(arr);

		  int cnt = 25;

		  for(int i=9; i>=0; i--) {
		      arr[cnt] *= i;
		      res += arr[cnt--];
		  }

		  // print
        System.out.println(res);
	}
	
}
