
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
        arr = new char[5][];
        for (int i = 0; i < 5; i++) {
            str = br.readLine();
            arr[i] = new char[str.length()];
            for (int j = 0; j < str.length(); j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[j].length <= i) {
                    continue;
                }
                sb.append(arr[j][i]);
            }
        }
        sb.append('\n');
    }
}