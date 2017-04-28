import java.util.*;
import java.io.*;

class Main {

    public static char next_letter(String s) {
        //System.out.println("Reçu :" + s);
        StringBuilder all = new StringBuilder("ABCD");
        for (int i = 0; i < s.length(); i++) {
            int j = all.indexOf(Character.toString(s.charAt(i)));
            if (j != -1) {
                all.deleteCharAt(j);
            }
        }
        //System.out.println("Donc : " + all.toString());
        return all.charAt(0);
    }

    public static boolean diff(char a, char b, char c) {
        //System.out.println("DIFF : " + a + b + c);
        return (a != b) && (a != c) && (b != c);
    }

    public static String completer(String s) {
        char a = s.charAt(0);
        char b = s.charAt(1);
        char c = next_letter("" + a + b);
        char d = next_letter("" + a + b + c);
        StringBuilder r = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == a) {
                r.append(c);
            }
            else {
                r.append(d);
            }
        }
        return r.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            in.nextLine();
            String s = in.nextLine();

            // On cherche si il existe 3 lettres différentes à la suite
            if (s.length() >= 3) {
                char a = s.charAt(0);
                char b = s.charAt(1);
                char c = s.charAt(2);
                int i = 1;
                while (!diff(a, b, c) && i + 2 < s.length()) {
                    i++;
                    a = s.charAt(i - 1);
                    b = s.charAt(i);
                    c = s.charAt(i + 1);
                }
                if (i + 2 == s.length() && !diff(a, b, c)) {
                    System.out.println(completer(s));
                }
                else {
                    //System.out.println(i);
                    // On complète vers la droite
                    StringBuilder r = new StringBuilder(s);
                    r.setCharAt(0, next_letter("" + s.charAt(0) + s.charAt(1)));
                    for (int j = 1; j < s.length(); j++) {
                        String letters = "" + s.charAt(j) + r.charAt(j - 1);
                        if (j + 1 < s.length()) letters += s.charAt(j + 1);
                        System.out.println(letters);
                        r.setCharAt(j, next_letter(letters));
                    }
                    System.out.println(r.toString());
               }
            }
            else {
                System.out.println(completer(s));
            }
        }
        in.close();
    }

}
