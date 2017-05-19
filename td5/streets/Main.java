import java.util.*;
import java.io.*;

class Main {


    public static void dfs(int x, int[] parent, Map<Integer, List<Integer>> succ, int[] d, int[] low, int[] t, boolean[][] fait, List<int[]> streets) {
        d[x] = t[0];
        t[0] = t[0] + 1;
        for (int y: succ.get(x)) {
            if (!fait[x][y]) {
                fait[x][y] = true; 
                fait[y][x] = true; 
                int[] e = new int[2];
                e[0] = x;
                e[1] = y;
                streets.add(e);
            }
            if (d[y] == -1) {
                // On n'est pas encore passé
                parent[y] = x;
                dfs(y, parent, succ, d, low, t, fait, streets);
                low[x] = Math.min(low[x], low[y]);
            }
            else if (y != parent[x]) {
                // y n'est pas le père de x
                low[x] = Math.min(low[x], d[y]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        int k = 1;
        while (m != 0 || n != 0) {
            System.out.println(k);
            System.out.println();
            k++;
            Map<Integer, List<Integer>> succ = new HashMap<Integer, List<Integer>>();
            for (int i = 0; i < m; i++) {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                in.nextLine();
                if (!succ.containsKey(x))
                    succ.put(x, new LinkedList<Integer>());
                if (!succ.containsKey(y))
                    succ.put(y, new LinkedList<Integer>());
                succ.get(x).add(y);
                succ.get(y).add(x);
            }

            int[] d = new int[n];
            int[] low = new int[n];
            int[] parent = new int[n];
            Arrays.fill(d, -1);
            Arrays.fill(parent, -1);
            Arrays.fill(low, Integer.MAX_VALUE);
            int[] t = new int[1];
            boolean[][] fait = new boolean[n][n];
            List<int[]> streets = new LinkedList<int[]>();
            dfs(0, parent, succ, d, low, t, fait, streets);
            //System.out.println(Arrays.toString(d));
            //System.out.println(Arrays.toString(low));
            
            for (int[] e: streets) {
                int x = e[0];
                int y = e[1];
                System.out.println((x + 1) + " " + (y + 1));
                // On veut d[x] <= d[y]
                if (d[x] > d[y]) {
                    int temp = d[x];
                    d[x] = d[y];
                    d[y] = temp;
                }
                if (parent[y] == x && low[y] >= d[y]) {
                    System.out.println((y + 1) + " " + (x + 1));
                    //System.out.println("LIAISON !");
                    int[] a = new int[2];
                    a[0] = e[1];
                    a[1] = e[0];
                }
                //System.out.println(Arrays.toString(e));
            }

            System.out.println("#");

            n = in.nextInt();
            m = in.nextInt();
            in.nextLine();
        }
        in.close();
    }

}
