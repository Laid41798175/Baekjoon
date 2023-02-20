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
	
	static Question[] qst;
	static int[] inDegree;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}
	
	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		qst = new Question[n + 1];
		for (int i = 1; i <= n; i++) {
			qst[i] = new Question();
		}
		inDegree = new int[n + 1];

		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			qst[a].q.add(b);
			
			inDegree[b]++; // edge가 들어오는 수를 셈
		}		
		
		Queue<Integer> q = new PriorityQueue<>();		
		for (int i = 1; i <= n; i++) {
			if (inDegree[i] == 0) {
				q.add(i);
			}
		}
		
		Queue<Integer> ans = new LinkedList<>();
		while (!q.isEmpty()) {
			int curr = q.poll();
			Queue<Integer> currQ = qst[curr].q;
			while (!currQ.isEmpty()) {
				int to = currQ.poll();
				inDegree[to]--;
				if (inDegree[to] == 0) {
					q.add(to);
				}
			}
			ans.add(curr);
		}
		
		while (!ans.isEmpty()) {
			sb.append(ans.poll() + " ");
		}
	}
}

class Question {	
	Queue<Integer> q;
	
	Question(){
		q = new LinkedList<>();
	}
}