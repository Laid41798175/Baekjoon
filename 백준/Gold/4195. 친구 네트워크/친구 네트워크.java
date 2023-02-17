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

	static HashMap<String, Integer> map;
	static int[] parent;
	static int[] numFriends;

	static int count = 0;

	public static void main(String[] args) throws IOException {
		str = br.readLine();
		T = Integer.parseInt(str);
		for (int t = 0; t < T; t++) {
			solve();
		}
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);
		map = new HashMap<>();
		parent = new int [200001];
		numFriends = new int [200001];
		int index = 0;
		for (int i = 0; i < n; i++) {
			str = br.readLine();
			String[] spl = str.split(" ");
			String name0 = spl[0];
			String name1 = spl[1];
			if (!map.containsKey(name0)) {
				map.put(name0, ++index);
				parent[index] = index;
				numFriends[index] = 1;
			}
			if (!map.containsKey(name1)) {
				map.put(name1, ++index);
				parent[index] = index;
				numFriends[index] = 1;
			}
			union(map.get(name0), map.get(name1));
			sb.append(numFriend(map.get(name0)) + "\n");
		}
	}
	
	static int numFriend(int x) {
		return numFriends[topNode(x)];
	}
	
	static void union(int x, int y) {
		if (isConnected(x, y)) {
			return;
		}
		
		numFriends[x] = numFriends[topNode(x)];
		parent[topNode(x)] = x;
		numFriends[x] += numFriends[topNode(y)];
		parent[topNode(y)] = y;
		parent[x] = x;
		parent[y] = x;
	}
	
	static boolean isConnected(int x, int y) {
		return topNode(x) == topNode(y);
	}	
	
	static int topNode(int x) {
		int c = x;
		while (parent[c] != c) {
			c = parent[c];
		}
		parent[x] = c;
		return c;
	}
}