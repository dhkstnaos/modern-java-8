package ch2;

import static ch2.Color.GREEN;

public class AppleGreenPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getColor().equals(GREEN);
    }
}
