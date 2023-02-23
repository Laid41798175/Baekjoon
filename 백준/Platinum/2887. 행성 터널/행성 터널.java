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

	static Planet[] planet;
	static Queue<Tunnel> q = new PriorityQueue<>((t1, t2) -> t1.w - t2.w);
	static int[] parent;

	static int count = 0;
	static long sum = 0;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);
		planet = new Planet[n];
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			planet[i] = new Planet(i, Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()),
					Integer.parseInt(stk.nextToken()));
			parent[i] = i;
		}

		Arrays.sort(planet, (i, j) -> i.x - j.x);
		for (int i = 1; i < n; i++) {
			q.add(new Tunnel(planet[i - 1].index, planet[i].index, planet[i].x - planet[i - 1].x));
		}
		
		Arrays.sort(planet, (i, j) -> i.y - j.y);
		for (int i = 1; i < n; i++) {
			q.add(new Tunnel(planet[i - 1].index, planet[i].index, planet[i].y - planet[i - 1].y));
		}
		
		Arrays.sort(planet, (i, j) -> i.z - j.z);
		for (int i = 1; i < n; i++) {
			q.add(new Tunnel(planet[i - 1].index, planet[i].index, planet[i].z - planet[i - 1].z));
		}
		
		while (count < n - 1) {
			Tunnel t = q.poll();
			if (find(t.from) == find(t.to)) {
				continue;
			}
			union(t.from, t.to);
			sum += t.w;
			count++;
		}
		sb.append(sum).append("\n");
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

class Planet {
	int index;

	int x;
	int y;
	int z;

	Planet(int i, int a, int b, int c) {
		index = i;
		x = a;
		y = b;
		z = c;
	}
}

class Tunnel {
	int from;
	int to;

	int w;

	Tunnel(int f, int t, int w) {
		from = f;
		to = t;
		this.w = w;
	}
}