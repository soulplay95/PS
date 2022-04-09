import java.util.*;
import java.io.*;

public class Main {
    static final int MOD = 10;
    
    static int getMod(int a, int b) {
        if (a == 1) return 1;
        a = a % MOD;
        if (b == 0) return 1;
        if (b == 1) return b;
        a = getMod(a * a, b / 2) * ((b & 1) == 1 ? a : 1);
        return a % MOD;
    }
    
    public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    StringBuilder sb = new StringBuilder();
    
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
        st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        int mod = getMod(a, b);
        sb.append(mod == 0 ? 10 : mod).append("\n");
    }
        
    System.out.print(sb.toString());
}
}