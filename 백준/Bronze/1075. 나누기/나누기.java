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

    static int[] arr;

    public static void main(String[] args) throws IOException {
        solve();
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        n = Integer.parseInt(br.readLine());
        n -= n % 100; 
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < 100; i++) {
            if ((n + i) % m == 0) {
                if (i < 10) {
                    sb.append(0);
                }
                sb.append(i + "\n");
                break;
            }
        }
    }
}