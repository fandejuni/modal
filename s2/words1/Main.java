import java.util.*;
import java.io.*;

class Main {

    public static char last(String s) {
        return s.charAt(s.length() - 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            in.nextLine();
            Map<Character, Set<Character>> m = new HashMap<Character, Set<Character>>();
            Map<Character, Integer> count = new HashMap<Character, Integer>();
            Set<Character> all = new HashSet<Character>();
            for (int i = 0; i < n; i++) {
                String s = in.nextLine();
                Character a = s.charAt(0);
                Character b = last(s);
                if (!m.containsKey(a))
                    m.put(a, new HashSet<Character>());
                if (!m.containsKey(b))
                    m.put(b, new HashSet<Character>());
                if (!count.containsKey(a))
                    count.put(a, 0);
                if (!count.containsKey(b))
                    count.put(b, 0);
                all.add(a);
                all.add(b);
                m.get(a).add(b);
                m.get(b).add(a);
                count.put(a, count.get(a) + 1);
                count.put(b, count.get(b) - 1);
            }


            // est connexe ?
            Stack<Character> to_see = new Stack<Character>();
            Set<Character> vus = new HashSet<Character>();
            to_see.add(all.iterator().next());
            while (!to_see.isEmpty()) {
                Character x = to_see.pop();
                for (Character y: m.get(x)) {
                    if (!vus.contains(y)) {
                        vus.add(y);
                        to_see.push(y);
                    }
                }
            }
            boolean b = true;
            boolean vu_1 = false;
            boolean vu_m1 = false;
            for (Character x: all) {
                if (count.get(x) == 1) {
                    if (!vu_1)
                        vu_1 = true;
                    else
                        b = false;
                }
                else if (count.get(x) == -1) {
                    if (!vu_m1)
                        vu_m1 = true;
                    else
                        b = false;
                }
                else
                    b = b && count.get(x) == 0;
            }
            b = b && (vus.size() == all.size()) && ((vu_1 && vu_m1) || (!vu_1 && !vu_m1));
            if (b)
                System.out.println("Ordering is possible.");
            else
                System.out.println("The door cannot be opened.");
        }

        in.close();
    }

}
