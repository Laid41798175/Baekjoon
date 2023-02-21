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

	static boolean[] isDocked;
	static int[] parent;
	static int[] arr;

	static int count = 0;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);
		isDocked = new boolean[n + 1];
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		str = br.readLine();
		m = Integer.parseInt(str);
		arr = new int[m];

		for (int i = 0; i < m; i++) {
			str = br.readLine();
			if (!dock(Integer.parseInt(str))) {
				break;
			}
		}

		sb.append(count);
	}

	public static boolean dock(int tgt) {
		int goal = find(tgt);
		if (goal == 0) {
			return false;
		}

		isDocked[goal] = true;
		union(goal - 1, tgt);
		count++;
		return true;
	}

	public static int find(int x) {
		int c = x;
		while (c != parent[c]) {
			c = parent[c];
		}
		parent[x] = c;
		return c;
	}
	
	public static void union(int x, int y) {
		if (find(x) == find(y)) {
			return;
		}
		parent[find(y)] = find(x);
	}
}