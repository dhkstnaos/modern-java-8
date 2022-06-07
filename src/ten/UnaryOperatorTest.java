package ten;

import java.util.function.UnaryOperator;

public class UnaryOperatorTest {
    public static void main(String[] args) {
        UnaryOperator<Integer> doubleOp = i -> i * 2;
        UnaryOperator<Integer> tripleOp = i -> i * 3;
        UnaryOperator<Integer> four = i -> i * 4;
        Integer apply = doubleOp.andThen(tripleOp).andThen(four).apply(10);
        System.out.println(apply);
    }
}
