import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        in.nextLine();

        for (int g = 1; g <= T; g++) {

            int N = in.nextInt();
            int L = N*N;
            in.nextLine();
            int[][] grille = new int[L][L];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < L; j++) {
                    grille[i][j] = in.nextInt();
                }
                in.nextLine();
            }
            
            boolean b = true; // Valide pour le moment

            // Lignes et colonnes
            for (int i = 0; i < L; i++) {
                boolean[] numbers_row = new boolean[L];
                boolean[] numbers_column = new boolean[L];
                for (int j = 0; j < L; j++) {
                    int x = grille[i][j] - 1;
                    int y = grille[j][i] - 1;
                    if (x >= 0 && x < L && !numbers_row[x]) {
                        numbers_row[x] = true;
                    }
                    else {
                        b = false;
                    }
                    if (y >= 0 && y < L && !numbers_column[y]) {
                        numbers_column[y] = true;
                    }
                    else {
                        b = false;
                    }
                }
            }

            // Sous-matrices
            for (int I = 0; I < N; I++) {
                for (int J = 0; J < N; J++) {
                    boolean[] numbers = new boolean[L];
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            int x = grille[I * N + i][J * N + j] - 1;
                            if (x >= 0 && x < L && !numbers[x]) {
                                numbers[x] = true;
                            }
                            else {
                                b = false;
                            }
                        }
                    }
                }
            }
            
            if (b) {
                System.out.println("Case #" + g + ": Yes");
            }
            else {
                System.out.println("Case #" + g + ": No");
            }

        }
        in.close();
    }
}
