package second;

public class Apple {
    int price;
    Color color;

    public Apple(Color color, int price) {
        this.price = price;
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public Color getColor() {
        return color;
    }
}
