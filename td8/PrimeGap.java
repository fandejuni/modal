import java.util.*;
import java.io.*;

class PrimeGap {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 1299709 + 1;
        boolean[] isPrime = new boolean[n];
        for (int i = 0; i < n; i++)
            isPrime[i] = true;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                int k = 2;
                while (k * i < n) {
                    isPrime[k * i] = false;
                    k++;
                }
            }
        }
        isPrime[0] = false;
        isPrime[1] = false;
        int k = in.nextInt();
        while (k != 0) {
            if (isPrime[k]) {
                System.out.println(0);
            }
            else {
                int bas = 1;
                while (!isPrime[k - bas])
                    bas++;
                int haut = 1;
                while (!isPrime[k + haut])
                    haut++;
                System.out.println(haut + bas);
            }
            k = in.nextInt();
        }
        in.close();
    }

}
