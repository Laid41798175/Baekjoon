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
	static ArrayList<SCC> SCClist = new ArrayList<>();

	static int count;

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

		s = new Stack<>();
		SCClist = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			vertex[Integer.parseInt(stk.nextToken())].edges.add(Integer.parseInt(stk.nextToken()));
		}

		count = 0;
		for (int i = 1; i <= n; i++) {
			if (isVisited[i]) {
				continue;
			}
			dfs(i);
		}
		isVisited = new boolean[n + 1]; // reset

		int index = SCClist.size() - 1;
		int ans = 0;
		while (true) {
			if (isVisited[SCClist.get(index).list.get(0)]) {
				index--;
				continue;
			} else {
				dfsDomino(SCClist.get(index).list.get(0));
				index--;
			}

			ans++;
			if (count == 0) {
				break;
			}
		}
		sb.append(ans + "\n");
	}

	public static void dfsDomino(int curr) {
		if (isVisited[curr]) {
			return;
		}
		isVisited[curr] = true;
		count--;

		for (int i = 0; i < vertex[curr].edges.size(); i++) {
			int to = vertex[curr].edges.get(i);
			dfsDomino(to);
		}
	}

	public static int dfs(int curr) {
		vertex[curr].num = ++count; // 정점의 번호를 방문 순서대로 다시 붙여줌
		int ret = vertex[curr].num;
		s.push(curr); // 스택에 넣는 번호는 index임 (꺼내서 다시 원래 번호를 추측할 필요가 없음)
		isVisited[curr] = true;
		for (int i = 0; i < vertex[curr].edges.size(); i++) {
			int to = vertex[curr].edges.get(i);
			if (isSCC[to]) {
				continue;
			}

			if (!isVisited[to]) {
				ret = Math.min(ret, dfs(to)); // 방문 순서대로 붙였기 때문에 최솟값이 가장 부모 노드
			} else {
				ret = Math.min(ret, vertex[to].num); // 대상이 방문했다면 방문 순서를 얻어오면 됨
			}
		}

		if (ret == vertex[curr].num) { // 본인이 SCC에서 제일 낮은(먼저 방문한) 번호임
			SCC scc = new SCC();
			while (true) {
				int p = s.pop();
				scc.list.add(p);
				isSCC[p] = true;
				if (p == curr) { // 본인을 만날때까지 스택에서 꺼냄
					break;
				}
			}
			SCClist.add(scc);
		}

		return ret;
	}
}

class SCC {
	ArrayList<Integer> list;

	SCC() {
		list = new ArrayList<>();
	}
}

class Vertex {
	int num;
	ArrayList<Integer> edges;

	Vertex() {
		edges = new ArrayList<>();
	}
}