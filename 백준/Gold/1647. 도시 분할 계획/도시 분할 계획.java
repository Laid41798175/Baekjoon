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

	static int x;
	static int y;

	static int[] parent;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.print(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		Queue<Edge> q = new PriorityQueue<>(); // Heap Sort
		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			q.add(new Edge(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()),
					Integer.parseInt(stk.nextToken())));
		}

		int count = 0;
		int sum = 0;
		while (count < n - 2) { // MST의 Edge 개수
			Edge e = q.poll();			
			if (find(e.from) == find(e.to)) {
				continue;
			} else {
				union(e.from, e.to);
				sum += e.w;
				count++;
			}			
		}
		sb.append(sum + "\n");
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

class Edge implements Comparable<Edge> {
	int from;
	int to;
	int w;

	Edge(int f, int t, int w) {
		from = f;
		to = t;
		this.w = w;
	}

	public int compareTo(Edge o) {
		return w - o.w;
	}
}