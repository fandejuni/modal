import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

class TestBuffer {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n, k;
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int c = 0;
        while (n-- > 0) {
           int x = Integer.parseInt(in.readLine());
           if (x % k == 0)
               c++;
        }
        System.out.println(c);
    }

}
