package eight;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactory {
    public static void main(String[] args) {
        //List.of
        List<String> friends = List.of("dhkstn", "frank", "josh");
        System.out.println(friends);
        //friends.add("hi");
        Set<String> sets = Set.of("dhkstn", "frank", "josh");

        Map<String, Integer> maps = Map.of("dhkstn", 10, "frank", 20, "josh", 30);
    }
}
