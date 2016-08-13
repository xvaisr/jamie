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

/**
 *
 * @author Roman Vais
 */
public class Abscissa2D {
    private final Point k, l, u, no;
    public double size;

    public Abscissa2D(Point k, Point l) {
        this.k = k;
        this.l = l;
        // get vector
        this.u = new Point((l.x() - k.x()), (l.y() - k.y()));
        // get normal vector
        this.no = new Point(u.y(), -u.x());

        // get size of segmet (equals size of vector)
        this.size = Point.getRealDistance(k, l);
    }

    public double getSize() {
        return this.size;
    }

    public Point getK() {
        return new Point(k);
    }

    public Point getL() {
        return new Point(l);
    }

    public Point getVector() {
        return this.u;
    }

    public Point getNormalVector() {
        return this.no;
    }

    public boolean getPerpendicular(Abscissa2D seg) {
        Point v = seg.getVector();
        int result;

        // scalar multiplication of two vectors;
        result = (this.u.x() * v.x()) + (this.u.y() * v.y());
        // if u.v == 0  vector are perpendicular
        return result == 0;
    }

    public boolean getParalel(Abscissa2D seg) {
        Point n = seg.getNormalVector();
        int result;
        // if u is perpendicular to the normal vector of other segment
        // segmets are paralel

        // scalar multiplication of two vectors;
        result = (this.u.x() * n.x()) + (this.u.y() * n.y());
        // if u.v == 0  vector are perpendicular
        return result == 0;
    }

    public Point getIntersection(Abscissa2D seg) {
        Point n, m;     // normal vectors;
        n = this.getNormalVector();
        m = seg.getNormalVector();

        double denominator = n.x()*m.y() - n.y()*m.x();

        if (denominator == 0) {
            return null;
        }

        // derived from implicit equations of line
        int CA = -n.x() * this.k.x() - n.y() * this.k.y();
        int CB = -m.x() * seg.k.x() - m.y() * seg.k.y();

        // intersection point
        double xp, yp;
        xp = (n.y()*CB - m.y()*CA) / denominator;
        yp = (m.x()*CA - n.x()*CB) / denominator;

        // rounded coordinations
        Long xd, yd;
        xd = Math.round(xp);
        yd = Math.round(yp);

        Point intersector = new Point(xd.intValue(), yd.intValue());
        return intersector;
    }

    public boolean insideSegment(Point intersector) {
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

    public boolean getIntersect(Abscissa2D seg) {
        Point intersector = this.getIntersection(seg);
        if (intersector == null) {
            return false;
        }
        return (this.insideSegment(intersector) && seg.insideSegment(intersector));
    }
}
