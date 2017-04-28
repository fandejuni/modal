import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while (!s.equals(".")) {
            String[] a = s.split(" ");
            String word = a[0];
            int l = word.length();
            int n = Integer.parseInt(a[1]);
            for (int i = 0; i < l; i++) {
                String dis = "";
                for (int j = 0; j < l * n; j++) {
                    dis += word.charAt((j + i) % l);
                }
                System.out.println(dis);
            }
            s = in.nextLine();
        }
        in.close();
    }

}
