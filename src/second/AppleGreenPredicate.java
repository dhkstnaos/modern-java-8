package second;

import static second.Color.GREEN;

public class AppleGreenPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getColor().equals(GREEN);
    }
}
