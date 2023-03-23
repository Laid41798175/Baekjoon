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
        int n1 = Integer.parseInt(br.readLine());
        int n2 = Integer.parseInt(br.readLine());
        int n3 = Integer.parseInt(br.readLine());
        
        if (n1 + n2 + n3 != 180) {
            sb.append("Error\n");
            return;
        } else if (n1 == n2 && n2 == n3 && n3 == 60) {
            sb.append("Equilateral\n");
            return;
        } else if (n1 == n2 || n2 == n3 || n3 == n1) {
            sb.append("Isosceles\n");
            return;
        } else {
            sb.append("Scalene\n");
            return;
        }
    }
}