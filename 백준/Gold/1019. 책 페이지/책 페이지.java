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

    static long[] count;

    public static void solve() throws IOException {
        count = new long[10];

        str = br.readLine();
        countSum(0);

        for (int i = 0; i < 10; i++) {
            sb.append(count[i] + " ");
        }
        sb.append('\n');
    }

    public static void countSum(int index) {
        n = str.charAt(index) - '0';
        int digit = str.length() - index - 1;
        
        if (digit == 0) {
            for (int i = 0; i <= n; i++) {
                count[i] += 1;
            }
            
            if (str.length() == 1) {
                count[0] = 0;
            }
            
            return;
        }

        if (index == 0) {
            for (int i = 0; i <= 9; i++) {
                count[i] += from0sTo9s(digit);
            }
            count[0] -= offset0(digit);

            for (int i = 1; i < n; i++) {
                count[i] += pow10(digit);
                for (int j = 0; j < 10; j++) {
                    count[j] += from0sTo9s(digit);
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                count[i] += pow10(digit);
                for (int j = 0; j < 10; j++) {
                    count[j] += from0sTo9s(digit);
                }
            }
        }

        int t = Integer.parseInt(str.substring(index + 1));
        count[n] += t + 1;

        countSum(index + 1);
    }

    public static long from0sTo9s(int digit) {
        long ret = digit;
        for (int i = 0; i < digit - 1; i++) {
            ret *= 10;
        }
        return ret;
    }

    public static long offset0(int digit) {
        long ret = 1;
        for (int i = 0; i < digit - 1; i++) {
            ret *= 10;
            ret += 1;
        }
        return ret;
    }

    public static long pow10(int n) {
        long ret = 1;
        for (int i = 0; i < n; i++) {
            ret *= 10;
        }
        return ret;
    }
}