
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

    static double sum = 0;
    static double total = 0;

    public static void solve() throws IOException {
        for (int i = 0; i < 20; i++) {
            str = br.readLine();
            stk = new StringTokenizer(str);
            stk.nextToken();
            double a = Double.parseDouble(stk.nextToken());
            String grade = stk.nextToken();

            if (grade.charAt(0) == 'P') {
                continue;
            }

            double b = gradeToDouble(grade);

            sum += a * b;
            total += a;
        }
        sb.append(sum / total).append('\n');
    }

    static double gradeToDouble(String str) {
        if (str.length() == 1) {
            return 0;
        } else {
            double base = 0;
            switch (str.charAt(0)) {
                case 'A':
                base = 4;
                break;
                case 'B':
                base = 3;
                break;
                case 'C':
                base = 2;
                break;
                case 'D':
                base = 1;
                break;
            }
            if (str.charAt(1) == '+') {
                base += 0.5;
            }
            return base;
        }
    }

    static void print(int i, int n){
        for (int j = 0; j < (n - i); j++){
            sb.append(' ');
        }
        for (int j = 0; j < 2 * i - 1; j++) {
            sb.append('*');
        }
        sb.append('\n');
    }
}