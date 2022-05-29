package second;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

import static second.Color.GREEN;
import static second.Color.RED;

public class version {
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        List<Apple> inventory = List.of(new Apple(GREEN, 1500), new Apple(RED, 2000), new Apple(GREEN, 4800), new Apple(RED, 7700), new Apple(RED, 850));
        List<Apple> greenApples = filterGreenApples(inventory);
        List<Apple> redApples = filterApplesByColor(inventory, RED);
        greenApples.stream().map(Apple::getColor).forEach(System.out::println);
        redApples.stream().map(Apple::getColor).forEach(System.out::println);
        int sum1 = redApples.stream().mapToInt(Apple::getPrice).sum();
        System.out.println("sum1 = " + sum1);

        List<Apple> highPriceApples = filterApples(inventory, new AppleHighPricePredicate());
        int sum = highPriceApples.stream().mapToInt(Apple::getPrice).sum();
        System.out.println(sum);

        //익명클래스로 필터링
        List<Apple> apples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return RED.equals(apple.getColor());
            }
        });

        //람다로 필터링
        List<Apple> lambdaApples = filterApples(inventory, (Apple apple) -> RED.equals(apple.getColor()));

        //리스트 형식으로 추상화
        List<Apple> filter = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        List<Integer> numbers = List.of(2, 6, 41, 5);
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
        System.out.println(evenNumbers);

        //compator로 정렬
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });
        //람다를 활용
        inventory.sort(
                (Apple a1, Apple a2) -> a1.getPrice() - a2.getPrice()
        );

        //Runnable
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        });

        Thread t2 = new Thread(() -> System.out.println("Hello World"));

        //Callable로 return 값 반환하기
        MyCallable callable = new MyCallable();
        String call = callable.call();
        System.out.println(call);
    }

    public static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "callable";
        }
    }
}