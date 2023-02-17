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

	static Loc[] loc;
	static int[] parent;
	static int[] treeNum;

	static double sum = 0;

	public static void main(String[] args) throws IOException {
		solve();
		// System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());

		loc = new Loc[n + 1];
		parent = new int[n + 1];
		treeNum = new int[n + 1];

		Queue<Edge> edges = new PriorityQueue<>();

		for (int i = 1; i <= n; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			loc[i] = new Loc(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
			for (int j = 1; j < i; j++) {
				edges.add(new Edge(i, j, loc[i].dst(loc[j])));
			}
			parent[i] = i;
			treeNum[i] = 1;
		}

		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			union(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
		}

		while (!edges.isEmpty()) {
			Edge e = edges.poll();
			if (find(e.from) == find(e.to)) {
				continue;
			}
			union(e.from, e.to);
			sum += e.w;
			if (treeNum[find(e.to)] == n) {
				break;
			}
		}

		System.out.printf("%.2f", (double) Math.round(sum * 100) / 100);
	}

	public static int find(int a) {
		int c = a;
		while (c != parent[c]) {
			c = parent[c];
		}
		parent[a] = c;
		return c;
	}

	public static boolean isConnected(int a, int b) {
		return find(a) == find(b);
	}

	public static void union(int a, int b) {
		if (isConnected(a, b)) {
			return;
		}
		treeNum[find(b)] += treeNum[find(a)];
		parent[find(a)] = find(b);
	}
}

class Edge implements Comparable<Edge> {
	int from;
	int to;
	double w;

	Edge(int from, int to, double w) {
		this.from = from;
		this.to = to;
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

class Loc {
	long x;
	long y;

	Loc(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double dst(Loc o) {
		return Math.sqrt((x - o.x) * (x - o.x) + (y - o.y) * (y - o.y));
	}
}