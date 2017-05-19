import java.util.*;
import java.io.*;

class Main {

    public static int level_2(int n) {
        int power = (int) Math.floor(Math.log(n) / Math.log(2));
        System.out.println("Power : "+ power);
        return (int) Math.pow(2.0, power);
    }

    public static int next(int n, int m) {
        System.out.println(n + ", " + m);
        if (n == 0 && m == 0) {
            return 1;
        }
        else if (n == 0 || m == 0) {
            System.out.println("A");
            return 0;
        }
        else if (m > n) {
            System.out.println("B");
            return 0;
        }
        else if (m % 2 == 0) {
            return next(m - 1, n -1);
        }
        int floor = level_2(m);
        System.out.println(floor);
        int n_ = (n - m) % floor;
        if (n_ >= floor / 2) {
            System.out.println("C");
            return 0;
        }
        return next(n_ + m - floor, m - floor);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            int M = in.nextInt();
            System.out.println("OK : " + next(N, M));
            in.nextLine();
            int[] index = new int[M];
            for (int i = 0; i < M; i++) {
                index[i] = i % 10;
            }
            int[][] s = new int[N][M];
            System.out.println(Arrays.toString(index));
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    s[n][m] = 0;
                    if (m == 0 && n == 0) {
                        s[n][m] = 1;
                    }
                    else if (n > 0 && m > 0) {
                        s[n][m] = (m * s[n - 1][m] + s[n-1][m-1]) % 2;
                    }
                    if (s[n][m] == 1) {
                        System.out.print("X");
                    }
                    else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
        in.close();
    }

}
