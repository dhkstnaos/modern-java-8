package ch4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class sevenAndEight {
    public static void main(String[] args) {
        //자바 7버전
        List<Dish> lowCaloricDished = new ArrayList<>();
        List<Dish> menu = List.of(
                new Dish(150, "fish"),
                new Dish(200, "meat"),
                new Dish(100, "rice"),
                new Dish(400, "seaweed")
        );
        for (Dish dish : menu) {
            if (dish.getCalories() <= 150) {
                lowCaloricDished.add(dish);
            }
        }

        Collections.sort(lowCaloricDished, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getCalories() - o2.getCalories();
            }
        });

        List<String> dishName = new ArrayList<>();
        for (Dish dish : lowCaloricDished) {
            dishName.add(dish.getName());
        }

        //자바 8버전
        List<String> collects = menu.stream()
                .filter(e -> e.getCalories() <= 150)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
