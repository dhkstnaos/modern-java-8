package eight;

import fifth.Trader;

import java.util.ArrayList;
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

        //setting
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new Trader("do", "00213"), 2022, 3000));
        transactions.add(new Transaction(new Trader("do", "dsf"), 2012, 2000));
        transactions.add(new Transaction(new Trader("vv", "453"), 2002, 5000));
        transactions.add(new Transaction(new Trader("vv", "df"), 2012, 8000));
        transactions.add(new Transaction(new Trader("do", "yongin"), 2032, 2000));
        transactions.add(new Transaction(new Trader("vv", "yongin"), 2002, 1000));

        System.out.println(transactions);
        transactions.removeIf(transaction -> Character.isDigit(transaction.getTrader().getCity().charAt(0)));
        System.out.println(transactions);
    }
}
