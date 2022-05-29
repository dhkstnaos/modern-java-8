package second;

import java.util.ArrayList;
import java.util.List;

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



    public static void main(String[] args) {
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

        List<Apple> apples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return RED.equals(apple.getColor());
            }
        });

    }
}