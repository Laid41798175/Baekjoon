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

    static int[][] sum = new int[400000][2];

    public static void main(String[] args) throws IOException {
        solve();
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        str = br.readLine();
        stk = new StringTokenizer(str);
        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        arr = new int[n];

        str = br.readLine();
        stk = new StringTokenizer(str);

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = arr[i] + arr[j];
                sum[tmp][0] = i;
                sum[tmp][1] = j;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = m - (arr[i] + arr[j]);
                if (tmp >= 400000 || tmp < 0 || (sum[tmp][0] == 0 && sum[tmp][1] == 0)) {
                    continue;
                }
                if (sum[tmp][0] != i && sum[tmp][0] != j && sum[tmp][1] != i && sum[tmp][1] != j) {
                    sb.append("YES\n");
                    return;
                }
            }
        }

        sb.append("NO\n");
    }
}