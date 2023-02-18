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

	static Vertex[] vertex;
	static boolean[] isVisited;
	static boolean[] isSCC;

	static Stack<Integer> s = new Stack<>();
	static PriorityQueue<SCC> SCClist = new PriorityQueue<>();

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

		vertex = new Vertex[n + 1];
		for (int i = 1; i <= n; i++) {
			vertex[i] = new Vertex();
		}
		isVisited = new boolean[n + 1];
		isSCC = new boolean[n + 1];

		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			vertex[Integer.parseInt(stk.nextToken())].edges.add(new Edge(Integer.parseInt(stk.nextToken())));
		}

		for (int i = 1; i <= n; i++) {
			if (isVisited[i]) {
				continue;
			}
			dfs(i);
		}

		sb.append(SCClist.size() + "\n");
		while (!SCClist.isEmpty()) {
			SCC scc = SCClist.poll();
			while (!scc.list.isEmpty()) {
				sb.append(scc.list.poll() +" ");
			}
			sb.append("-1\n");
		}
		sb.append("\n");
	}

	public static int dfs(int curr) {
		vertex[curr].num = ++count;
		int ret = vertex[curr].num;
		s.push(curr);
		isVisited[curr] = true;
		while (!vertex[curr].edges.isEmpty()) {
			Edge e = vertex[curr].edges.poll();
			if (!isVisited[e.to]) {
				ret = Math.min(ret, dfs(e.to));
			}
			if (!isSCC[e.to]) {
				ret = Math.min(ret, vertex[e.to].num);
			}
		}

		if (ret == vertex[curr].num) {
			SCC scc = new SCC();
			while (true) {
				int p = s.pop();
				scc.list.add(p);
				isSCC[p] = true;
				if (p == curr) {
					break;
				}
			}
			SCClist.add(scc);
		}

		return ret;
	}
}

class SCC implements Comparable<SCC> {
	PriorityQueue<Integer> list;

	SCC() {
		list = new PriorityQueue<>();
	}

	@Override
	public int compareTo(SCC o) {
		return list.peek() - o.list.peek();
	}
}

class Vertex {

	int num;
	Queue<Edge> edges;

	Vertex() {
		edges = new LinkedList<>();
	}
}

class Edge implements Comparable<Edge> {
	int to;
	int w;

	Edge(int t) {
		to = t;
		w = 1;
	}

	Edge(int t, int w) {
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