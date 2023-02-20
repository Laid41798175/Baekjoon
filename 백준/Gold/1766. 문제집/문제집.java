import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stk;

	static int n;
	static int m;

	static Question[] qst;
	static int[] inDegree;
	static boolean[] isVisited;

	static String str;

	public static void main(String[] args) throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		qst = new Question[n + 1];
		for (int i = 1; i <= n; i++) {
			qst[i] = new Question(i);
		}
		inDegree = new int[n + 1];
		isVisited = new boolean[n + 1];

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
		System.out.println(sb);
	}
}

class Question implements Comparable<Question> {	
	Queue<Integer> q;
	int n;
	
	Question(int i){
		q = new LinkedList<>();
		n = i;
	}

	@Override
	public int compareTo(Question o) {
		if (q.size() == o.q.size()) {
			return n - o.n;
		}
		return q.size() - o.q.size();
	}
}