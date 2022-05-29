package second;

import java.util.ArrayList;
import java.util.List;

import static second.Color.GREEN;

public class firstVersion {
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
}

class Apple {
    Color color;

    public Apple(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
