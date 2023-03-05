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

    static ArrayList<ArrayList<Integer>> node;
    static int[] tgt;

    // 최소 트리 분할
    public static void main(String[] args) throws IOException {
        solve();
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        str = br.readLine();
        n = Integer.parseInt(str);
        node = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            node.add(new ArrayList<>());
        }
        tgt = new int[n + 1];

        str = br.readLine();
        stk = new StringTokenizer(str);
        for (int i = 1; i <= n; i++) {
            tgt[i] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            str = br.readLine();
            stk = new StringTokenizer(str);
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            node.get(from).add(to);
            node.get(to).add(from);
        }

        for (int i = 1; i <= n; i++) {
            count += tgt[i];
            isVisited = new boolean[n + 1];
            dfs(i, tgt[i]);
        }
        sb.append(count);
    }

    static boolean[] isVisited;
    static long count = 0;

    public static void dfs(int curr, int flow) {
        if (isVisited[curr] || tgt[curr] == 0) {
            return;
        }
        isVisited[curr] = true;

        if (tgt[curr] < flow) {
            for (int to : node.get(curr)) {
                dfs(to, tgt[curr]);
            }
            tgt[curr] = 0;
        } else { // tgt[curr] >= flow            
            for (int to : node.get(curr)) {
                dfs(to, flow);
            }
            tgt[curr] = tgt[curr] - flow;
        }
    }

    public static void print() {
        for (int i = 1; i <= n; i++) {
            System.out.print(tgt[i] + " ");
        }
        System.out.println();
    }
}