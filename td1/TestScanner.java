import java.util.Scanner;

class TestScanner {

    public static void main(String[] args) {
        int n, k;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        in.nextLine();
        int c = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x % k == 0) {
                c++;
            }
            in.nextLine();
        }
        in.close();
        System.out.println(c);
    }

}
