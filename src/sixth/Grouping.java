package sixth;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import static sixth.Dish.menu;
import static sixth.Grouping.CaloricLevel.chooseCaloricLevel;

public class Grouping {
    public static void main(String[] args) {
        System.out.println("dishesByType() = " + dishesByType());
        System.out.println("dishesByCaloricLevel() = " + dishesByCaloricLevel());
        System.out.println("caloriesToNameByType() = " + caloriesToNameByType());
        System.out.println("groupDishNamesByType() = " + groupDishNamesByType());
        System.out.println("mostCaloricDishesByType() = " + mostCaloricDishesByType());
        System.out.println("sumCaloriesByType() = " + sumCaloriesByType());
    }

    private static Map<Dish.Type, List<Dish>> dishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<CaloricLevel, List<Dish>> dishesByCaloricLevel() {
//        return menu.stream().collect(groupingBy(dish -> {
//            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//            else return CaloricLevel.FAT;
//        }));
        return menu.stream().collect(groupingBy(dish -> chooseCaloricLevel(dish.getCalories())));
    }

    //이렇게 되면 FISH 타입은 생기지 않는다. 우리는 이걸 의도한게 아니다.
    private static Map<Dish.Type, List<Dish>> caloriesByTypeWorst() {
        return menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, List<Dish>> caloriesByTypeBest() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500, toList())));
    }

    private static Map<Dish.Type, List<String>> caloriesToNameByType() {
        return menu.stream()
                .collect(
                        groupingBy(
                                Dish::getType, mapping(Dish::getName, toList())));
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }


    public enum CaloricLevel {
        DIET, NORMAL, FAT;

        public static CaloricLevel chooseCaloricLevel(Integer calories) {
            if (calories <= 400) return CaloricLevel.DIET;
            else if (calories <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }
    }
}
