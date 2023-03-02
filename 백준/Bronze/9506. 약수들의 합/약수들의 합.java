
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
        while (true) {
            str = br.readLine();
            n = Integer.parseInt(str);
            if (n == -1) {
                break;
            } else {
                solve();
            }
        }
        System.out.print(sb);
    }

    static Queue<Integer> list;

    public static void solve() throws IOException {
        int sum = 0;
        list = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum += i;
                if (sum > n) {
                    sb.append(n + " is NOT perfect.\n");
                    return;
                }
                list.add(i);
            }
        }
        if (sum == n) {
            sb.append(n + " = " + list.poll());
            while (!list.isEmpty()) {
                sb.append(" + " + list.poll());
            }
            sb.append('\n');
        } else {
            sb.append(n + " is NOT perfect.\n");
        }
    }
}