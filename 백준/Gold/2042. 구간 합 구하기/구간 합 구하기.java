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
                seg.updateVal(b, arr[b], c);
                arr[b] = c;
            } else {
                sb.append(seg.sumVal(b, (int) c)).append('\n');
            }
        }
    }

}

class SegTree {

    long[] tree;
    int n;

    SegTree(long[] arr) {
        this.n = arr.length - 1;
        tree = new long[4 * n];
        init (arr, 1, 1, this.n);
    }

    private long init (long[] arr, int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            return tree[node] = init(arr, leftChild(node), start, mid)
                    + init(arr, rightChild(node), mid + 1, end);
        }
    }

    long sumVal(int from, int to) {
        return sum(1, 1, n, from, to);
    }

    private long sum(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return 0;
        } else if (left <= start && end <= right) {
            return tree[node];
        } else {
            int mid = (start + end) / 2;
            return sum(leftChild(node), start, mid, left, right) + sum(rightChild(node), mid + 1, end, left, right);
        }
    }

    void updateVal(int index, long orgVal, long newVal) {
        update(1, 1, n, index, newVal - orgVal);
    }

    private void update(int node, int start, int end, int index, long diff) {
        if (index < start || end < index) {
            return;
        } else {
            tree[node] += diff;

            if (start != end) {
                int mid = (start + end) / 2;
                update(leftChild(node), start, mid, index, diff) ;
                update(rightChild(node), mid + 1, end, index, diff) ;
            }
        }
    }

    private int leftChild(int i) {
        return i * 2;
    }

    private int rightChild(int i) {
        return i * 2 + 1;
    }
}