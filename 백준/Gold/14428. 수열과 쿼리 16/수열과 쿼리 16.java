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

    public static void main(String[] args) throws IOException {
        solve();
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        SegTree tree = new SegTree(arr);

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            if (q == 1) {
                tree.updateVal(a, b);
            } else {
                sb.append(tree.min(a, b)).append('\n');
            }
        }
    }
}

class SegTree {
    int[] min;
    int n;

    int[] arr;

    SegTree(int[] arr) {
        n = arr.length - 1;
        this.arr = arr;
        arr[0] = Integer.MAX_VALUE;

        min = new int[4 * n];
        initMin(1, 1, n);
    }

    private int initMin(int node, int start, int end) {
        if (start == end) {
            return min[node] = start;
        } else {
            int mid = (start + end) / 2;
            int l = initMin(leftChild(node), start, mid);
            int r = initMin(rightChild(node), mid + 1, end);
            return min[node] = (arr[l] <= arr[r] ? l : r);
        }
    }

    int min(int left, int right) {
        int ret = getMin(1, 1, n, left, right);
        if (ret == 0) {
            int c = 1/0;
        }
        return ret;
    }

    private int getMin(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return 0; // arr[0] = Integer.MAX_VALUE;
        } else if (left <= start && end <= right) {
            return min[node];
        } else {
            int mid = (start + end) / 2;
            int l = getMin(leftChild(node), start, mid, left, right);
            int r = getMin(rightChild(node), mid + 1, end, left, right);
            return arr[l] <= arr[r] ? l : r;
        }
    }

    void updateVal(int index, int newVal) {
        arr[index] = newVal;
        updateMin(1, 1, n, index);
    }

    private int updateMin(int node, int start, int end, int index) {
        if (index < start || end < index) {
            return min[node];
        } else if (start == end) {
            return min[node];
        } else {
            int mid = (start + end) / 2;
            int l = updateMin(leftChild(node), start, mid, index);
            int r = updateMin(rightChild(node), mid + 1, end, index);
            return min[node] = (arr[l] <= arr[r] ? l : r);
        }
    }

    private int leftChild(int i) {
        return i * 2;
    }

    private int rightChild(int i) {
        return i * 2 + 1;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(arr[i] + " ");
        }
        sb.append('\n');
        for (int i = 0; i < 4 * n; i++) {
            sb.append(min[i] + " ");
        }
        sb.append('\n');
        return sb.toString();
    }
}