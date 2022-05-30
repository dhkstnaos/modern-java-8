package third;

import second.Apple;
import second.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;
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

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T e : list) {
            result.add(f.apply(e));
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
        //Predicate 커스텀
        Predicate<String> nonEmptyStringPredicate = (String s) -> s.length() > 7;
        List<String> list = List.of("", "sdfdfsfset4", "5lkfb", "dfp;ovsdfsdfsf");
        List<String> nonEmpty = filter(list, nonEmptyStringPredicate);
        System.out.println(nonEmpty);

        //Consumer 커스텀
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));

        //Fuction 커스텀
        List<Integer> map = map(
                Arrays.asList("lambdas", "in", "action"), (String s) -> s.length()
        );
        System.out.println(map);

        //생성자 참조
        Supplier<Apple> c1 = Apple::new;
        Apple apple = c1.get();

        Function<Integer, Apple> c2 = Apple::new;
        Apple apple2 = c2.apply(200);

        BiFunction<Color, Integer, Apple> biFunction = Apple::new;
        Apple appleBiFunction = biFunction.apply(GREEN, 100);

        //익명 클래스
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });

        //람다 표현식
        inventory.sort((Apple o1, Apple o2) -> o1.getPrice() - o2.getPrice());

        //코드 간소화
        inventory.sort(comparing(a -> a.getPrice()));
    }
}
