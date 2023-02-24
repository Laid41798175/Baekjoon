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

	static int k;

	static int[] arr;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		arr = new int[m];
		parent = new int[m];

		str = br.readLine();
		stk = new StringTokenizer(str);
		for (int i = 0; i < m; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			parent[i] = i;
		}
		Arrays.sort(arr);

		str = br.readLine();
		stk = new StringTokenizer(str);
		for (int i = 0; i < k; i++) {
			int tgt = Integer.parseInt(stk.nextToken());
			int index = Arrays.binarySearch(arr, tgt);
			if (index >= 0) {
				index++;
			} else {
				index = -index - 1;
			}
			sb.append(arr[find(index)] + "\n");
			if (find(index) + 1 < m) {
				union(index,find(index) + 1);
			}
		}
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

		parent[find(x)] = find(y);
	}
}