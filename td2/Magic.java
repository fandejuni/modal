import java.util.*;
import java.io.*;

class Stick {

    int debut;
    int fin;

    public Stick(int x, int y) {
        debut = x;
        fin = y;
    }

    public String toString() {
        return "[" + debut + ", " + fin + "]";
    }

}

class Main {

    public static int solve(LinkedList<Stick> l) {
        int s = 0;
        while (l.size() > 1) {
            Stick x = l.remove(0);
            List<Stick> admissibles = new LinkedList<Stick>();
            for (Stick y: l) {
                if (x != y && x.fin <= y.debut) {
                    admissibles.add(y);
                }
            }
            if (admissibles.size() == 0) {
                s += 1;
            }
            else {
                Stick y = admissibles.remove(0);
                Stick z = new Stick(x.debut, y.fin);
                l.addFirst(z);
                l.removeLastOccurrence(y);
            }
        }
        return s + l.size();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            in.nextLine();
            LinkedList<Stick> l = new LinkedList<Stick>();
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                in.nextLine();
                l.add(new Stick(x, y));
            } 
            Collections.sort(l, new Comparator<Stick>() {
                public int compare(Stick a, Stick b) {
                    return a.debut - b.debut;
                }
            });
            System.out.println(solve(l));
        }
        in.close();
    }

}
