import java.util.*;
import java.io.*;

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        in.nextLine();

        int size = 200;
        int maxi = 50000;

        while (M != -1 || N != -1) {

            boolean[][] w_droite = new boolean[size][size];
            boolean[][] w_haut = new boolean[size][size];
            
            for (int i = 0; i < M; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int d = in.nextInt();
                int t = in.nextInt();
                in.nextLine();
                for (int j = 0; j < t; j++) {
                    if (d == 0) {
                        w_haut[x + j][y - 1] = true;
                    }
                    else {
                        w_droite[x - 1][y + j] = true;
                    }
                }
            }

            boolean[][] d_droite = new boolean[size][size];
            boolean[][] d_haut = new boolean[size][size];

            for (int i = 0; i < N; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int d = in.nextInt();
                in.nextLine();
                if (d == 0) {
                    d_haut[x][y - 1] = true;
                }
                else {
                    d_droite[x - 1][y] = true;
                }
            }

            float x_nemo = in.nextFloat();
            float y_nemo = in.nextFloat();
            in.nextLine();

            int[][] distance = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++){
                    distance[i][j] = maxi;
                }
            }
            boolean[][] vu = new boolean[size][size];
            Deque<Point> q = new LinkedList<Point>();
            
            q.add(new Point(0, 0));
            vu[0][0] = true;
            distance[0][0] = 0;

            while (!q.isEmpty()) {
                Point p = q.pollFirst();

                List<Point> voisinsDirects = new LinkedList<Point>();
                List<Point> voisinsIndirects = new LinkedList<Point>(); // séparés par une porte
                
                // Gauche
                if (p.x - 1 >= 0) {
                    Point voisin = new Point(p.x - 1, p.y);
                    if (d_droite[p.x - 1][p.y]) {
                        voisinsIndirects.add(voisin);
                    }
                    else if (!w_droite[p.x - 1][p.y]) {
                        voisinsDirects.add(voisin);
                    }
                }

                // Droite
                if (p.x + 1 <= size - 1) {
                    Point voisin = new Point(p.x + 1, p.y);
                    if (d_droite[p.x][p.y]) {
                        voisinsIndirects.add(voisin);
                    }
                    else if (!w_droite[p.x][p.y]) {
                        voisinsDirects.add(voisin);
                    }
                }

                // Haut
                if (p.y + 1 <= size - 1) {
                    Point voisin = new Point(p.x, p.y + 1);
                    if (d_haut[p.x][p.y]) {
                        voisinsIndirects.add(voisin);
                    }
                    else if (!w_haut[p.x][p.y]) {
                        voisinsDirects.add(voisin);
                    }
                }

                // Bas
                if (p.y - 1 >= 0) {
                    Point voisin = new Point(p.x, p.y - 1);
                    if (d_haut[p.x][p.y - 1]) {
                        voisinsIndirects.add(voisin);
                    }
                    else if (!w_haut[p.x][p.y - 1]) {
                        voisinsDirects.add(voisin);
                    }
                }

                for (Point point: voisinsDirects) {
                    if (!vu[point.x][point.y]) {
                        vu[point.x][point.y] = true;
                        q.addFirst(point);
                    }
                    int d = distance[p.x][p.y];
                    distance[point.x][point.y] = Math.min(d, distance[point.x][point.y]);
                }

                for (Point point: voisinsIndirects) {
                    if (!vu[point.x][point.y]) {
                        vu[point.x][point.y] = true;
                        q.addLast(point);
                    }

                    int d = distance[p.x][p.y];
                    distance[point.x][point.y] = Math.min(d + 1, distance[point.x][point.y]);
                }

            }
            /*
            for (int j = size - 1; j >= 0; j--) {
                for (int i = 0; i < size; i++) {
                    if (distance[i][j] == maxi) {
                        System.out.print("X ");
                    }
                    else {
                        System.out.print(distance[i][j] + " ");
                    }
                }
                System.out.println();
            }
            */
            int result = -1;
            int x = (int) Math.floor(x_nemo);
            int y = (int) Math.floor(y_nemo);
            if (x >= 0 && x < size && y >= 0 && y < size)  {
                if (distance[x][y] < maxi) {
                    result = distance[x][y];
                }
            }
            else {
                result = 0;
            }
            System.out.println(result);

            M = in.nextInt();
            N = in.nextInt();
            in.nextLine();

        }
            
        in.close();
    }

}
