package eight;

import fifth.Trader;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Map.entry;

public class CollectionFactory {
    public static void main(String[] args) {
        //List.of
        List<String> friends = List.of("dhkstn", "frank", "josh");
        Set<String> sets = Set.of("dhkstn", "frank", "josh");
        Map<String, Integer> maps = Map.of("dhkstn", 10, "frank", 20, "josh", 30);

        //setting
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new Trader("do", "00213"), 2022, 3000));
        transactions.add(new Transaction(new Trader("do", "dsf"), 2012, 2000));
        transactions.add(new Transaction(new Trader("vv", "453"), 2002, 5000));
        transactions.add(new Transaction(new Trader("vv", "df"), 2012, 8000));
        transactions.add(new Transaction(new Trader("do", "yongin"), 2032, 2000));
        transactions.add(new Transaction(new Trader("vv", "yongin"), 2002, 1000));

        //removeIf
        System.out.println(transactions);
        //transactions.removeIf(transaction -> Character.isDigit(transaction.getTrader().getCity().charAt(0)));
        //System.out.println(transactions);

        //removeAll
        List<String> referenceCodes = Arrays.asList("a12", "C14", "b13");
        System.out.println("Back to the original: " + referenceCodes);
        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
        System.out.println("referenceCodes = " + referenceCodes);

        //forEach
        referenceCodes.stream().forEach(System.out::println);


        //
        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        ageOfFriends.forEach((key, value) -> System.out.println(key + ":" + value));

        ageOfFriends.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

        ageOfFriends.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(System.out::println);

        //getOrDefault
        System.out.println(ageOfFriends.getOrDefault("Olivia", 0));
        System.out.println(ageOfFriends.getOrDefault("rily", 0));

        ageOfFriends.put("Hello",ageOfFriends.getOrDefault("Hello",0)+1);
        //computeIfAbsent 제공된 키에 해당 값이 없으면 새 값을 계산하고 맵에 추가
        Map<String, List<String>> friendsToMap = new HashMap<>();
        friendsToMap.computeIfAbsent("null", name -> new ArrayList<>())
                .add("plus movie");
        System.out.println(friendsToMap);
        //compute 제공된 키로 새 값을 계산하고 맵에 저장한다.

        //remove
        friendsToMap.remove("Olivia");

        Map<String, String> favouriteMovies = new HashMap<>();
        favouriteMovies.put("Raphael", "Star Wars");
        favouriteMovies.put("Olivia", "james bond");
        //replaceAll
        favouriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println(favouriteMovies);

        //merge
        Map<String, Integer> map = new HashMap<>();
        map.put("john", 20);
        map.put("paul", 30);
        map.put("peter", 40);

        //key값이 존재를 하면, 해당 key의 값을 remapping 함수의 결과 값으로 바꾼다
        map.merge("peter", 50, (k, v) -> map.get("john") + 10);

        //key가 존재하고 remapping 함수의 결과가 null이면 map에서 해당 key를 삭제한다
        map.merge("peter", 30, (k, v) -> map.get("nancy"));

        //key가 존재하지 않으면 key, value값을 추가함
        map.merge("kelly", 50, (k, v) -> map.get("john") + 10);

        mergingMaps();

        //conCurrentHashMap
        conCurrentHashMap();
    }

    private static void mergingMaps() {
        Map<String, String> family = Map.ofEntries(
                entry("Teo", "Star Wars"),
                entry("Cristina", "James Bond")
        );
        Map<String, String> friends = Map.ofEntries(
                entry("Raphael", "Star Wars"),
                entry("Cristina", "Son")
        );

        Map<String, String> everyone = new HashMap<>(family);
        //덮어버린다.
        everyone.putAll(friends);
        System.out.println(everyone);

        Map<String, String> friends2 = Map.ofEntries(
                entry("Raphael", "Star Wars"),
                entry("Cristina", "Matrix")
        );

        Map<String, String> everyone2 = new HashMap<>(family);

        //중복 밸류를 로직을 통해 처리할 수 있다.
        friends2.forEach((k, v) -> everyone2.merge(k, v, (m1, m2) -> m1 + "&" + m2));
        System.out.println(everyone2);
    }

    private static void conCurrentHashMap() {
        //동시성 친화적이고, 동기화된 HashTable보다 읽기,쓰기 연산 기능이 월등이 높다.
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.put("1", 1L);
        map.put("#$", 3L);
        long parallelism = 1;
        Optional<Long> maxValue = Optional.ofNullable(map.reduceValues(parallelism, Long::max));
        System.out.println(maxValue.get());
    }
}
