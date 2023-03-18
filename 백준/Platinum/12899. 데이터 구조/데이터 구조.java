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

    public static void solve() throws IOException {
        n = Integer.parseInt(br.readLine());
        SegmentTree tree = new SegmentTree();
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            T = Integer.parseInt(stk.nextToken());
            if (T == 1) {
                tree.update(Integer.parseInt(stk.nextToken()));
            } else {
                sb.append(tree.pop(Integer.parseInt(stk.nextToken())) + "\n");
            }
        }
    }
}

class SegmentTree {

    int max;
    int[] tree;

    SegmentTree() {
        max = 2000000;
        tree = new int[max * 4];
    }

    private int update(int num, int node, int left, int right) {
        if (num < left || right < num)
            return tree[node];

        if (left == right)
            return tree[node] = tree[node] + 1;

        int mid = (left + right) / 2;
        return tree[node] = update(num, leftChild(node), left, mid) + update(num, rightChild(node), mid + 1, right);
    }

    private int pop(int num, int compare, int node, int left, int right) {
        tree[node]--;
        if (left == right)
            return left;

        int mid = (left + right) / 2;
        int compareNext = compare + tree[leftChild(node)];
        if (num <= compareNext)
            return pop(num, compare, leftChild(node), left, mid);
        else
            return pop(num, compareNext, rightChild(node), mid + 1, right);
    }

    private int leftChild(int node) {
        return node * 2;
    }

    private int rightChild(int node) {
        return node * 2 + 1;
    }

    void update(int n) {
        update(n, 1, 1, max);
    }

    int pop(int num) {
        return pop(num, 0, 1, 1, max);
    }

    void print() {
        System.out.println(Arrays.toString(tree));
    }
}