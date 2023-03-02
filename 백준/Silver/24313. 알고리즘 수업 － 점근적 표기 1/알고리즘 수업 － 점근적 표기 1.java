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
        str = br.readLine();
        stk = new StringTokenizer(str);
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        
        str = br.readLine();
        int c = Integer.parseInt(str);

        str = br.readLine();
        int n0 = Integer.parseInt(str);

        if (c == n) {
            sb.append(m < 0 ? "1\n" : "0\n");
        } else if (n < c) {
            double x = m / (c - n);
            sb.append(x <= n0 ? "1\n" : "0\n");
        } else {
            sb.append("0\n");
        }
    }
}