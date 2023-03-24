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

    static int a;
    static int b;
    static int c;
    static int d;
    static int e;
    static int f;

    public static void main(String[] args) throws IOException {
        solve();
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        stk = new StringTokenizer(br.readLine());
        a = Integer.parseInt(stk.nextToken());
        b = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        d = Integer.parseInt(stk.nextToken());
        e = Integer.parseInt(stk.nextToken());
        f = Integer.parseInt(stk.nextToken());
        
        for (int x = -999; x <= 999; x++) {
            for (int y = -999; y <= 999; y++) {
                if (a * x + b * y == c && d * x + e * y == f) {
                    sb.append(x + " " + y + '\n');
                    return;
                }
            }
        }
    }
}