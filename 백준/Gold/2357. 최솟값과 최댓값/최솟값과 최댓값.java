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
        stk = new StringTokenizer(str);
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            str = br.readLine();
            arr[i] = Long.parseLong(str);
        }

        SegTree seg = new SegTree(arr);
        
        for (int i = 0; i < m; i++) {
            str = br.readLine();
            stk = new StringTokenizer(str);
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            sb.append(seg.min(a, b)).append(' ').append(seg.max(a, b)).append('\n');
        }
    }
}

class SegTree {

    long[] minTree;
    long[] maxTree;
    int n;

    SegTree(long[] arr) {
        this.n = arr.length - 1;
        minTree = new long[4 * n];
        maxTree = new long[4 * n];
        initMin (arr, 1, 1, this.n);
        initMax (arr, 1, 1, this.n);
    }

    long min(int from, int to) {
        return min(1, 1, n, from, to);
    }

    private long min(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return Long.MAX_VALUE;
        } else if (left <= start && end <= right) {
            return minTree[node];
        } else {
            int mid = (start + end) / 2;
            return Math.min(min(leftChild(node), start, mid, left, right), min(rightChild(node), mid + 1, end, left, right));
        }
    }

    long max(int from, int to) {
        return max(1, 1, n, from, to);
    }

    private long max(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return Long.MIN_VALUE;
        } else if (left <= start && end <= right) {
            return maxTree[node];
        } else {
            int mid = (start + end) / 2;
            return Math.max(max(leftChild(node), start, mid, left, right), max(rightChild(node), mid + 1, end, left, right));
        }
    }

    private long initMin (long[] arr, int node, int start, int end) {
        if (start == end) {
            return minTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            return minTree[node] = Math.min(initMin(arr, leftChild(node), start, mid), initMin(arr, rightChild(node), mid + 1, end));
        }
    }
    
    private long initMax (long[] arr, int node, int start, int end) {
        if (start == end) {
            return maxTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            return maxTree[node] = Math.max(initMax(arr, leftChild(node), start, mid), initMax(arr, rightChild(node), mid + 1, end));
        }
    }

    private int leftChild(int i) {
        return i * 2;
    }

    private int rightChild(int i) {
        return i * 2 + 1;
    }
}