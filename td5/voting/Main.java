import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            int V = in.nextInt();
            in.nextLine();
            String[] candidates = new String[N];
            for (int i = 0; i < N; i++) {
                candidates[i] = in.nextLine();
            }
            List<Queue<Integer>> l = new LinkedList<Queue<Integer>>();
            for (int i = 0; i < V; i++) {
                Queue<Integer> q = new LinkedList<Integer>();
                for (int j = 0; j < N; j++) {
                    q.add(in.nextInt() - 1);
                }
                l.add(q);
                in.nextLine();
            }
            boolean continuer = true;
            List<String> winners = new LinkedList<String>();
            int[] scores = new int[N];
            Set<Integer> eliminated = new HashSet<Integer>();
            //System.out.println(l);
            while(continuer) {
                // Mise à zéro
                for (int i = 0; i < N; i++)
                    scores[i] = 0;
                // On compte les votes
                //System.out.println("ELI " + eliminated);
                for (Queue<Integer> q: l)
                    scores[q.peek()]++;
                //System.out.println(Arrays.toString(scores));
                // On checke si winner > 50%
                // Et les pires
                int lowest_score = V + 1;
                List<Integer> lowest = new LinkedList<Integer>();
                int current_winner = -1;
                int score_max = -1;
                for (int i = 0; i < N; i++) {
                    if (!eliminated.contains(i)) {
                        int score = scores[i];
                        if (score > score_max) {
                            score_max = score;
                            current_winner = i;
                        }
                        if (score < lowest_score) {
                            lowest_score = score;
                            lowest.clear();
                            lowest.add(i);
                        }
                        else if (score == lowest_score) {
                            lowest.add(i);
                        }
                    }
                }
                //System.out.println(V + ", " + eliminated.size() + ", " + lowest.size());

                //1er cas : winner
                if (score_max > V / 2) {
                    continuer = false;
                    winners.add(candidates[current_winner]);
                }
                else if (N - eliminated.size() == lowest.size()) {
                    continuer = false;
                    for (int x: lowest)
                        winners.add(candidates[x]);
                }
                else {
                    for (int x: lowest) {
                        /*
                        //System.out.println(x);
                        for (Queue<Integer> q: l) {
                            q.remove(x);
                        }
                        */
                        eliminated.add(x);
                    }
                    // On dégage les derniers
                    for (Queue<Integer> q: l) {
                        while (eliminated.contains(q.peek()))
                            q.remove();
                    }
                }
            }
            for (String s: winners)
                System.out.println(s);
        }
        in.close();
    }

}
