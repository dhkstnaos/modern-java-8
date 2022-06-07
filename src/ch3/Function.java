package ch3;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
