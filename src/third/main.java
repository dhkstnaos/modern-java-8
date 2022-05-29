package third;

import second.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static second.Color.GREEN;
import static second.Color.RED;

public class main {
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = List.of(
                new Apple(GREEN, 1500),
                new Apple(RED, 2000),
                new Apple(GREEN, 4800),
                new Apple(RED, 7700),
                new Apple(RED, 850)
        );
        Predicate<String> nonEmptyStringPredicate = (String s) -> s.length() > 7;
        List<String> list = List.of("", "sdfdfsfset4", "5lkfb", "dfp;ovsdfsdfsf");
        List<String> nonEmpty = filter(list, nonEmptyStringPredicate);
        System.out.println(nonEmpty);

    }
}
