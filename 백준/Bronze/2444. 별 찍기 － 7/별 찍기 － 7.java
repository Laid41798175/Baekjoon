
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

    static int[] arr;

    public static void solve() throws IOException {
        str = br.readLine();
        n = Integer.parseInt(str);
        for (int i = 1; i <= n; i++) {
            print(i, n);
        }
        for (int i = n - 1; i >= 1; i--) {
            print(i, n);
        }       
    }

    static void print(int i, int n){
        for (int j = 0; j < (n - i); j++){
            sb.append(' ');
        }
        for (int j = 0; j < 2 * i - 1; j++) {
            sb.append('*');
        }
        sb.append('\n');
    }
}