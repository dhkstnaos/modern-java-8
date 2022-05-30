package fifth;

import fourth.Dish;

import java.util.List;
import java.util.stream.Collectors;

import static fourth.Dish.menu;

public class filtering {
    public static void main(String[] args) {
        List<Dish> dishes = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
    }
}
