import java.util.*;
import java.io.*;

class Skycraper {

    public static int[] pgcd_etendu(int a, int b, int u, int u_prime, int v, int v_prime) {
        if (b == 0) {
            int[] t = {u, v};
            return t;
        }
        return pgcd_etendu(b, a % b, u_prime, u - (a/b) * u_prime, v_prime, v - (a/b) * v_prime);
    }

    public static int pgcd(int a, int b) {
        if (b == 0)
            return a;
        return pgcd(b, a % b);
    }

    public static int relies(int xa, int ya, int xb, int yb) {
        int d = pgcd(xa, xb);
        int ecart = yb - ya;
        if (ecart % d != 0)
            return -1;
        int[] r = pgcd_etendu(xa / d, xb / d, 1, 0, 0, 1);
        int u = r[0] * (ecart / d);
        // int v = r[1] * (ecart / d);
        int k = (int) Math.ceil((float) (-u - (ya) / (float) xa) / (float) xb);
        int etage = ya + xa * (u + xb * k);
        return etage;
    }

    public static boolean reaches(int xa, int ya, int f) {
        return ((ya - f) % xa == 0);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int F = in.nextInt();
            int E = in.nextInt();
            int A = in.nextInt();
            int B = in.nextInt();
            int[] x = new int[E];
            int[] y = new int[E];
            for (int i = 0; i < E; i++) {
                x[i] = in.nextInt();
                y[i] = in.nextInt();
            }
            Set<Integer> vus = new HashSet<Integer>();
            Queue<Integer> to_see = new LinkedList<Integer>();
            Set<Integer> fin = new HashSet<Integer>();
            for (int i = 0; i < E; i++) {
                int x_local = x[i];
                int y_local = y[i];
                //System.out.println(x_local + "," + y_local);
                if (reaches(x_local, y_local, A))
                    to_see.add(i);
                if (reaches(x_local, y_local, B))
                    fin.add(i);
            }
            boolean atteint = false;
            while (!to_see.isEmpty() && !atteint) {
                int ascenseur = to_see.poll();
                for (int i = 0; i < E; i++) {
                    if (i != ascenseur && !vus.contains(i)) {
                        int f =  relies(x[ascenseur], y[ascenseur], x[i], y[i]);
                        if (f >= 0 && f < F) {
                            vus.add(i);
                            to_see.add(i);
                            if (fin.contains(i))
                                atteint = true;
                        }
                    }
                }
            }
            //System.out.println(vus);
            //System.out.println(fin);
            if (atteint)
                System.out.println("It is possible to move the furniture.");
            else
                System.out.println("The furniture cannot be moved.");
        }
        in.close();
    }

}
