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

	static int[] arr;
	static boolean[] isChecked;
	static int[] isVisited;

	public static void main(String[] args) throws IOException {
		str = br.readLine();
		T = Integer.parseInt(str);
		for (int t = 0; t < T; t++) {
			solve();
		}
		System.out.print(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);

		arr = new int[n + 1];
		isChecked = new boolean[n + 1];
		isVisited = new int[n + 1];

		str = br.readLine();
		stk = new StringTokenizer(str);
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}

		for (int i = 1; i <= n; i++) {
			if (!isChecked[i]) {
				beingTeam(i);
				checkTeam(i);
			}
		}

		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (isVisited[i] == 1) {
				count++;
			}
		}

		sb.append(count + "\n");
	}

	public static void beingTeam(int i) {
		if (isVisited[i] == 0) {
			isVisited[i]++;
			if (!isChecked[arr[i]]) {
				beingTeam(arr[i]);
			}
		} else if (isVisited[i] == 1) {
			isVisited[i]++;
			if (!isChecked[arr[i]]) {
				beingTeam(arr[i]);
			}
		}
	}

	public static void checkTeam(int i) {
		isChecked[i] = true;
		if (!isChecked[arr[i]]) {
			checkTeam(arr[i]);
		}
	}
}