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

    public static void solve() throws IOException {
        str = br.readLine();
        if (str.length() == 1) {
            sb.append("-1\n");
            return;
        }

        if (isPalindrome(str)) {
            if (isPalindrome(str.substring(1))) {
                sb.append("-1\n");
                return;
            } else {
                sb.append(str.length() - 1).append('\n');
            }
        } else {
            sb.append(str.length()).append('\n');
        }
       
    }

    public static boolean isPalindrome(String str){
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

}