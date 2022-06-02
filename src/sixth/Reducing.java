package sixth;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static sixth.Dish.menu;

public class Reducing {
    public static void main(String[] args) {
        System.out.println("count:" + countingMenus());
        System.out.println("calculateTotalCalories:" + calculateTotalCalories());
        System.out.println("maxCaloriesDish:" + maxCaloriesDish().get());
        System.out.println("minCaloriesDish:" + minCaloriesDish().get());
        System.out.println("averageCaloriesDishes:" + averageCaloriesDishes());
        System.out.println("sumCaloriesDishes:" + sumCaloriesDishes() + ", " + bestSumCaloriesDishes());
        System.out.println("summaryDishes:" + summaryDishes().toString());
        System.out.println("summaryMenuNames:" + summaryMenuNames());
        System.out.println("summaryCategories:" + summaryCategories());
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

    private static Double sumCaloriesDishesMapToDouble() {
        return menu.stream().mapToDouble(Dish::getCalories).sum();
    }

    private static Double sumCaloriesDishes() {
        return menu.stream().collect(Collectors.summingDouble(Dish::getCalories));
    }

    private static int bestSumCaloriesDishes() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }

    private static DoubleSummaryStatistics summaryDishes() {
        return menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories));
    }

    private static String summaryMenuNames() {
        return menu.stream().map(Dish::getName).collect(joining(", "));
    }

    private static String summaryCategories() {
        return Arrays.stream(Dish.Type.values()).map(Dish.Type::name).collect(joining(", "));
    }

}
