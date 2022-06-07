package nine;


public class Debugging {

    public static void main(String[] args) {
        testMove();
    }

    public static void testMove() {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveMoveRightBy(5);
        System.out.println(p2.x);
    }

    private static class Point {

        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public Point moveMoveRightBy(int x) {
            return new Point(this.x + x, this.y);
        }

    }

}