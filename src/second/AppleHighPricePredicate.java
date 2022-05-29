package second;

public class AppleHighPricePredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getPrice() > 2000;
    }
}
