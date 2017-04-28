import java.util.*;
import java.io.*;

class Tree {

    int prof;
    int[] r;
    Tree parent;
    boolean vu_zero;

    public Tree(int p, Tree par, int[] s) {
        //System.out.println("Arbre : " + p);
        //System.out.println(Arrays.toString(s));
        prof = p;
        parent = par;
        if (prof > s[0]) {
            s[0] = prof;
            s[1] = s[2];
        }
        r = s;
        vu_zero = false;
        s[2]++;
    }

    public Tree remonter() {
        if (vu_zero) {
            return parent.remonter();
        }
        else {
            vu_zero = true;
            return this;
        }
    }

    public Tree solve(char x) {
        if (x == '1') {
            Tree fils = new Tree(prof + 1, this, r);
            return fils;
        }
        else {
            return remonter();
        }
    }
}

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            in.nextLine();
            String s = in.nextLine();
            int[] r = new int[3];
            Tree mon_arbre = new Tree(0, null, r);
            for (int i = 0; i < s.length(); i++) {
                mon_arbre = mon_arbre.solve(s.charAt(i));
            }
            System.out.println(r[1]);
        }
        in.close();
    }

}
