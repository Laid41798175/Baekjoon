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

	static int[] arr;

	static int max = 0;
	static int maxIndex = -1;

	static ArrayList<Integer> lis = new ArrayList<>();
	static HashMap<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);
		Pair[] pr = new Pair[n];
		map = new HashMap<>();
		arr = new int[n + 1];

		for (int i = 0; i < n; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			pr[i] = new Pair(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
			map.put(pr[i].y, pr[i].x);
		}

		Arrays.sort(pr);
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			arr[i + 1] = pr[i].y;
			list.add(pr[i].x);
		}

		lis(arr);
		list.removeAll(lis);
		sb.append(list.size() + "\n");
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(iter.next()).append("\n");
		}
	}

	static void lis(int[] arr) {
		int[] prev = new int[n + 1];
		int[] aValue = new int[n + 1];
		int[] aIndex = new int[n + 1];

		max = 0;
		for (int i = 1; i <= n; i++) {
			int index = binarySearch(aValue, max, arr[i]);
			int ind = Arrays.binarySearch(aValue, 1, max + 1, arr[i]);

			aValue[index] = arr[i];
			prev[i] = aIndex[index - 1];
			aIndex[index] = i;

			if (max < index) {
				max = index;
				maxIndex = i;
			}
		}

		int[] ans = new int[max];
		int idx = 0;
		while (maxIndex != 0) {
			ans[idx++] = arr[maxIndex];
			maxIndex = prev[maxIndex];
		}
		for (int i = max - 1; i >= 0; i--) {
			// lis.add(ans[i]);
			lis.add(map.get(ans[i]));
		}
	}

	static int binarySearch(int[] a, int aMax, int value) { // a is sorted
		if (aMax == 0) {
			return 1;
		}

		int start = 1;
		int end = aMax; // inclusive
		while (start < end) {
			int half = (start + end) / 2;
			if (a[half] == value) {
				return half;
			} else if (a[half] < value) {
				start = half + 1;
			} else { // if (value < a[half]) {
				end = half;
			}
		}

		if (end == aMax && a[aMax] < value) {
			return aMax + 1;
		}

		return start;
	}
}

class Pair implements Comparable<Pair> {
	int x;
	int y;

	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Pair o) {
		if (x < o.x) {
			return -1;
		} else if (x == o.x) {
			return 0;
		} else {
			return 1;
		}
	}
}