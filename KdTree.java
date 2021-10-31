import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;

// TODO: implement toString(), match insert with BST code

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
        public int compareTo(Node other) {
            return  isVertical()
                    ? Double.compare(this.x(), other.x())
                    : Double.compare(this.y(), other.y());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;

            if (obj == null) return false;

            if (obj.getClass() == this.getClass())
                return ((Node) obj).point.equals(this.point);

            return false;
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

        public String toString() {
            return point.toString();
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
            ++numberOfNodes;
            return node;
        }

        int compare = node.compareTo(newNode);
        if (compare >= 0) {
            node.setLeft(insert(node.getLeft(), newNode));
        }
        else {
            node.setRight(insert(node.getRight(), newNode));
        }

        if (node.getLeft() != null)
            node.getLeft().setVertical(!node.isVertical());

        if (node.getRight() != null)
            node.getRight().setVertical(!node.isVertical());

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

    private String toString(Node node) {
        java.util.Queue<Node> queue = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        queue.add(node);
        Node rightNode = node;

        while (!queue.isEmpty()) {
            Node pop = queue.poll();

            if (pop.equals(rightNode)) {
                stringBuilder.append(pop.toString() + "\n");
                rightNode = rightNode.getRight();
            }
            else {
                stringBuilder.append(pop.toString());
            }

            if (pop.getLeft() != null)
                queue.add(pop.getLeft());

            if (pop.getRight() != null)
                queue.add(pop.getRight());
        }

        return stringBuilder.toString();
    }

    public String toString() {
        return  toString(root);
    }

    public static void main(String[] args) {
        KdTree kd = new KdTree();
        kd.insert(new Point2D(1, 1)); //в
        kd.insert(new Point2D(5, 12));
        kd.insert(new Point2D(-3, 8));
        kd.insert(new Point2D(6, 13));
        kd.insert(new Point2D(8, 15));
        kd.insert(new Point2D(22, -4));
        kd.insert(new Point2D(37, 1));
        kd.insert(new Point2D(0, 0));
        kd.insert(new Point2D(2, -1));
        kd.insert(new Point2D(-9, -9));


        System.out.println(kd);
    }
}
