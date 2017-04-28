class TestMyScanner {

    public static void main(String[] args) {
        int n, k;
        MyScanner in = new MyScanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        int c = 0;
        for (int i = 0; i < n; i++) {
            in.nextLine();
            int x = in.nextInt();
            if (x % k == 0) {
                c++;
            }
        }
        System.out.println(c);
    }

}
