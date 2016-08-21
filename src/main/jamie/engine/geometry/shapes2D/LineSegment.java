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
import java.io.Serializable;

/**
 * Class represents line segment defined by two points in two-dimensional space.
 * Implementation is closely similar to Line class implementation except Line can
 * be both, two-dimensional and three-dimensional. If defining points are not
 * two-dimensional, the z axis coordinates are ignored. Because class is purely
 * two-dimensional offers some useful methods that cannot work in 3D space.
 *
 * @author Roman Vais
 */
public class LineSegment
        implements Serializable, Cloneable {

    private final Point k, l, u, no;
    public double lenght;

    /**
     * Creates instance of line segment represented by two points or its vectors.
     * Whether or not is line defined in two-dimensional or three-dimensional space depends
     * only on the two points defining the line. If defining points are not both two-dimensional
     * (coordinate of z axis is equal to zero), than new points are created to define this abscissa
     * by setting their z axis to zero.
     *
     * @param k - one of the two defining points
     * @param l - one of the two defining points
     */
    public LineSegment(Point k, Point l) {
        this.k = ((k.getIs2D()) ? k : new Point(k.x(), k.y()));
        this.l = ((l.getIs2D()) ? l : new Point(l.x(), l.y()));
        // get vector
        this.u = new Point((l.x() - k.x()), (l.y() - k.y()));
        // get normal vector
        this.no = new Point(u.y(), -u.x());

        // get lenght of segmet (equals lenght of vector)
        this.lenght = Point.getRealDistance(k, l);
    }

    /**
     * Returns precise value of distance between two defining points
     *
     * @return distance between two defining points as double value
     */
    public double getPreciseLenght() {
        return this.lenght;
    }

    /**
     * Returns value of distance between two defining points
     *
     * @return distance between two defining points as integer value
     */
    public int getLenght() {
        return new Long(Math.round(this.lenght)).intValue();
    }

    /**
     * Returns one of the two points defining this line segment.
     *
     * @return one of the two points defining this line segment
     */
    public Point getK() {
        return new Point(k);
    }

    /**
     * Returns one of the two points defining this line segment.
     *
     * @return one of the two points defining this line segment
     */
    public Point getL() {
        return new Point(l);
    }

    /**
     * Returns directional vector defining this line segment.
     *
     * @return point with coordinates corresponding to coordinates of directional
     * vector for this line.
     */
    public Point getVector() {
        return this.u;
    }

    /**
     * Returns normal vector defining this line segment.
     *
     * @return point with coordinates corresponding to coordinates of normal
     * vector for this line segment
     */
    public Point getNormalVector() {
        return this.no;
    }

    /**
     * Computes whether or not is given line segment perpendicular to this one.
     *
     * @param seg examined line segment
     * @return true if given line segment is perpendicular to this one, false otherwise
     */
    public boolean getPerpendicular(LineSegment seg) {
        Point v = seg.getVector();
        int result;

        // scalar multiplication of two vectors;
        result = (this.u.x() * v.x()) + (this.u.y() * v.y());
        // if u.v == 0  vector are perpendicular
        return result == 0;
    }

    /**
     * Computes whether or not is given line segment parallel to this one.
     * Method internally uses getPerpendicular and getLineIntersection, because
     * if two lines do not intersect then they can be only parallel to each other;
     *
     * @param seg examined line segment
     * @return true if given line segment is parallel to this one, false otherwise
     */
    public boolean getParallel(LineSegment seg) {
        return (this.getPerpendicular(seg) && this.getLineIntersection(seg) == null);
    }

    /**
     * Computes whether or not is given line segment intersecting with this one.
     *
     * @param seg examined line segment
     * @return true if given line segment intersects with this one, false otherwise
     */
    public boolean getIntersects(LineSegment seg) {
        Point intersector = this.getLineIntersection(seg);
        if (intersector == null) {
            return false;
        }

        return this.insideSegment(intersector) && seg.insideSegment(intersector);
    }

    /**
     * Computes intersection point of extended lines made by this and given line segment.
     *
     * @param seg examined line segment
     * @return intersection point of extended lines made by line segments or null if
     * extended lines do not intersect
     */
    public Point getLineIntersection(LineSegment seg) {
        Point n, m;     // normal vectors;
        n = this.getNormalVector();
        m = seg.getNormalVector();

        double denominator = n.x() * m.y() - n.y() * m.x();

        if (denominator == 0) {
            return null;
        }

        // derived from implicit equations of line
        int CA = -n.x() * this.k.x() - n.y() * this.k.y();
        int CB = -m.x() * seg.k.x() - m.y() * seg.k.y();

        // intersection point
        double xp, yp;
        xp = (n.y() * CB - m.y() * CA) / denominator;
        yp = (m.x() * CA - n.x() * CB) / denominator;

        // rounded coordinations
        Long xd, yd;
        xd = Math.round(xp);
        yd = Math.round(yp);

        Point intersector = new Point(xd.intValue(), yd.intValue());
        return intersector;

    }

    /**
     * Returns whether or not can be found on a segment. Method does not guarantee
     * that point actually is found on this line segment due to use integer coordinates
     * and due to the fact that it only assumes that it does. Method guarantees to be correct
     * only if given point has been found as a point in which is line where this segment supposed
     * to be is intersecting other line or segment. DO NOT MODIFY OR OVERRIDE!
     *
     * @param intersector - point of intersection
     */
    private boolean insideSegment(Point intersector) {
        boolean xAxis, yAxis;
        int xa, xb, ya, yb;
        xa = Math.min(this.k.x(), this.l.x());
        xb = Math.max(this.k.x(), this.l.x());
        ya = Math.min(this.k.y(), this.l.y());
        yb = Math.max(this.k.y(), this.l.y());

        xAxis = xa <= intersector.x() && intersector.x() <= xb;
        yAxis = ya <= intersector.y() && intersector.y() <= yb;

        return xAxis && yAxis;
    }

    @Override
    public LineSegment clone() throws CloneNotSupportedException {
        LineSegment ls = (LineSegment) super.clone();
        return ls;
    }
}
