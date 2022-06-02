package sixth;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static sixth.Dish.menu;

public class Grouping {
    public static void main(String[] args) {
        System.out.println("dishesByType() = " + dishesByType());;
    }
    
    private static Map<Dish.Type, List<Dish>> dishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType));
    }
}
