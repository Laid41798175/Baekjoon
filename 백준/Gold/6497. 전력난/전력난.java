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

	static int[] parent;

	static Queue<Edge> q;
	static int sum;
	static int maintain;

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
		q = new PriorityQueue<Edge>();
		sum = 0;
		maintain = 0;
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());

			q.add(new Edge(from, to, w));
			sum += w;
		}

		int count = n - 1;
		while (!q.isEmpty()) {
			Edge e = q.poll();
			if (find(e.from) == find(e.to)) {
				continue;
			}
			union(e.from, e.to);
			maintain += e.w;
			if (--count == 0) {
				break;
			}
		}
		sb.append(sum - maintain + "\n");
	}

	public static void union(int x, int y) {
		if (find(x) == find(y)) {
			return;
		}
		parent[find(x)] = find(y);
	}

	public static int find(int x) {
		int c = x;
		while (c != parent[c]) {
			c = parent[c];
		}
		parent[x] = c;
		return c;
	}
}

class Edge implements Comparable<Edge> {

	int from;
	int to;
	int w;

	Edge(int f, int t, int w) {
		from = f;
		to = t;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		if (w < o.w)
			return -1;
		else if (w == o.w)
			return 0;
		else
			return 1;
	}
}