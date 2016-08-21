/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jamie.engine.geometry.shapes2D;

import jamie.engine.geometry.basic.Point;
import jamie.tools.algorithms.graph.Graph;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Class representing common convex polygon in 2D space. Polygon can contain 3D points but in that case
 * depth coordinates (z axis) will be ignored by most of the methods. Depending on implementation, polygon
 * may be flattened. Polygon requires midpoint for sorting it's vertices clockwise or counterclockwise around
 * it in order to guarantee its convexity. Class has method for verification whether or not is its instance really
 * convex. If it is not some of the methods (depending on implementation) implementing Shape interface are not
 * guaranteed to work properly.
 *
 * @author Roman Vais
 */
public class Polygon
        implements Serializable, Shape, Cloneable{

    private Point midpoint;
    private ArrayList<Point> verticies;
    private ArrayList<LineSegment> edges;
    private boolean verticesChanged;

    /**
     * Creates empty instance of a polygon with default midpoint in centre of coordinate system.
     * Default midpoint has coordinates point(x, y, z) = (0, 0, 0). Instance is empty and ready
     * for addition of defining points.
     */
    public Polygon() {
        this(new Point());
    }

    /**
     * Creates empty instance of a polygon with given midpoint.
     * Instance is empty and ready for addition of defining points.
     * @param midpoint - point that is supposed to be centre of a polygon
     */
    public Polygon(Point midpoint) {
        this(midpoint, Collections.emptyList());
    }

    /**
     * Creates empty instance of a polygon with given midpoint and list of vertices.
     * Polygon is created by sorting vertices around its midpoint.
     * @param midpoint - point that is supposed to be centre of a polygon
     * @param verticies - list of vertices that should define this convex polygon
     */
    public Polygon(Point midpoint, List<Point> verticies) {
        this.midpoint = midpoint;
        this.verticies = new ArrayList<>();
        this.verticies.addAll(verticies);
        this.edges = null;
        this.verticesChanged = false;
    }

    /**
     * Returns middle point of this polygon.
     * @return midpoint
     */
    public Point getMidpoint() {
        return this.midpoint;
    }

    /**
     * Adds given vertex to the geometry of this polygon.
     * Vertex is added to the end of list without sorting. This method should be used
     * only if correct representation polygon is created by connecting vertices by edges
     * in given order. Resulting edges should not cross each other.
     * @param v - vertex / point that should be added to polygon
     */
    public void addVertex(Point v) {
        this.verticies.add(v);
        this.verticesChanged = true;
    }

/*
    / **
     * Adds given vertex to the geometry of this polygon.
     * Method has additional parameter that decides whether given point will be
     * added to the end of list of vertices and sorted clockwise or to the beginning
     * and sorted counterclockwise. It is recommended to use only one or the other option
     * so list is not
     * @param v - vertex / point that should be added to polygon
     * @param cw - if true, points in list will be sorted clockwise
     * /
    public void addVertex(Point v, boolean cw) {
    }
 */


    /**
     * Verifies whether or not is this polygon really convex.
     * Method may return false even if polygon is convex till it is actually implemented.
     * @return true if this polygon is really convex
     */
    public boolean getIsConvex() {
        return false; // TODO: fill method with actual implementation
    }

    @Override
    public boolean contains(Point p) {
        Rectangle r = this.getBoundingBox();
        if (!r.contains(p)) {
            return false;
        }

        boolean in = false;
        Iterator<Point> itp = this.getVertices().iterator();

        while(in == false && itp.hasNext()) {
            Point q = itp.next();
            in = q.equals(p);
        }

        if (in) {
            return in;
        }

        LineSegment ls = new LineSegment(p, this.midpoint);
        Iterator<LineSegment> ite = this.getEdges().iterator();

        while(in == false && ite.hasNext()) {
            LineSegment edge = ite.next();
            in = ls.getIntersects(edge);
        }

        return !in;
    }

    @Override
    public boolean contains(Rectangle r) {
        return this.contains((Shape) r);

    }

    @Override
    public boolean contains(Shape s) {
        if (!this.getBoundingBox().contains(s.getBoundingBox())) {
            return false;
        }

        // if bounding box contains the shape but shapes do not intersect, than other shape is contained by this one
        return !this.intersects(s);
    }

    @Override
    public boolean intersects(Rectangle r) {
        return this.intersects((Shape) r);
    }

    @Override
    public boolean intersects(Shape s) {
        boolean in = false;

        LineSegment mineEdge, itsEdge;
        Iterator<LineSegment> mine, its;
        mine = this.getEdges().iterator();
        its = s.getEdges().iterator();

        while(in == false && mine.hasNext()) {
            mineEdge = mine.next();
            while (in == false && its.hasNext()) {
                itsEdge = its.next();

                in = mineEdge.getIntersects(itsEdge);
            }
        }

        return in;
    }

    @Override
    public Rectangle getBoundingBox() {
        ArrayList<Point> set;
        int x, y, w, h;

        set = (ArrayList<Point>) this.verticies.clone();
        Collections.sort(set);
        x = set.get(0).x();
        w = set.get(set.size() - 1).x() - set.get(0).x();

        Point.setSortKey(Point.SortBy.y);
        Collections.sort(set);
        y = set.get(0).y();
        h = set.get(set.size() - 1).y() - set.get(0).y();

        Point.setSortKey(Point.SortBy.y);
        return new Rectangle(new Point(x, y), w, h);
    }

    @Override
    public List<Point> getVertices() {
        return Collections.unmodifiableList(this.verticies);
    }

    @Override
    public List<LineSegment> getEdges() {
        if (this.edges == null) { // serves for lazy evaluation
            this.edges = new ArrayList();
            this.verticesChanged = true;
        }

        if (this.verticesChanged) {
            if (!this.edges.isEmpty()) {
                this.edges.clear();
            }

            Point v1, v2, st;
            Iterator<Point> it;

            v2 = null;
            st = null;

            it = this.verticies.iterator();
            if (it.hasNext()) {
                v2 = it.next();
                st = v2;
            }

            while (it.hasNext()) {
                v1 = v2;
                v2 = it.next();

                this.edges.add(new LineSegment(v1, v2));
            }

            if (v2 != st && st != null) {
                this.edges.add(new LineSegment(v2, st));
            }

            this.verticesChanged = false;

        }

        return Collections.unmodifiableList(this.edges);
    }

    @Override
    public Graph getGraph() {
        return null;
    }
}
