import java.util.*;

class Couple {

    static int RIEN = -1;
    static int OPERATOR = 0;
    static int NOMBRE = 1;
    static int CELLULE = 2;
    static int PARENTHESE = 3;

    int type;
    String content;

    public Couple(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public String toString() {
        String ope = "RIEN";
        switch(type) {
            case 0:
                ope = "OPERATOR";
                break;
            case 1:
                ope = "NOMBRE";
                break;
            case 2:
                ope = "CELLULE";
                break;
            case 3:
                ope = "PARENTHESE";
                break;
        }
        return "C[" + ope + "," + content + "]";
    }
}

class Spreadsheet {

    static Set<String> deja_vu;
    static Map<String, ArrayList<Couple>> sheet;
    static Map<String, Long> values;
    static Map<String, Integer> tetes;
    static boolean circular;

    static long modulo = 1000000;

    public static long moduler(long x) {
        long y = x % modulo;
        if (y < 0) {
            y += modulo;
        }
        return y;
    }
    
    public static long B(ArrayList<Couple> l, String cellule) {
        Couple c = l.get(tetes.get(cellule));
        tetes.put(cellule, tetes.get(cellule) + 1);
        if (c.type == Couple.CELLULE) {
            return eval(c.content);
        }
        else if (c.type == Couple.NOMBRE) {
            return Long.parseLong(c.content);
        }
        else if (c.type == Couple.PARENTHESE) {
            long s = S(l, cellule);
            tetes.put(cellule, tetes.get(cellule) + 1);
            return s;
        }
        System.out.println("ERREUR B");
        return 0;
    }

    public static long P(ArrayList<Couple> l, String cellule) {
        long b = B(l, cellule);
        if (tetes.get(cellule) == l.size()) {
            return b;
        }
        Couple c = l.get(tetes.get(cellule));
        if (c.content.equals("*") || c.content.equals("/")) {
            tetes.put(cellule, tetes.get(cellule) + 1);
            long p = P(l, cellule);
            if (c.content.equals("*")) {
                return moduler(b * p); 
            }
            else if (c.content.equals("/")) {
                if (p == 0) {
                    return 0;
                }
                return moduler(b / p);
            }
        }
        return b;
    }

    public static long S(ArrayList<Couple> l, String cellule) {
        long p = P(l, cellule);
        if (tetes.get(cellule) == l.size()) {
            return p;
        }
        Couple c = l.get(tetes.get(cellule));
        if (c.content.equals("+") || c.content.equals("-")) {
            tetes.put(cellule, tetes.get(cellule) + 1);
            long s = S(l, cellule);
            if (c.content.equals("+")) {
                return moduler(p + s); 
            }
            else if (c.content.equals("-")) {
                return moduler(p - s);
            }
        }
        return p;
    }

    public static long eval(String s) {
        if (circular || deja_vu.contains(s)) {
            circular = true;
            return modulo;
        }
        if (values.containsKey(s)) {
            return values.get(s);
        }
        else {
            deja_vu.add(s);
            long r = 0;
            if (sheet.containsKey(s)) {
                r = S(sheet.get(s), s);
            }
            values.put(s, r);
            deja_vu.remove(s);
            return r;
        }
    }

    private static ArrayList<Couple> tokenize(String s) {
        ArrayList<Couple> l = new ArrayList<Couple>();
        StringBuilder current = new StringBuilder("");
        long current_type = Couple.RIEN;
        for (int i = 0; i < s.length(); i++) {
            int type = Couple.RIEN;
            char x = s.charAt(i);

            if (x == '*' || x == '+' || x == '-' || x == '/') {
                type = Couple.OPERATOR;
            }
            else if (Character.isDigit(x)) {
                type = Couple.NOMBRE;
            }
            else if (Character.isLetter(x)) {
                type = Couple.CELLULE;
            }
            else if (x == '(' || x == ')') {
                type = Couple.PARENTHESE;
            }
            else {
                System.out.println("ERROR: " + x);
            }

            if (current_type == Couple.NOMBRE) {
                if (type == Couple.NOMBRE) {
                    current.append(x);
                }
                else {
                    l.add(new Couple(Couple.NOMBRE, current.toString()));
                }
            }
            else if (current_type == Couple.CELLULE) {
                current.append(x);
                l.add(new Couple(Couple.CELLULE, current.toString()));
                type = Couple.RIEN;
            }
            else if (type == Couple.NOMBRE) {
                current = new StringBuilder(x + "");
            }
            if (type == Couple.CELLULE) {
                current = new StringBuilder(x + "");
            }

            if (type != Couple.NOMBRE && type != Couple.CELLULE && type != Couple.RIEN) {
                l.add(new Couple(type, x + ""));
            }


            current_type = type;

        }
        if (current_type == Couple.NOMBRE) {
            l.add(new Couple(Couple.NOMBRE, current.toString()));
        }
        return l;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {

            deja_vu = new HashSet<String>();
            sheet = new HashMap<String, ArrayList<Couple>>();
            values = new HashMap<String, Long>();
            circular = false;
            tetes = new HashMap<String, Integer>();

            long N = in.nextInt();
            in.nextLine();

            for (int i = 0; i < N; i++) {
                String s = in.nextLine().replaceAll(" ", "");
                String[] a = s.split("=");
                sheet.put(a[0], tokenize(a[1]));
                tetes.put(a[0], 0);
            }
            long r = moduler(eval("A1"));
            if (circular) {
                r = modulo;
            }
            System.out.println(r);
        }
        in.close();
    }

}
