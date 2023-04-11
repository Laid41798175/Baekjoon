import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String str;
	static String[] spl;

	static int n;
	static int[] d;
	static int[][] cache;

	public static void main(String[] args) throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);
		
		d = new int[n + 1];
		
		str = br.readLine();
		spl = str.split(" ");
		d[0] = Integer.parseInt(spl[0]);
		d[1] = Integer.parseInt(spl[1]);
		
		for (int i = 2; i <= n; i++) {
			str = br.readLine();
			spl = str.split(" ");
			d[i] = Integer.parseInt(spl[1]);
		}
		
		cache = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(cache[i], -1);
		}
		for (int i = 0; i <= n; i++) {
			cache[i][i] = 0;
		}
		
		System.out.println(min(1, n));
	}
	
	public static int min(int start, int end) {
		if (cache[start][end] != -1) {
			return cache[start][end];
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = start; i < end; i++) {
			int tmp = min(start, i) + d[start - 1] * d[i] * d[end] + min(i + 1, end);
			if (tmp < min) min = tmp;
		}
		return cache[start][end] = min;
	}	
}