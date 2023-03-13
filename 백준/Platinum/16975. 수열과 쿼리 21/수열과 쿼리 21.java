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

    static long[] arr;

    public static void main(String[] args) throws IOException {
        solve();
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        str = br.readLine();
        n = Integer.parseInt(str);
        arr = new long[n + 1];

        str = br.readLine();
        stk = new StringTokenizer(str);
        for (int i = 1; i <= n; i++) {            
            arr[i] = Long.parseLong(stk.nextToken());
        }

        FenwickTree ft = new FenwickTree(arr);

        str = br.readLine();
        m = Integer.parseInt(str);
        for (int i = 0; i < m; i++) {
            str = br.readLine();
            stk = new StringTokenizer(str);
            int q = Integer.parseInt(stk.nextToken());           
            if (q == 1) {
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());
                int k = Integer.parseInt(stk.nextToken());
                ft.update(a, k);
                if (b + 1 <= n) {
                    ft.update(b + 1, -k);
                }
            } else {
                int x = Integer.parseInt(stk.nextToken());
                sb.append(ft.sum(x) + "\n");
            }
        }
    }

}

class FenwickTree {

    long[] tree;
    int n;

    FenwickTree(long[] arr) {
        n = arr.length - 1;
        tree = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            update(i, arr[i] - arr[i - 1]);
        }
    }
    
    void update (int index, long diff){
        while (index <= n) {
            tree[index] += diff;
            index += (index & -index);
        }
    }

    long get (int index) {
        return tree[index];
    }

    long sum (int to) {
        long sum = 0;
        while (to > 0) {
            sum += tree[to];
            to -= (to & -to);
        }
        return sum;
    }

    long sum (int from, int to) {
        return sum(to) - sum(from - 1);
    }

    void print() {
        System.out.println("PRINT");
        for (int i = 1; i <= n; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }
}