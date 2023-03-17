
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
        stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        sb.append(num(m) - num(n - 1)).append('\n');
    }

    public static int num(int n) {
        int count = 0;
        int j = 1;
        int jcount = 0;
        for (int i = 1; i <= n; i++) {
            count += j;
            jcount++;
            if (jcount == j) {
                j++;
                jcount = 0;
            }
        }
        return count;
    }
}