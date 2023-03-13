
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

    static int k;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        solve();
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        str = br.readLine();
        stk = new StringTokenizer(str);
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        arr = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            str = br.readLine();
            arr[i] = Long.parseLong(str);
        }
        SegTree seg = new SegTree(arr);

        for (int i = 0; i < m + k; i++) {
            str = br.readLine();
            stk = new StringTokenizer(str);
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            long c = Long.parseLong(stk.nextToken());

            if (a == 1) {
                seg.updateVal(b, c);
            } else {
                sb.append(seg.mulVal(b, (int) c)).append('\n');
            }
        }
    }

}

class SegTree {

    static int X = 1_000_000_007;

    long[] tree;
    int n;

    SegTree(long[] arr) {
        this.n = arr.length - 1;
        tree = new long[4 * n];
        init(arr, 1, 1, this.n);
    }

    private long init(long[] arr, int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            return tree[node] = (init(arr, leftChild(node), start, mid)
                    * init(arr, rightChild(node), mid + 1, end)) % X;
        }
    }

    long mulVal(int from, int to) {
        return mul(1, 1, n, from, to);
    }

    private long mul(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return 1;
        } else if (left <= start && end <= right) {
            return tree[node];
        } else {
            int mid = (start + end) / 2;
            return (mul(leftChild(node), start, mid, left, right) * mul(rightChild(node), mid + 1, end, left, right))
                    % X;
        }
    }

    void updateVal(int index, long newVal) {
        update(1, 1, n, index, newVal);
    }

    private long update(int node, int start, int end, int index, long newVal) {
        if (index < start || end < index) {
            return tree[node];
        } else if (start == end) {
            return tree[node] = newVal;
        } else {
            int mid = (start + end) / 2;
            return tree[node] = (update(leftChild(node), start, mid, index, newVal) * update(rightChild(node), mid + 1, end, index, newVal)) % X;
        }
    }

    private int leftChild(int i) {
        return i * 2;
    }

    private int rightChild(int i) {
        return i * 2 + 1;
    }

    void print(){
        System.out.println();
        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }
}