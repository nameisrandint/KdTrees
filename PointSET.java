import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {

    private final TreeSet<Point2D> set;

    public PointSET() {
        set = new TreeSet<>();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        else {
            set.add(p);
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        else {
            return set.contains(p);
        }
    }

    public void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> list = new LinkedList<>();
        for (Point2D point : set) {
            if (rect.contains(point)) {
                list.add(point);
            }
        }
        return list;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            return null;
        }

        double min = Double.POSITIVE_INFINITY;
        Point2D nearest = p;

        for (Point2D otherPoint : set) {
            if (p == otherPoint) {
                continue;
            }

            double distance = p.distanceSquaredTo(otherPoint);

            if (distance < min) {
                min = distance;
                nearest = otherPoint;
            }
        }

        return nearest;
    }

    public static void main(String[] args) {
        /* empty */
    }
}

