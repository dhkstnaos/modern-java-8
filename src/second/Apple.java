package second;

public class Apple {
    int price;
    Color color;

    public Apple(Color color, int price) {
        this.price = price;
        this.color = color;
    }

    public Apple() {
        this.price = 1000;
        this.color = Color.RED;
    }

    public Apple(Integer price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Color getColor() {
        return color;
    }
}
