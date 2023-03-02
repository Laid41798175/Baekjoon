
//Do Not Modify From
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String str = "";
    static StringTokenizer stk;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T;
    static int n;
    static int m;
    // Do Nod Modify To

    public static void main(String[] args) throws IOException {
        solve();
        System.out.print(sb);
    }

    static int[] arr;

    public static void solve() throws IOException {
        str = br.readLine();
        stk = new StringTokenizer(str);
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < m; i++) {
            str = br.readLine();
            stk = new StringTokenizer(str);
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            rotate(a, b, c);
        }

        print();
    }

    static void print(){
        for (int i = 1; i <= n; i++) {
            sb.append(arr[i] + " ");
        }
        sb.append('\n');
    }

    static void rotate(int a, int b, int c){
        int[] cpy = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cpy[i] = arr[i];
        }
        for (int i = 0; i <= b - a; i++) {
            if (c + i > b) {
                c -= (b - a + 1);
            }
            arr[a + i] = cpy[c + i];
        }
    }

    static void swap(int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}