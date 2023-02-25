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

	static int count;
	static Stack<Integer> s;
	static ArrayList<ArrayList<Integer>> SCClist;

	public static void main(String[] args) throws IOException {
		str = br.readLine();
		T = Integer.parseInt(str);
		for (int t = 0; t < T; t++) {
			if (t != 0) {
				br.readLine();
			}
			solve();
		}
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		vertex = new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertex[i] = new Vertex();
		}
		isVisited = new boolean[n];
		isSCC = new boolean[n];

		count = 0;
		s = new Stack<>();
		SCClist = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			vertex[Integer.parseInt(stk.nextToken())].edges.add(Integer.parseInt(stk.nextToken()));
		}

		for (int i = 0; i < n; i++) {
			if (isVisited[i]) {
				continue;
			}
			dfs(i);
		}
		
		ArrayList<Integer> ans = SCClist.get(SCClist.size() - 1);
		int[] arr = new int[ans.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ans.get(i);
		}
		Arrays.sort(arr);
		
		isVisited = new boolean[n];
		for (int i = 0; i < ans.size(); i++) {
			traversal(arr[i]);
		}
		
		for (int i = 0; i < n; i++) {
			if (!isVisited[i]) {
				sb.append("Confused\n\n");
				return;
			}
		}		
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]).append("\n");
		}
		sb.append("\n");
	}

	public static void traversal(int curr) {
		if (isVisited[curr]) {
			return;
		}		
		isVisited[curr] = true;
		Iterator<Integer> iter = vertex[curr].edges.iterator();
		while (iter.hasNext()) {
			traversal(iter.next());
		}
	}
	
	public static int dfs(int curr) {
		vertex[curr].num = count++; // 정점의 번호를 방문 순서대로 다시 붙여줌
		int ret = vertex[curr].num;
		s.push(curr); // 스택에 넣는 번호는 index임 (꺼내서 다시 원래 번호를 추측할 필요가 없음)
		isVisited[curr] = true;
		for (int i = 0; i < vertex[curr].edges.size(); i++) {
			int to = vertex[curr].edges.get(i);
			if (isSCC[to]) { // 이미 SCC의 요소라면 continue, 어차피 현재 노드는 그 SCC의 요소가 절대 아님
				continue;
			}

			if (!isVisited[to]) {
				ret = Math.min(ret, dfs(to)); // 방문 순서대로 붙였기 때문에 최솟값이 가장 부모 노드
			} else {
				ret = Math.min(ret, vertex[to].num); // 대상을 방문했다면 방문 순서를 얻어오면 됨, 방문하지 않았다면 dfs로 호출해서 알아오기
			}
		}

		if (ret == vertex[curr].num) { // 본인이 SCC에서 제일 낮은(먼저 방문한) 번호임
			ArrayList<Integer> scc = new ArrayList<>();
			while (true) {
				int p = s.pop();
				scc.add(p);
				isSCC[p] = true;
				if (p == curr) { // 본인을 만날때까지 스택에서 꺼냄
					break;
				}
			}
			SCClist.add(scc);
		}

		return ret; // vertex[curr].num 의 최솟값을 반복해 제일 부모 노드를 리턴.
	}
}

class Vertex {
	int num;
	ArrayList<Integer> edges;

	Vertex() {
		edges = new ArrayList<>();
	}
}