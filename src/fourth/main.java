package fourth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {
        //자바 7버전
        List<Dish> lowCaloricDished = new ArrayList<>();
        List<Dish> menu = List.of(
                new Dish(150),
                new Dish(200),
                new Dish(100),
                new Dish(400)
        );
        for (Dish dish : menu) {
            if (dish.getCalorie() <= 150) {
                lowCaloricDished.add(dish);
            }
        }

        //자바 8버전
        List<Dish> collects = menu.stream()
                .filter(e -> e.getCalorie() <= 150)
                .collect(Collectors.toList());
    }
}
