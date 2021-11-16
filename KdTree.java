import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class  KdTree {

    private Node root;
    private int numberOfNodes;
    private final boolean VERTICAL = true;

    public KdTree() { /* empty */ }

    private class Node {
        Node left;
        Node right;
        Point2D point;
        boolean type;
        RectHV rect;

        Node(Point2D point) {
            this.point = point;
            rect = new RectHV(0, 0, 1.0, 1.0);
        }
    }

    public void insert(Point2D point) { 
        if (point == null) throw new IllegalArgumentException();

        if (contains(point)) return;

		if (root == null) {
            root = new Node(point);
            root.type = VERTICAL;
        }
        else {
            double[] bounds = new double[4]; // Bounds for xmin, ymin, xmax, ymax
            bounds[0] = bounds[1] = 0.0;     //         in Node.rect
            bounds[2] = bounds[3] = 1.0;
            root = insertNode(new Node(point), root, bounds);
        }
    }

        
    private Node insertNode(Node node, Node current, double[] bounds) {
        if (current == null) {
            ++numberOfNodes;
            node.rect = new RectHV(bounds[0], bounds[1], bounds[2], bounds[3]);
            return node;
        }
        
        if (current.type == VERTICAL) {
            if (node.point.x() <= current.point.x()) {
                bounds[2] = current.point.x();
                current.left = insertNode(node, current.left, bounds);
            }
            else {
                bounds[0] = current.point.x();
                current.right = insertNode(node, current.right, bounds);
            }
        }
        else {
            if (node.point.y() <= current.point.y()) {
                bounds[3] = current.point.y();
                current.left = insertNode(node, current.left, bounds);
            }
            else {
                bounds[1] = current.point.y();
                current.right = insertNode(node, current.right, bounds);
            }
        }

		if (current.left != null) current.left.type = !current.type;
		if (current.right != null) current.right.type = !current.type;

        return current;
    }

    public boolean isEmpty() { return size() == 0; }

    public int size() { return numberOfNodes; }

    public boolean contains(Point2D point) { 
        if (point == null) throw new IllegalArgumentException();
        Node current = root;
        while (current != null) {
            if (point.equals(current.point)) return true;

            if (current.type == VERTICAL) {
                if (point.x() <= current.point.x()) {
                    current = current.left;
                }
                else {
                    current = current.right;
                }
            }
            else {
                if (point.y() <= current.point.y()) {
                    current = current.left;
                }
                else {
                    current = current.right;
                }
            }
        }

        return false;
    }

    public void draw() { 
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) return;
        if (node.type == VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), node.rect.ymax(), node.point.x(), node.rect.ymin());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmax(), node.point.y(), node.rect.xmin(), node.point.y());
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.015);
        node.point.draw();
        StdDraw.setPenRadius(0.005);
        draw(node.left);
        draw(node.right);
    }

    public Iterable<Point2D> range(RectHV rect) { 
        if (rect == null) throw new IllegalArgumentException();
        ArrayList<Point2D> list = new ArrayList<>();
        rangeRecursion(root, list, rect);
        return list; 
    }

    private void rangeRecursion(Node current, ArrayList<Point2D> list, RectHV query) {
        if (current == null) return;

        if (!current.rect.intersects(query)) return;

        if (query.contains(current.point)) list.add(current.point);

        rangeRecursion(current.left, list, query);
        rangeRecursion(current.right, list, query);
    }

    public Point2D nearest(Point2D point) { 
        if (point == null) throw new IllegalArgumentException();
        Point2D nearestPoint = new Point2D(10.0, 10.0);
        Node nearest = new Node(nearestPoint);
        nearestPoint(root, nearest, point);
        return nearest.point.equals(new Point2D(10.0, 10.0))
                ? null
                : nearest.point;
    }

    private void nearestPoint(Node current, Node nearest, Point2D search) {
        if (current == null || current.point.equals(search)) return;

        if (current.rect.distanceSquaredTo(search) > nearest.point.distanceSquaredTo(search)) return;

        if (current.point.distanceSquaredTo(search) < nearest.point.distanceSquaredTo(search)) {
            nearest.point = current.point;
        }

        if (current.type == VERTICAL) {
            if (search.x() <= current.point.x()) {
                nearestPoint(current.left, nearest, search);
                nearestPoint(current.right, nearest, search);
            }
            else {
                nearestPoint(current.right, nearest, search);
                nearestPoint(current.left, nearest, search);
            }
        }
        else {
            if (search.y() <= current.point.y()) {
                nearestPoint(current.left, nearest, search);
                nearestPoint(current.right, nearest, search);
            }
            else {
                nearestPoint(current.right, nearest, search);
                nearestPoint(current.left, nearest, search);
            }
        }
    }

    public static void main(String... args) { /* empty */ }
}
