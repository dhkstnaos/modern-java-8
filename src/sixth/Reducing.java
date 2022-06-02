package sixth;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static sixth.Dish.menu;

public class Reducing {
    public static void main(String[] args) {
        System.out.println(countingMenus());
        System.out.println(calculateTotalCalories());
        System.out.println(maxCaloriesDish().get());
        System.out.println(minCaloriesDish().get());
        System.out.println(averageCaloriesDishes());
        System.out.println(sumCaloriesDishes());
        System.out.println(summaryDishes().toString());
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(reducing(0, Dish::getCalories, (Integer a, Integer b) -> a + b));
    }

    private static long countingMenus() {
        return menu.stream().collect(Collectors.counting());
    }

    private static Optional<Dish> maxCaloriesDish() {
        return menu.stream().collect(maxBy(Comparator.comparingInt(Dish::getCalories)));
    }

    private static Optional<Dish> minCaloriesDish() {
        return menu.stream().collect(minBy(Comparator.comparingInt(Dish::getCalories)));
    }

    private static Double averageCaloriesDishes() {
        return menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
    }

    private static Double sumCaloriesDishes() {
        return menu.stream().collect(Collectors.summingDouble(Dish::getCalories));
    }

    private static DoubleSummaryStatistics summaryDishes() {
        return menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories));
    }
}
