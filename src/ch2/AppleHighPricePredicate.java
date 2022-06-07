package ch2;

public class AppleHighPricePredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getPrice() > 2000;
    }
}
