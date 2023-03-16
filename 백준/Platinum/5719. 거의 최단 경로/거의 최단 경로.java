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
    static ArrayList<ArrayList<Edge>> list;
    static ArrayList<ArrayList<Edge>> route;
    static int minLength;

    static ArrayList<Edge> ban;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        while (true) {
            stk = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stk.nextToken());
            m = Integer.parseInt(stk.nextToken());
            if (n == 0 && m == 0) {
                break;
            }
            solve();
        }
        System.out.print(sb);
    }

    static int start;
    static int end;

    public static void solve() throws IOException {
        list = new ArrayList<>();
        route = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
            route.add(new ArrayList<>());
        }
        ban = new ArrayList<>();

        stk = new StringTokenizer(br.readLine());
        start = Integer.parseInt(stk.nextToken());
        end = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());   
            int w = Integer.parseInt(stk.nextToken());   
            list.get(from).add(new Edge(from, to, w));
        }

        // System.out.println(list.toString());

        dijkstra();

        isVisited = new boolean[n];
        ban(end);
        erase();
        dijkstra2();

        // System.out.println(list.toString());
        // System.out.println(ban.toString());
    }

    public static void dijkstra() {
		boolean[] isVisited = new boolean[n];
		int[] length = new int[n];
		Arrays.fill(length, 123456789);

		length[start] = 0;
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		pq.add(new Pair(0, start));		
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			if (isVisited[p.vertex]) {
                continue;
            }
			isVisited[p.vertex] = true;

            ArrayList<Edge> edges = list.get(p.vertex);
            for (Edge e : edges) {
                if (!isVisited[e.to] && length[p.vertex] + e.w < length[e.to]) {
					length[e.to] = length[p.vertex] + e.w;
					pq.add(new Pair(length[e.to], e.to));
                    route.get(e.to).clear();
                    route.get(e.to).add(e);
				} else if (length[p.vertex] + e.w == length[e.to]) {
                    route.get(e.to).add(e);
                }
            }
		}
        
        minLength = length[end];
	}

    public static void ban(int index) {
        if (index == start || isVisited[index]) {
            return;
        }
        isVisited[index] = true;

        for (Edge e : route.get(index)) {
            ban.add(e);
            ban(e.from);
        }
    }

    public static void erase() {
        for (ArrayList<Edge> edges : list) {
            edges.removeIf((Edge e) -> ban.contains(e));
        }
    }

    public static void dijkstra2() {
		boolean[] isVisited = new boolean[n];
		int[] length = new int[n];
		Arrays.fill(length, 123456789);

		length[start] = 0;
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		pq.add(new Pair(0, start));		
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			if (isVisited[p.vertex]) {
                continue;
            }
			isVisited[p.vertex] = true;

            ArrayList<Edge> edges = list.get(p.vertex);
            for (Edge e : edges) {
                if (!isVisited[e.to] && length[p.vertex] + e.w < length[e.to]) {
					length[e.to] = length[p.vertex] + e.w;
					pq.add(new Pair(length[e.to], e.to));
				}
            }
		}
        
        sb.append(length[end] == 123456789 ? -1 : length[end]).append('\n');
	}
}

class Pair implements Comparable<Pair> {
    int length;
    int vertex;

    Pair(int l, int v) {
        length = l;
        vertex = v;
    }

    public int compareTo(Pair o) {
        return length - o.length;
    }
}

class Edge {
    int from;   
    int to;
    int w;

    Edge (int f, int t, int w) {
        from = f;
        to = t;
        this.w = w;
    }

    public String toString(){
        return from + " -> " + to;
    }
}