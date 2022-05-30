package fifth;

import fourth.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fourth.Dish.menu;

public class main {
    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");

    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        //1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하라.
        List<Transaction> first = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        // 질의 2: 거래자가 근무하는 모든 고유 도시는?
        List<String> second = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        // 질의 3: Cambridge의 모든 거래자를 찾아 이름으로 정렬
        List<Trader> traders = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        // 질의 4: 알파벳 순으로 정렬된 모든 거래자의 이름 문자열을 반환
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce("", (s1, s2) -> s1 + s2);
        // 질의 5: Milan에 거주하는 거래자가 있는가?
        if (transactions.stream().map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"))) {
            System.out.println("inMilan");
        }
        // 질의 6: Cambridge에 사는 거래자의 모든 거래내역 출력
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        // 질의 7: 모든 거래에서 최고값은 얼마인가?
        int max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println(max);
        // 가장 작은 값을 가진 거래 탐색
        Optional<Transaction> minTransaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        // 거래가 없을 때 기본 문자열을 사용할 수 있도록발견된 거래가 있으면 문자열로 바꾸는 꼼수를 사용함(예, the Stream is empty)
        minTransaction.map(String::valueOf).orElse("No Transactions");

        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        OptionalInt maxOptional = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        Stream<String> modern = Stream.of("Modern", "JAVA");
        Stream<String> empty = Stream.empty();

        String home = System.getProperty("home");
        Stream<String> stream= home == null ? Stream.empty() : Stream.of(home);
    }
}
