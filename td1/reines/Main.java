import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            in.nextLine();
            List<int[]> reines = new LinkedList<int[]>();
            for (int i = 0; i < N; i++) {
                String l = in.nextLine();
                for (int j = 0; j < l.length(); j++) {
                    if (l.charAt(j) == '#') {
                        int[] a = {i, j};
                        reines.add(a);
                    }
                }
            }
            boolean valid = true;
            for (int[] a: reines) {
                for (int[] b: reines) {
                    int x1 = a[0];
                    int x2 = b[0];
                    int y1 = a[1];
                    int y2 = b[1];
                    if (x1 != x2 || y1 != y2) {
                        valid = valid && x1 != x2 && y1 != y2; // Ligne, colonne
                        valid = valid && x1 + y1 != x2 + y2;
                        valid = valid && x1 - y1 != x2 - y2;
                    }
                }
            }
            if (valid) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
        in.close();
    }

}
