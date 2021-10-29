import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private Node root;
    private int numberOfNodes;

    private class Node implements Comparable<Node> {

        private final Point2D point;
        private boolean vertical;
        private Node left;
        private Node right;


        Node(Point2D point) {
            this.point = point;
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
            if (isVertical()) {
                return Double.compare(this.x(), node.x());
            }
            else {
                return Double.compare(this.y(), node.y());
            }
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getLeft() {
            return left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getRight() {
            return right;
        }

        public void setVertical(boolean vertical) {
            this.vertical = vertical;
        }
    }

    public KdTree() {
        /* empty */
    }

    public boolean isEmpty() {
        return numberOfNodes == 0;
    }

    public int size() {
        return numberOfNodes;
    }

    public void insert(Point2D p) {
        root = insert(root, new Node(p));
    }

    private Node insert(Node node, Node newNode) {
        if (node == null) {
            node = newNode;
            newNode.setVertical(!node.isVertical());
            ++numberOfNodes;
            return newNode;
        }

        int compare = node.compareTo(newNode);
        if (compare >= 0) {
            node.setLeft(insert(node.getLeft(), newNode));
        }
        else {
            node.setRight(insert(node.getRight(), newNode));
        }

       return node;
    }

    public boolean contains(Point2D p) {
        return false;
    }

    public void draw() {
    }

    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    public KdTree nearest(Point2D query) {
        return null;
    }

    public static void main(String[] args) {

    }
}
