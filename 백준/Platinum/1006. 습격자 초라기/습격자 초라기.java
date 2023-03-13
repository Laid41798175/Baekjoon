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

    static int[] arr;
    static int[] dp0; // 둘다 사용 불가
    static int[] dp1; // 아래 사용 불가, 위 사용 가능
    static int[] dp2; // 아래 사용 가능, 위 사용 불가
    static int[] dp3; // 둘다 사용 가능

    static int X = 123456789;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            solve();
        }
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new int[2 * n + 1];
        dp0 = new int[n + 1];
        dp1 = new int[n + 1];
        dp2 = new int[n + 1];
        dp3 = new int[n + 1];

        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[n + i] = Integer.parseInt(stk.nextToken());
        }

        if (n == 1) {
            sb.append(arr[1] + arr[2] <= m ? 1 : 2).append('\n');
            return;
        }

        int ans = min(cand0(), cand1(), cand2(), cand3());
        if (ans == X) {
            int c = 1/0;
        }
        sb.append(ans + "\n");
    }

    public static int cand0(){ 
        if (arr[1] + arr[n + 1] <= m) {
            dp0[1] = 1;            
        } else {
            dp0[1] = X;
        }
        dp1[1] = X;
        dp2[1] = X;
        dp3[1] = 2;

        // 2부터 n까지
        for (int i = 2; i <= n; i++) {
            if (arr[i] + arr[i - 1] <= m) {
                if (arr[n + i] + arr[n + i - 1] <= m) {
                    if (arr[i] + arr[n + i] <= m) {
                        /// 모두 가능한 경우의 수
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1]);
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = dp3[i - 1];
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                } else { /// 위가 옆으로 불가능
                    if (arr[i] + arr[n + i] <= m) {                        
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                }
            } else { /// 아래가 옆으로 불가능
                if (arr[n + i] + arr[n + i - 1] <= m) {
                    if (arr[i] + arr[n + i] <= m) {
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = X;
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = X;
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                } else { /// 위가 옆으로 불가능
                    if (arr[i] + arr[n + i] <= m) {
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = X;
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = X;
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                }
            }
        }

        return min(n);
    }

    public static int cand1(){ 
        if (arr[1] + arr[n] > m) {
            return X;
        }

        dp0[1] = X;
        dp1[1] = 2;
        dp2[1] = X;
        dp3[1] = X;

        // 2부터 n - 1까지
        for (int i = 2; i <= n - 1; i++) {
            if (arr[i] + arr[i - 1] <= m) {
                if (arr[n + i] + arr[n + i - 1] <= m) {
                    if (arr[i] + arr[n + i] <= m) {
                        /// 모두 가능한 경우의 수
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1]);
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = dp3[i - 1];
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                } else { /// 위가 옆으로 불가능
                    if (arr[i] + arr[n + i] <= m) {                        
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                }
            } else { /// 아래가 옆으로 불가능
                if (arr[n + i] + arr[n + i - 1] <= m) {
                    if (arr[i] + arr[n + i] <= m) {
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = X;
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = X;
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                } else { /// 위가 옆으로 불가능
                    if (arr[i] + arr[n + i] <= m) {
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = X;
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = X;
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                }
            }
        }

        if (arr[2 * n - 1] + arr[2 * n] <= m) {
            return min (dp0[n - 1] + 1, dp1[n - 1], dp2[n - 1] + 1, dp3[n - 1]);
        } else {
            return min (n - 1) + 1;
        }
    }

    public static int cand2(){ 
        if (arr[n + 1] + arr[2 * n] > m) {
            return X;
        }

        dp0[1] = X;
        dp1[1] = X;
        dp2[1] = 2;
        dp3[1] = X;

        // 2부터 n - 1까지
        for (int i = 2; i <= n - 1; i++) {
            if (arr[i] + arr[i - 1] <= m) {
                if (arr[n + i] + arr[n + i - 1] <= m) {
                    if (arr[i] + arr[n + i] <= m) {
                        /// 모두 가능한 경우의 수
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1]);
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = dp3[i - 1];
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                } else { /// 위가 옆으로 불가능
                    if (arr[i] + arr[n + i] <= m) {                        
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                }
            } else { /// 아래가 옆으로 불가능
                if (arr[n + i] + arr[n + i - 1] <= m) {
                    if (arr[i] + arr[n + i] <= m) {
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = X;
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = X;
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                } else { /// 위가 옆으로 불가능
                    if (arr[i] + arr[n + i] <= m) {
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = X;
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = X;
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                }
            }
        }

        if (arr[n - 1] + arr[n] <= m) {
            return min (dp0[n - 1] + 1, dp1[n - 1] + 1, dp2[n - 1], dp3[n - 1]);
        } else {
            return min (n - 1) + 1;
        }
    }

    public static int cand3(){ 
        if (arr[1] + arr[n] > m || arr[n + 1] + arr[2 * n] > m) {
            return X;
        }

        dp0[1] = 2;
        dp1[1] = X;
        dp2[1] = X;
        dp3[1] = X;

        // 2부터 n - 1까지
        for (int i = 2; i <= n - 1; i++) {
            if (arr[i] + arr[i - 1] <= m) {
                if (arr[n + i] + arr[n + i - 1] <= m) {
                    if (arr[i] + arr[n + i] <= m) {
                        /// 모두 가능한 경우의 수
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1]);
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = dp3[i - 1];
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                } else { /// 위가 옆으로 불가능
                    if (arr[i] + arr[n + i] <= m) {                        
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = min(dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                }
            } else { /// 아래가 옆으로 불가능
                if (arr[n + i] + arr[n + i - 1] <= m) {
                    if (arr[i] + arr[n + i] <= m) {
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = X;
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = X;
                        dp2[i] = min(dp1[i - 1] + 1, dp3[i - 1] + 1);
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                } else { /// 위가 옆으로 불가능
                    if (arr[i] + arr[n + i] <= m) {
                        dp0[i] = min(dp0[i - 1] + 1, dp1[i - 1] + 1, dp2[i - 1] + 1, dp3[i - 1] + 1);
                        dp1[i] = X;
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    } else { /// 위아래로 불가능
                        dp0[i] = X;
                        dp1[i] = X;
                        dp2[i] = X;
                        dp3[i] = min(dp0[i - 1] + 2, dp1[i - 1] + 2, dp2[i - 1] + 2, dp3[i - 1] + 2);
                    }
                }
            }
        }
        return min (n - 1);       
    }

    public static int min(int index) {
        int i = Math.min(dp0[index], dp1[index]);
        int j = Math.min(dp2[index], dp3[index]);
        return Math.min(i, j);
    }

    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    public static int min(int a, int b, int c) {
        int i = Math.min(a, b);
        return Math.min(i, c);
    }

    public static int min(int a, int b, int c, int d) {
        int i = Math.min(a, b);
        int j = Math.min(c, d);
        return Math.min(i, j);
    }

}