package eight;

import fifth.Trader;

import java.util.*;

public class CollectionFactory {
    public static void main(String[] args) {
        //List.of
        List<String> friends = List.of("dhkstn", "frank", "josh");
        //friends.add("hi");
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
        ageOfFriends.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

        ageOfFriends.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(System.out::println);

        //getOrDefault
        System.out.println(ageOfFriends.getOrDefault("Olivia", 0));
        System.out.println(ageOfFriends.getOrDefault("rily", 0));

        //computeIfAbsent 제공된 키에 해당 값이 없으면 새 값을 계산하고 맵에 추가
        Map<String, List<String>> friendsToMap = new HashMap<>();
        friendsToMap.computeIfAbsent("null",name-> new ArrayList<>())
                .add("plus movie");
        System.out.println(friendsToMap);
        //compute 제공된 키로 새 값을 계산하고 맵에 저장한다.



    }
}
