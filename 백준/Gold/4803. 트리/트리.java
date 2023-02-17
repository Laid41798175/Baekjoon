//Do Not Modify From
import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String str;
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int T;
	static int n;
	static int m;

	// Do Nod Modify To

	static boolean[][] isConnected;
	static boolean[] isVisited;

	static int count = 0;

	public static void main(String[] args) throws IOException {
		while (true) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			n = Integer.parseInt(stk.nextToken());
			m = Integer.parseInt(stk.nextToken());
			if (n == 0 && m == 0) {
				break;
			}
			solve();
		}
		System.out.print(sb);
	}

	public static void solve() throws IOException {
		count = 0;
		isConnected = new boolean[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			isConnected[from][to] = true;
			isConnected[to][from] = true;
		}

		isVisited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			if (!isVisited[i]) {
				isTree = true;
				dfs(0, i);
				if (isTree) {
					count++;
				}
			}
		}
		
		switch (count) {
		case 0:
			sb.append("Case " + ++T + ": No trees.\n");
			break;
		case 1:
			sb.append("Case " + ++T + ": There is one tree.\n");
			break;
		default:
			sb.append("Case " + ++T + ": A forest of " + count + " trees.\n");
		}
	}

	static boolean isTree;

	public static void dfs(int from, int to) {
		if (isVisited[to]) {
			isTree = false;
			return;
		}
		isVisited[to] = true;

		for (int i = 1; i <= n; i++) {
			if (i != from && isConnected[to][i]) {
				dfs(to, i);
			}
		}
	}

}