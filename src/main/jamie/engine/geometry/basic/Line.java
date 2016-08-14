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

package jamie.engine.geometry.basic;

import java.io.Serializable;

/**
 * Class represents infinite straight line defined by two points in two-dimensional or three-dimensional space.
 * If line is defined in two-dimensional space, instance can provide normal vector.
 * @author Roman Vais
 */
public class Line implements Serializable, Cloneable {
    // k, l are points making the line and u, no are vectors (why make duplicate class?)
    private final Point k, l, u, no;
    private final boolean twoDimensional;

    /**
     * Creates instance of line represented by two points or its directional vector.
     * Whether or not is line defined in two-dimensional or three-dimensional space depends
     * only on the two points defining the line. If defining points are both two-dimensional
     * (coordinate of z axis is equal to zero), than the line is two-dimensional as well.
     * otherwise the line is considered to be three dimensional.
     *
     * @param k - one of the two defining points
     * @param l - one of the two defining points
     */
    public Line(Point k, Point l) {
        this.k = k;
        this.l = l;
        // get directional vector
        this.u = new Point((l.x() - k.x()), (l.y() - k.y()), (l.z() - k.z()));
        // get normal vector (for 2D only)
        this.no = new Point(this.u.y(), -this.u.x());

        this.twoDimensional = k.getIs2D() && l.getIs2D();
    }

    /**
     * Returns one of the two points defining this line.
     * @return one of the two points defining this line
     */
    public Point getK() {
        return this.k;
    }

    /**
     * Returns one of the two points defining this line.
     * @return one of the two points defining this line
     */
    public Point getL() {
        return this.l;
    }

    /**
     * Returns directional vector defining this line.
     * @return point with coordinates corresponding to coordinates of directional
     * vector for this line.
     */
    public Point getVector() {
        return this.u;
    }

    /**
     * Returns two-dimensional normal vector defining this line.
     * If line is not two-dimensional, returns point with all coordinates equal
     * to zero.
     * @return point with coordinates corresponding to coordinates of normal
     * vector for this line or point P[0, 0, 0]
     */
    public Point getNormalVector() {
        return ((this.k.getIs2D() && this.l.getIs2D())? this.no : new Point());
    }

    /**
     * Returns whether or not is this line considered to be 2D line.
     * @return true if z coordinate of both defining points is equal to 0
     */
    public boolean getIs2D() {
        return this.twoDimensional;
    }

}
