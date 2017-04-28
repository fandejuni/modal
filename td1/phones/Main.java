import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            in.nextLine();
            String[] numbers = new String[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = in.nextLine();
            }
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    valid = valid && !numbers[i].startsWith(numbers[j]) && !numbers[j].startsWith(numbers[i]);
                }
            }
            if (valid) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }

}
