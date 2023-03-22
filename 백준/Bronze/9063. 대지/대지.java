//Do Not Modify From
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String str = "";
    static StringTokenizer stk;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T;
    static int n;
    static int m;
    // Do Nod Modify To

    public static void main(String[] args) throws IOException {
        solve();
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        n = Integer.parseInt(br.readLine());
        
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(stk.nextToken());
            y[i] = Integer.parseInt(stk.nextToken());
        }
        
        Arrays.sort(x);
        Arrays.sort(y);
        
        sb.append((x[n - 1] - x[0]) * (y[n - 1] - y[0])).append('\n');
    }
}