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
		
		arr = new int[n];
		str = br.readLine();
		stk = new StringTokenizer(str);
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(arr);
		
		for (int i = 1; i < (1 << n); i++) {
			if (sum(i) == m) {
				count++;
			}
		}
		sb.append(count + "\n");
	}
	
	public static int sum(int bitmask) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			if ((bitmask & 1) == 1) {
				sum += arr[i];
			}
			bitmask >>= 1;
		}
		return sum;
	}
}