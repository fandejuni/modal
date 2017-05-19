import java.util.*;
import java.io.*;

class Main {

    public static double distance(double[] a, double[] b) {
        return Math.abs(b[0] - a[0]) + Math.abs(b[1] - a[1]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            in.nextLine();
            List<int[]> l1 = new LinkedList<int[]>();
            List<int[]> l2 = new LinkedList<int[]>();
            for (int i = 0; i < n; i++) {
                int[] pos = new int[2];
                pos[0] = in.nextInt();
                pos[1] = in.nextInt();
                l.add(pos);
                in.nextLine();
            }
            Collections.sort(l1, new Comparator<int[]>() {
                public int compare(int[] p, int[] q) {
                    return p[0] - q[0];
                }
            });
        }
        in.close();
    }
}
