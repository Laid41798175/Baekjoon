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

	static ArrayList<Island> islandList;
	static Island[] island;
	static int[] parent;
	static boolean[][] isIsland;

	static Queue<Edge> q;
	static int sum = 0;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.print(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		isIsland = new boolean[n][m];

		islandList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			for (int j = 0; j < m; j++) {
				int curr = Integer.parseInt(stk.nextToken());
				if (curr == 1) {
					islandList.add(new Island(i, j));
					isIsland[i][j] = true;
					count++;
				}
			}
		}

		island = new Island[islandList.size()];
		islandList.toArray(island);
		parent = new int[island.length];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		q = new PriorityQueue<Edge>();
		for (int i = 0; i < island.length - 1; i++) {
			for (int j = i + 1; j < island.length; j++) {
				int dst = island[i].dst(island[j]);
				if (dst != -1) {
					q.add(new Edge(i, j, dst));
				}
			}
		}

		while (!q.isEmpty()) {
			Edge e = q.poll();
			if (find(e.from) == find(e.to) || !isValid(e)) {
				continue;
			}
			union(e.from, e.to);
			sum += e.w;
			if (--count == 1) {
				break;
			}
		}	
		
		sb.append(count == 1 ? sum : -1).append("\n");
	}

	public static boolean isValid(Edge e) {
		if (e.w == 1) {
			return false;
		}
		
		Island f = island[e.from];
		Island t = island[e.to];
		if (f.x == t.x) {
			for (int i = Math.min(f.y, t.y) + 1; i < Math.max(f.y, t.y); i++) {
				if (isIsland[f.x][i]) {
					return false;
				}
			}
		} else {
			for (int i = Math.min(f.x, t.x) + 1; i < Math.max(f.x, t.x); i++) {
				if (isIsland[i][f.y]) {
					return false;
				}
			}
		}
		return true;
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

class Island {

	int x;
	int y;

	Island(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int dst(Island o) {
		if (x == o.x) {
			return Math.abs(y - o.y) - 1;
		} else if (y == o.y) {
			return Math.abs(x - o.x) - 1;
		} else {
			return -1; // not connected
		}
	}
}