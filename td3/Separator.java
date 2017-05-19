import java.util.*;
import java.io.*;

class Couple {
    int x;
    int y;
    public Couple(int x_, int y_) {
        x = x_;
        y = y_;
    }
    public List<Couple> voisins(int[][] dist, int maxi) {
        List<Couple> l = new LinkedList<Couple>();
        int n = dist.length;
        int m = dist[0].length;
        if (y + 1 < m && dist[x][y + 1] == maxi) {
            l.add(new Couple(x, y + 1));
        }
        if (y - 1 >= 0 && dist[x][y - 1] == maxi) {
            l.add(new Couple(x, y - 1));
        }
        if (x + 1 < n && dist[x + 1][y] == maxi) {
            l.add(new Couple(x + 1, y));
        }
        return l;
    }
}

class Main {

    public static void printTab(boolean[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i);
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j]) {
                    System.out.print("X");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int debug = -1;

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        int compteur = 0;
        while (n != 0 || m != 0) {
            compteur++;
            String[] s = new String[n];
            for (int i = 0; i < n; i++) {
                s[i] = in.nextLine();
                if (compteur == debug) {
                    System.out.println(s[i]);
                }
            }
            // On met les noirs à gauche
            if (s[0].charAt(0) == 'B') {
                for (int i = 0; i < n; i++) {
                    s[i] = new StringBuilder(s[i]).reverse().toString();
                }
            }
            StringBuilder[] lignes = new StringBuilder[n];
            for (int i = 0; i < n; i++) {
                lignes[i] = new StringBuilder(s[i]);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m - 1; j++) {
                    if (s[i].charAt(j) == 'S') {
                        lignes[i].setCharAt(j + 1, 'S');
                        if (i < n - 2 && j > 0 && s[i+1].charAt(j - 1) == 'S' && s[i+1].charAt(j) == 'B') {
                            lignes[i + 1].setCharAt(j, 'S');
                        }
                    }
               }
                s[i] = lignes[i].toString();
            }

			boolean[][] g = new boolean[n][m];
			int[][] dist = new int[n][m];
            int maxi = 100000;
            LinkedList<Couple> to_see = new LinkedList<Couple>();

			
			for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dist[i][j] = -1;
                    if (s[i].charAt(j) == 'S') {
                        g[i][j] = true;
                        dist[i][j] = maxi;
                        if (i == 0) {
                            dist[i][j] = 0;
                            to_see.addLast(new Couple(i, j));
                        }
                    }
                }
            }
            int distance = maxi;
            while (!to_see.isEmpty()) {
                Couple current = to_see.getFirst();
                to_see.remove(current);
                List<Couple> l = current.voisins(dist, maxi);
                for (Couple c: l) {
                    to_see.addLast(c);
                    dist[c.x][c.y] = dist[current.x][current.y] + 1;
                }
                if (distance == maxi && current.x == n - 1) {
                    distance = dist[current.x][current.y];
                }
            }
            //printTab(g);
            //System.out.println(Arrays.deepToString(dist));
            System.out.println(distance + 1);
            n = in.nextInt();
            m = in.nextInt();
            in.nextLine();
        }
        in.close();
    }

}
