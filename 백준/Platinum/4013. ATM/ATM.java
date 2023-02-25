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

	static int start;
	static int[] goal;

	static Vertex[] vertex;
	static boolean[] isVisited;
	static boolean[] isSCC;

	static Stack<Vertex> s;
	static ArrayList<ArrayList<Integer>> SCClist;

	static int count = 0;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		vertex = new Vertex[n + 1];
		for (int i = 1; i <= n; i++) {
			vertex[i] = new Vertex(i);
		}
		isVisited = new boolean[n + 1];
		isSCC = new boolean[n + 1];

		s = new Stack<>();
		SCClist = new ArrayList<>();

		int[][] edge = new int[m][2];
		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			edge[i][0] = Integer.parseInt(stk.nextToken());
			edge[i][1] = Integer.parseInt(stk.nextToken());
			vertex[edge[i][0]].edges.add(edge[i][1]);
		}

		for (int i = 1; i <= n; i++) {
			str = br.readLine();
			vertex[i].value = Integer.parseInt(str);
		}

		str = br.readLine();
		stk = new StringTokenizer(str);
		start = Integer.parseInt(stk.nextToken());
		goal = new int[Integer.parseInt(stk.nextToken())];

		str = br.readLine();
		stk = new StringTokenizer(str);
		for (int i = 0; i < goal.length; i++) {
			goal[i] = Integer.parseInt(stk.nextToken());
		}
		
		dfs(start);

		int[] toSCC = new int[n + 1];
		SCC[] sccs = new SCC[SCClist.size() + 1];
		for (int i = 1; i <= SCClist.size(); i++) {
			sccs[i] = new SCC();
			sccs[i].list = SCClist.get(SCClist.size() - i);
			Iterator<Integer> iter = sccs[i].list.iterator();
			while (iter.hasNext()) {
				int e = iter.next();
				sccs[i].total += vertex[e].value;
				toSCC[e] = i;
			}
		}

		for (int i = 0; i < m; i++) {
			int from = toSCC[edge[i][0]];
			int to = toSCC[edge[i][1]];
			if (from != 0 && to != 0 && from != to) {
				sccs[from].add(to);
			}
		}
		
		int[] length = new int[sccs.length];
		Arrays.fill(length, -1);
		length[1] = 0;

		PriorityQueue<Pair> pq = new PriorityQueue<>();

		pq.add(new Pair(0, 1));
		while (!pq.isEmpty()) {
			Pair p = pq.poll();

			Iterator<Integer> iter = sccs[p.scc].edges.iterator();
			while (iter.hasNext()) {
				int to = iter.next();
				if (length[p.scc] + sccs[p.scc].total > length[to]) {
					length[to] = length[p.scc] + sccs[p.scc].total;
					pq.add(new Pair(length[to], to));
				}
			}

		}

		for (int i = 1; i < length.length; i++) {
			if (length[i] != -1) {
				length[i] += sccs[i].total;
			}			
		}
		
		int max = -1;
		for (int i = 0; i < goal.length; i++) {
			if (toSCC[goal[i]] == 0) continue;
			max = Math.max(max, length[toSCC[goal[i]]]);
		}
		sb.append(max).append('\n');
	}

	// 타잔 알고리즘 - SCC
	public static int dfs(int curr) {
		vertex[curr].num = ++count; // 정점의 번호를 방문 순서대로 다시 붙여줌
		int ret = vertex[curr].num;
		s.push(vertex[curr]); // 스택에 넣는 번호는 index임 (꺼내서 다시 원래 번호를 추측할 필요가 없음)
		isVisited[curr] = true;
		for (int i = 0; i < vertex[curr].edges.size(); i++) {
			int to = vertex[curr].edges.get(i);
			if (isSCC[to]) { // 이미 scc의 요소라면 continue, 어차피 현재 노드는 그 scc의 요소가 절대 아님
				continue;
			}

			if (!isVisited[to]) {
				ret = Math.min(ret, dfs(to)); // 방문 순서대로 붙였기 때문에 최솟값이 가장 부모 노드
			} else {
				ret = Math.min(ret, vertex[to].num); // 대상을 방문했다면 방문 순서를 얻어오면 됨, 방문하지 않았다면 dfs로 호출해서 알아오기
			}
		}

		if (ret == vertex[curr].num) { // 본인이 ArrayList<Integer>에서 제일 낮은(먼저 방문한) 번호임
			ArrayList<Integer> scc = new ArrayList<Integer>();
			while (true) {
				Vertex p = s.pop();
				scc.add(p.index);
				isSCC[p.index] = true;
				if (p.index == curr) { // 본인을 만날때까지 스택에서 꺼냄
					break;
				}
			}
			SCClist.add(scc);
		}

		return ret; // vertex[curr].num 의 최솟값을 반복해 제일 부모 노드를 리턴.
	}
}

class Pair implements Comparable<Pair> {
	int length;
	int scc;

	Pair(int length, int index) {
		this.length = length;
		scc = index;
	}

	@Override
	public int compareTo(Pair o) {
		return length - o.length;
	}
}

class SCC {
	int total;

	ArrayList<Integer> list;
	Set<Integer> edges;

	SCC() {
		total = 0;
		list = new ArrayList<>();
		edges = new HashSet<>();
	}

	public void add(int to) {
		edges.add(to);
	}

	public String toString() {
		return "total: " + total + "\nlist:" + list.toString() + "\nedges:" + edges.toString() + "\n";
	}
}

class Vertex {
	int index;
	int num;

	int value = 0;
	ArrayList<Integer> edges;

	Vertex(int i) {
		index = i;
		edges = new ArrayList<>();
	}
}