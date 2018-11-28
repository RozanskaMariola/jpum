package jpum;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class IteratorsDemo {
    private static final List<Integer> values = Arrays.asList(1,2,3,4,5);

    private static void externalIteration() {
        System.out.println("--- External iteration... ");
 
        System.out.println("1) for (int i = 0; i < values.size(); ++i)");
        for (int i = 0; i < values.size(); ++i) {
            System.out.println(values.get(i));
        }
        System.out.println();

        System.out.println("2) for (int e : values)");
        for (int e : values) {
            System.out.println(e);
        }
        System.out.println();

        System.out.println("3) while (it1.hasNext())");
        Iterator<Integer> it1 = values.iterator();
        while (it1.hasNext()) {
            int e = it1.next();
            System.out.println(e);
        }
        System.out.println();

        System.out.println("4) for (Iterator<Integer> it2 = ... )");
        for (Iterator<Integer> it2 = values.iterator(); it2.hasNext(); ) {
            int e = it2.next();
            System.out.println(e);
        }
        System.out.println();
    }

    private static void internalIteration() {
        System.out.println("--- Internal iteration... ");

        System.out.println("1) values.forEach(new Consumer<Integer>()");
        values.forEach(new Consumer<Integer>() {
            public void accept(Integer e) {
                System.out.println(e);
            }
        });
        System.out.println();

        System.out.println("2) values.forEach((Integer e) -> ");
        values.forEach((Integer e) -> System.out.println(e));
        System.out.println();

        System.out.println("3) values.forEach((e) -> ...");
        values.forEach((e) -> System.out.println(e));
        System.out.println();

        System.out.println("4) values.forEach(e -> ...");
        values.forEach(e -> System.out.println(e));
        System.out.println();

        System.out.println("5) values.forEach(System.out::println)");
        values.forEach(System.out::println);
        System.out.println();

        System.out.println("6) values.forEach(e -> {...}...");
        values.forEach(e -> {
            int i = (e % 2 == 0) ? e * e : 10 * e;
            System.out.println(i);
        });
        System.out.println();
    }

    public static void main(String[] args) {
        externalIteration();
        internalIteration();
    }
}
