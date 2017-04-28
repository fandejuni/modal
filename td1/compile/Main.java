import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        in.useDelimiter("");
        while (in.hasNext()) {
            int x = 0;
            int y = 0;
            String m = "0";
            while (!m.equals("+") && !m.equals("*") && !m.equals("-")) {
                x = 10 * x + Integer.parseInt(m);
                m = in.next();
            }
            String ope = m;
            boolean b = true;
            while (b) {
                m = in.next();
                if (!m.equals("\n")) {
                    y = 10 * y + Integer.parseInt(m);
                }
                else {
                    b = false;
                }
            }
            switch (ope) {
                case "+": System.out.println(x + y);
                          break;
                case "-": System.out.println(x - y);
                          break;
                case "*": System.out.println(x * y);
                          break;
            }
        }
        in.close(); 
    }
}
