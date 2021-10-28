import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private class Node implements Comparable<Node> {
        private boolean vertical;
        private Point2D point;
        private Point2D left;
        private Point2D right;

        Node(Point2D point, boolean vertical) {
            this.point = point;
            this.vertical = vertical;
        }

        public double x() {
            return point.x();
        }

        public double y() {
            return point.y();
        }

        public boolean isVertical() {
            return vertical;
        }

        @Override
        public int compareTo(Node node) {
            if (node.isVertical()) {
                return Double.compare(this.y(), node.y());
            }
            else {
                return Double.compare(this.x(), node.x());
            }
        }
    }

    public KdTree() {

    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public void insert(Point2D p) {
    }

    public boolean contains(Point2D p) {
        return false;
    }

    public void draw() {
    }

    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    public static void main(String[] args) {
    }

    public KdTree nearest(Point2D query) {
        return null;
    }
}
