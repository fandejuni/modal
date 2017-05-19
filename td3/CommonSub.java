import java.util.*;
import java.io.*;

class Node {

    int a;
    int b;
    int height;

    List<Node> l;

    public static int h_max(List<Node> l) {
        int m = 0;
        for (Node x: l) {
            m = Math.max(x.height, m);
        }
        return m;
    }

    public Node(int a, int b, int height) {
        this.a = a;
        this.b = b;
        this.height = height;
        l = new LinkedList<Node>();
    }

    public String toString() {
        String s = "Node : ["+a+", "+b+"], "+height+"\n";
        if (!l.isEmpty()) {
            s += "Sous-noeuds\n";
            for (Node x: l) {
                s += x.toString();
            }
            s += "Fin des sous-noeuds\n";
        }
        return s;
    }

    boolean intersects(Node other) {
        //System.out.println("Intersects ("+ other.a+","+other.b+"), ("+a+", "+b+")");
        if (other.a < a) {
            return other.intersects(this);
        }
        return b >= other.a;
    }

    List<Node> intersection(List<Node> l) {
        List<Node> r = new LinkedList<Node>();
        for (Node x: l) {
            if (intersects(x)) {
                r.add(x);
            }
        }
        return r;
    }

    public static int[] extrema(List<Node> l) {
        int[] r = new int[4];
        // r = (a_min, h_a, b_max, h_b)
        Node first = l.get(0);
        r[0] = first.a;
        r[1] = first.height;
        r[2] = first.b;
        r[3] = first.height;
        for (Node x: l) {
            if (x.a < r[0]) {
                r[0] = x.a;
                r[1] = x.height;
            }
            if (x.b > r[2]) {
                r[2] = x.b;
                r[3] = x.height;
            }
        }
        return r;
    }

    void fusion(Node other) {
        List<Node> inter = other.intersection(l);
        //System.out.println(inter);
        l.removeAll(inter);
        if (!inter.isEmpty()) {
            int[] ext = Node.extrema(inter);
            //System.out.println(Arrays.toString(ext));
            if (ext[0] < other.a) {
                Node f = new Node(ext[0], other.a - 1, ext[1]);
                l.add(f);
            }
            if (ext[2] > other.b) {
                Node f = new Node(other.b + 1, ext[2], ext[3]);
                l.add(f);
            }
            int h = Node.h_max(inter);
            other.height += h;
        }
        height = Math.max(height, other.height);
        l.add(other);
    }

}

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int W = in.nextInt();
        in.nextLine();
        while (W != 0 || N != 0) {
            Node root = new Node(1, W, 0);
            for (int i = 0; i < N; i++) {
                Node n = new Node(in.nextInt(), in.nextInt(), in.nextInt());
                root.fusion(n);
                //System.out.println(i + " -> " + root);
            }
            System.out.println(root.height);

            N = in.nextInt();
            W = in.nextInt();
        }
        in.close();
    }

}
