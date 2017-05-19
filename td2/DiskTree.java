import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

class Folder {

    Hashtable<String, Folder> ls;
    int p;

    public Folder(int p) {
        ls = new Hashtable<String, Folder>();
        this.p = p;
    }

    public void browse(List<String> l) {
        if (l.size() > 0) {
            String x = l.remove(0);
            if (!ls.contains(x)) {
                ls.put(x, new Folder(p + 1));
            }
            ls.get(x).browse(l);
        }
    }

    public void print() {
        String s = "";
        for (int i = 0; i < p; i++) {
            s += " ";
        }
        List<String> l = new LinkedList<String>();
        Enumeration e = ls.keys();
        while (e.hasMoreElements()) {
            l.add(e.nextElement().toString());
        }
        Collections.sort(l);
        for (String x: l) {
            System.out.println(s + x);
            ls.get(x).print();
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
            Folder root = new Folder(0);
            for (int i = 0; i < n; i++) {
                String s = in.nextLine();
                String[] ta = s.split(Pattern.quote("\\"));
                List<String> l = new LinkedList<String>();
                for (int j = 0; j < ta.length; j++) {
                    l.add(ta[j]);
                }
                root.browse(l);
            }
            root.print();
        }
        in.close();
    }

}
