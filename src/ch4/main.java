package ch4;

import java.util.List;

import static ch4.Dish.menu;
import static java.util.stream.Collectors.toList;

public class main {
    public static void main(String[] args) {
        List<String> names = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(dish -> dish.getName())
                .limit(3)
                .collect(toList());
        System.out.println(names);
    }

}
