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
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            solve();
        }
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        int ans = 1;
        for (int i = 0; i < m; i++) {
            ans *= n;
            ans %= 10;
        }
        if (ans == 0) {
            ans = 10;
        }
        sb.append(ans + "\n");
    }
}