import java.util.*;
import java.io.*;
import java.text.*;

class TriCount {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double epsilon = 0.000001;
        int n = in.nextInt();
        while (n != 0) {
            List<Double> l = new LinkedList<Double>();
            double moyenne = 0.0;
            double coeff = 1. / (float) n;
            for (int i = 0; i < n; i++) {
                double x = in.nextDouble();
                l.add(x);
                moyenne += coeff * x;
            }
            moyenne = Math.floor(moyenne * 100) / 100.0;
            double sum_sup = 0.0;
            int n_sup = 0;
            double sum_inf = 0.0;
            int n_inf = 0;
            for (double x: l) {
                if (x < moyenne) {
                    sum_inf += moyenne - x;
                    n_inf++;
                }
                else if (x > moyenne) {
                    sum_sup += x - moyenne;
                    n_sup++;
                }
            }
            //System.out.println(n_sup + ", " + sum_sup + "; " + n_inf + ", " + sum_inf);
            if (sum_inf > sum_sup) {
                int temp_n = n_inf;
                n_inf = n_sup;
                n_sup = temp_n;

                double temp_sum = sum_inf;
                sum_inf = sum_sup;
                sum_sup = temp_sum;
            }
            double ecart = sum_sup - sum_inf;
            ecart = Math.min(ecart, n_sup * 0.01);
            double r = sum_sup - ecart;
            NumberFormat formatter = new DecimalFormat("#0.00"); 
            System.out.println("$" + formatter.format(r));
            n = in.nextInt();
        }
        in.close();
    }

}
