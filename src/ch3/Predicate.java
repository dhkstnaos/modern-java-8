package ch3;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
