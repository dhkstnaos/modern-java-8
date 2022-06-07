package ch5;

import ch4.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ch4.Dish.menu;

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

    public static void main(String[] args) throws IOException {
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

        Stream<String> stringStream = Stream.ofNullable(System.getProperty("home"));

        int[] numsArray={1,2,3,4,5};
        int sum1 = Arrays.stream(numsArray).sum();

        // Stream.iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // iterate를 이용한 피보나치
        Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] })
                .limit(10)
                .forEach(t -> System.out.printf("(%d, %d)", t[0], t[1]));

        Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] })
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

        // Stream.generate를 이용한 임의의 double 스트림
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        // Stream.generate을 이용한 요소 1을 갖는 스트림
        IntStream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::println);

        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        }).limit(5).forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {

            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int nextValue = previous + current;
                previous = current;
                current = nextValue;
                return previous;
            }

        };
        IntStream.generate(fib)
                .limit(10)
                .forEach(System.out::println);

        long uniqueWords = Files.lines(Paths.get("lambdasinaction/chap5/data.txt"), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();

        System.out.println("There are " + uniqueWords + " unique words in data.txt");
    }
}
