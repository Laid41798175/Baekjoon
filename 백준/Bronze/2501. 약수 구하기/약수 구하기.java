
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

    static char[][] arr;

    public static void solve() throws IOException {
        str = br.readLine();
        stk = new StringTokenizer(str);
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                if (++count == m) {
                    sb.append(i).append('\n');
                    return;
                }
            }
        }
        sb.append(0).append('\n');
    }
}