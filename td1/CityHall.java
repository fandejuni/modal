import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();

        for (int t = 0; t < T; t++) {

            int M = in.nextInt();
            int N = in.nextInt();
            in.nextLine();
            int[] r = new int[M];
            boolean[][] broken = new boolean[M][N];
            for (int i = 0; i < M; i++) {
                String s = in.nextLine();
                for (int j = 0; j < N; j++) {
                    if (s.charAt(j) == '0') {
                        broken[i][j] = true;
                    }
                }
            }
            
            // Traitement du problème
            for (int j = 0; j < N; j++) {
                int current = 0;
                for (int i = 0; i < M; i++) {
                    if (broken[i][j]) {
                        current++;
                    }
                    else {
                        if (current > 0) {
                            r[current - 1] += 1;
                        }
                        current = 0;
                    }
                }
                if (current > 0) {
                    r[current - 1] += 1;
                }
            }

            // Affichage
            for (int i = 0; i < M; i++) {
                if (r[i] > 0) {
                    System.out.println((i + 1) + " " + r[i]);
                }
            }
            System.out.println();
        }
        in.close();
    }

}
