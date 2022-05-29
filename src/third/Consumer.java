package third;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
