package sixth;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static sixth.Dish.dishTags;
import static sixth.Dish.menu;
import static sixth.Grouping.CaloricLevel.classifyingCaloricLevel;

public class Grouping {
    public static void main(String[] args) {
        System.out.println("dishesByType() = " + dishesByType());
        System.out.println("dishesByCaloricLevel() = " + dishesByCaloricLevel());
        System.out.println("caloriesToNameByType() = " + caloriesToNameByType());
        System.out.println("groupDishNamesByType() = " + groupDishNamesByType());
        System.out.println("mostCaloricDishesByType() = " + mostCaloricDishesByType());
        System.out.println("sumCaloriesByType() = " + sumCaloriesByType());
        System.out.println("groupDishTagsByType() = " + groupDishTagsByType());
        System.out.println("groupDishedByTypeAndCaloricLevel() = " + groupDishedByTypeAndCaloricLevel());
        System.out.println("subGroupCountingByType() = " + subGroupCountingByType());
        System.out.println("mostCaloricDishesByTypeWithoutOptional() = " + mostCaloricDishesByTypeWithoutOptional());
        System.out.println("caloricLevelsByType() = " + caloricLevelsByType());
        System.out.println("partitionedMenu() = " + partitionedMenu());
        System.out.println("partitionedMenuByType() = " + partitionedMenuByType());
        System.out.println("mostCaloricPartitionedByVegetarian() = " + mostCaloricPartitionedByVegetarian());
        System.out.println("caloriesByTypeBest() = " + caloriesByTypeBest());
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
        return menu.stream().collect(groupingBy(dish -> classifyingCaloricLevel(dish.getCalories())));
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

    private static Map<Dish.Type, Set<String>> groupDishTagsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> classifyingCaloricLevel(dish.getCalories()))
                )
        );
    }

    private static Map<Dish.Type, Long> subGroupCountingByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        Collectors.counting()
                )
        );
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptional() {
//        return menu.stream().collect(
//                groupingBy(Dish::getType,
//                        collectingAndThen(
//                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
//                                Optional::get)));
        //reducing보다는 Collectors의 메서드를 활용하자
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                Collectors.maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)
                )
        );
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping(
                                dish -> classifyingCaloricLevel(dish.getCalories()), toCollection(HashSet::new)
                        ))
        );
    }

    private static Map<Boolean, List<Dish>> partitionedMenu() {
        return menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> partitionedMenuByType() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType)));
    }

    private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                Optional::get))
        );
    }

    public enum CaloricLevel {
        DIET, NORMAL, FAT;

        public static CaloricLevel classifyingCaloricLevel(Integer calories) {
            if (calories <= 400) return CaloricLevel.DIET;
            else if (calories <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }
    }
}
