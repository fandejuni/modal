import java.util.*;
import java.io.*;

class Courses {

    int N, P;

    List<Integer>[] students, courses;
    int[] couplage_students, couplage_courses;
    boolean[] course_visited;

    static final int LIBRE = -1;

    public Courses(List<Integer>[] students, List<Integer>[] courses) {
        N = students.length;
        P = courses.length;
        this.students = students;
        this.courses = courses;
        couplage_students = new int[N];
        couplage_courses = new int[P];
        Arrays.fill(couplage_students, LIBRE);
        Arrays.fill(couplage_courses, LIBRE);
        course_visited = new boolean[P];
    }

    // Part des courses
    public boolean dfs(int i) {
        course_visited[i] = true;
        for (int j: courses[i]) {
            int course = couplage_students[j];
            if ((course == LIBRE) || (!course_visited[course] && dfs(course))) {
                // On couple
                couplage_courses[i] = j;
                couplage_students[j] = i;
                return true;
            }
        }
        return false;
    }

    public boolean existeCheminAugmentant() {
        Arrays.fill(course_visited, false);
        for (int i = 0; i < P; i++) {
            if (couplage_courses[i] == LIBRE) {
                if (dfs(i))
                    return true;
            }
        }
        return false;
    }

    public boolean solve() {
        int n = 0;
        while (existeCheminAugmentant())
            n++;
        return n == P;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int t = 0; t < T; t++) {
            int P = in.nextInt();
            int N = in.nextInt();
            in.nextLine();
            List<Integer>[] students = new List[N];
            List<Integer>[] courses = new List[P];
            for (int i = 0; i < N; i++) {
                students[i] = new LinkedList<Integer>();
            }
            for (int i = 0; i < P; i++) {
                int c = in.nextInt();
                List<Integer> l = new LinkedList<Integer>();
                for (int j = 0; j < c; j++) {
                    int x = in.nextInt() - 1;
                    l.add(x);
                    students[x].add(i);
                }
                courses[i] = l;
                in.nextLine();
            }
            Courses instance = new Courses(students, courses);
            if (instance.solve())
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        in.close();
    }

}
