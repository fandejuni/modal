import java.util.*;
import java.io.*;

// WARNING
// On fait tout *2

class Edge {

    int weight, flow, residu_bfs;
    // flow : arithmétique de a vers b
    Node a, b;

    public Edge(Node n1, Node n2, int d) {
        a = n1;
        b = n2;
        weight = d;
        flow = 0;
        residu_bfs = 0;
    }

    public Node autre(Node moi) {
        if (a.equals(moi)) {
            return b;
        }
        return a;
    }

    public boolean isSource() {
        return (a.x == 0 && a.y == 0) || (b.x == 0 && b.y == 0);
    }

    public int residual() {
        return weight - flow;
    }

    public String toString() {
        return "E : " + a + " -> " + b + " (w : " + weight + ", f : " + flow +  ")";
    }

}

class Node {

    // M*2, N*2
    int M, N;
    int x, y;
    List<Edge> voisins;
    Edge parent_bfs;

    public int hashCode() {
        return x * N + y;
    }

    public boolean equals(Object o) {
        Node a = (Node) o;
        return a.x == x && a.y == y;
    }

    public Node(int M, int N, int x, int y) {
        this.M = M;
        this.N = N;
        this.x = x;
        this.y = y;
        voisins = new LinkedList<Edge>();
        parent_bfs = null;
    }

    public boolean isSource() {
        return x == 0 && y == 0;
    }

    public boolean isTarget() {
        return x == M && y == N;
    }

    public void addEdge(Edge e) {
        voisins.add(e);
    }

    public String toString() {
        return "N(" + x + "," + y + ")";
    }

    public static Node bfs(Node source) {
        Queue<Node> q = new LinkedList<Node>();
        Set<Node> vus = new HashSet<Node>();
        vus.add(source);
        q.add(source);
        Node target = null;
        while (!q.isEmpty() && target == null) {
            Node x = q.poll();
            if (x.isTarget()) {
                target = x;
            }
            else {
                for (Edge e: x.voisins) {
                    Node y = e.autre(x);
                    if (e.residual() > 0 && !vus.contains(y)) {
                        vus.add(y);
                        y.parent_bfs = e;
                        e.residu_bfs = e.residual();
                        q.add(y);
                    }
                }
            }
        }
        return target;
    }
}

class War {

    public static int maxFlow(Node source, Node fin) {
        Node target = Node.bfs(source);
        while (target != null) {
            // Il existe un chemin augmentant
            List<Edge> l = new LinkedList<Edge>();
            Edge current_e = target.parent_bfs;
            Node current_n = target;
            int min = current_e.residu_bfs;
            while (current_e != null) {
                min = Math.min(min, current_e.residu_bfs);
                l.add(current_e);
                current_n = current_e.autre(current_n);
                current_e = current_n.parent_bfs;
            }
            for (Edge e: l) {
                e.flow += min;
            }
            target = Node.bfs(source);
        }
        int s = 0;
        for (Edge e: fin.voisins) {
            s += e.flow;
        }
        return s;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        in.nextLine();
        while (M != 0 || N != 0) {
            Node[][] nodes = new Node[2 * (M + 1)][2 * (N + 1)];
            for (int i = 0; i <= M; i++) {
                for (int j = 0; j <= N; j++) {
                    nodes[2 * i][2 * j] = new Node(2 * M, 2 * N, 2 * i, 2 * j);
                    if (i < M && j < N) {
                        nodes[2 * i + 1][2 * j + 1] = new Node(2 * M, 2 * N, 2 * i + 1, 2*j + 1);
                    }
                }
            }
            for (int j = 0; j < N + 1; j++) {
                for (int i = 0; i < M; i++) {
                    int x1 = 2 * i;
                    int x2 = 2 * (i + 1);
                    int y = 2 * j;
                    Node n1 = nodes[x1][y];
                    Node n2 = nodes[x2][y];
                    int d = in.nextInt();
                    Edge e = new Edge(n1, n2, d);
                    n1.addEdge(e);
                    n2.addEdge(e);
                }
                in.nextLine();
            }
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < M + 1; i++) {
                    int x = 2 * i;
                    int y1 = 2 * j;
                    int y2 = 2 * (j + 1);
                    Node n1 = nodes[x][y1];
                    Node n2 = nodes[x][y2];
                    int d = in.nextInt();
                    Edge e = new Edge(n1, n2, d);
                    n1.addEdge(e);
                    n2.addEdge(e);
                }
                in.nextLine();
            }
            for (int j = 0; j < 2 * N; j++) {
                for (int i = 0; i < M; i++) {
                    int xa = 2 * i;
                    int xb = 2 * i + 1;
                    int xc = 2 * (i + 1);
                    // Si pair
                    int y = j;
                    int y_bis = j + 1;
                    if (j % 2 == 1) {
                        // Si y impair
                        y = j + 1;
                        y_bis = j;
                    }
                    Node a = nodes[xa][y];
                    Node b = nodes[xb][y_bis];
                    Node c = nodes[xc][y];
                    int d1 = in.nextInt();
                    int d2 = in.nextInt();
                    Edge e1 = new Edge(a, b, d1);
                    Edge e2 = new Edge(b, c, d2);
                    a.addEdge(e1);
                    c.addEdge(e2);
                    b.addEdge(e1);
                    b.addEdge(e2);
                }
                in.nextLine();
            }
            System.out.println(maxFlow(nodes[0][0], nodes[2 * M][2 * N]));
            in.nextLine();
            N = in.nextInt();
            M = in.nextInt();
            in.nextLine();
        }
        in.close();
    }

}
