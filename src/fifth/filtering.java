package fifth;

import fourth.Dish;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static fourth.Dish.menu;

public class filtering {
    public static void main(String[] args) {
        List<Dish> menus = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 400, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        //predicate로 필터링
        List<Dish> dishes = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        //고유 요소로 필터링
        List<Integer> numbers = List.of(1, 2, 1, 3, 3, 2, 4);
        List<Integer> numberList = List.of(1, 2, 1, 3, 3, 2, 4).stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
        Collections.sort(menus, (o1, o2) -> o1.getCalories() - o2.getCalories());
        List<Dish> collect = menus.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

        for (Dish dish : collect) {
            System.out.println(dish.getCalories());
        }

        List<Dish> collect1 = menus.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

        for (Dish dish : collect1) {
            System.out.println(dish.getCalories());
        }

        List<Dish> filters = menus.stream()
                .filter(dish -> dish.getCalories() < 320)
                .limit(3)
                .collect(Collectors.toList());

        List<String> strings = menus.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());


    }

}
