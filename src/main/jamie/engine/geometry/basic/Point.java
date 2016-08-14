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
 * Class represents point in a three-dimensional space of Cartesian coordinate system.
 * Class has methods that allow for using it's instances as two-dimensional or
 * three-dimensional points. By extending this class, you can use protected final methods
 * to change inner fields and make instances effectively mutable.
 * @author Roman Vais
 */
public class Point implements Cloneable, Serializable, Comparable<Point> {

    /**
     * Enumeration of values which can be used as key for comparison/sort process of Points
     */
    public static enum SortBy {x,y,z}
    private static SortBy key = SortBy.x;

    private int x;
    private int y;
    private int z;

    /**
     * Creates immutable instance of Point suitable for a three-dimensional space
     * represented by Cartesian coordinate system.
     * Default constructor without parameters creates point with coordinates equal
     * to Point(X, Y, Z) = [0, 0, 0];
     */
    public Point() {
        this(0, 0, 0);
    }

    /**
     * Creates immutable instance of Point suitable for a three-dimensional space
     * represented by Cartesian coordinate system.
     * Constructor with X and Y parameters creates point with coordinates equal
     * to Point(X, Y, Z) = [x, y, 0]; allowing it to effectively act
     * as in two-dimensional space.
     *
     * @param x - value of coordinates on X axis
     * @param y - value of coordinates on Y axis
     */
    public Point(int x, int y) {
        this(x, y, 0);
    }

    /**
     * Creates immutable instance of Point suitable for a three-dimensional space
     * represented by Cartesian coordinate system.
     * Constructor with X, Y and Z parameters creates point with coordinates equal
     * to Point(X, Y, Z) = [x, y, z];
     *
     * @param x - value of coordinates on X axis
     * @param y - value of coordinates on Y axis
     * @param z - value of coordinates on Z axis
     */
    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates immutable instance of Point suitable for a three-dimensional space
     * represented by Cartesian coordinate system.
     * Constructor will copy coordinates from given point.
     *
     * @param p - point to copy coordinates from
     */
    public Point(Point p) {
            this.x = p.x();
            this.y = p.y();
            this.z = p.z();
    }

    /**
     * Computes distance of two distant points.
     * Uses Pythagorean theorem to compute distance between two distant points.
     * This method ignores third coordinate (z axis) as if points are purely
     * two-dimensional.
     * @param a - distant point
     * @param b - distant point
     * @return value of distance
     */
    public static int getDistance(Point a, Point b) {
        // Pythagorean Theorem
        double delta;
        Long distance;

        delta = Point.getRealDistance(a, b);
        distance = Math.round(delta);

        return distance.intValue();
    }

    /**
     * Computes distance of two distant points.
     * Uses Pythagorean theorem to compute distance between two distant points.
     * This method ignores third coordinate (z axis) as if points are purely
     * two-dimensional.
     * @param a - distant point
     * @param b - distant point
     * @return value of distance as double precision number
     */
    public static double getRealDistance(Point a, Point b) {
        // Pythagorean Theorem
        int x, y;
        x = a.x() - b.x();
        y = a.y() - b.y();

        return Math.sqrt( ((double) (x*x + y*y) ));
    }

    /**
     * Computes distance of two distant points.
     * Uses Pythagorean theorem to compute distance between two distant points.
     * @param a - distant point
     * @param b - distant point
     * @return value of distance
     */
    public static int getDistance3D(Point a, Point b) {
        // Pythagorean Theorem
        double delta;
        Long distance;

        delta = Point.getRealDistance(a, b);
        distance = Math.round(delta);

        return distance.intValue();
    }

    /**
     * Computes distance of two distant points.
     * Uses Pythagorean theorem to compute distance between two distant points.
     * @param a - distant point
     * @param b - distant point
     * @return value of distance as double precision number
     */
    public static double getRealDistance3D(Point a, Point b) {
        // Pythagorean Theorem
        int x, y, z;
        x = a.x() - b.x();
        y = a.y() - b.y();
        z = a.z() - b.z();

        return Math.sqrt( (double) (x*x + y*y + z*z) );
    }

    /**
     * Sets key for comparison of two Point instances. The value determines by
     * which axis values should be instances compered.
     * @param key - one of Point.SortBy.x Point.SortBy.y Point.SortBy.z values
     * @see jamie.engine.geometry.basic.Point.SortBy
     */
    public static void setSortKey(SortBy key) {
        if (key == null) throw new NullPointerException();
        Point.key = key;
    }

    /**
     * Returns X coordinate of this point.
     * @return X coordinate
     */
    public int x() {
        return this.x;
    }

    /**
     * Returns Y coordinate of this point.
     * @return Y coordinate
     */
    public int y() {
        return this.y;
    }

    /**
     * Returns Z coordinate of this point.
     * @return Z coordinate
     */
    public int z() {
        return this.z;
    }

    /**
     * Changes the coordinates of this point by shifting it along the axis by
     * given distance.
     * @param dx - distance on X axis to move this point
     * @param dy - distance on Y axis to move this point
     */
    protected final void _move (int dx, int dy) {
        this._move(dx, dy, 0);
    }

    /**
     * Changes the coordinates of this point by shifting it along the axis by
     * given distance.
     * @param dx - distance on X axis to move this point
     * @param dy - distance on Y axis to move this point
     * @param dz - distance on Z axis to move this point
     */
    protected final void _move (int dx, int dy, int dz) {
        this.x+= dx;
        this.y+= dy;
        this.z+= dz;
    }

    /**
     * Changes the coordinates of this point by replacing them with new values.
     * @param x - new coordinate value on X axis
     * @param y - new coordinate value on Y axis
     */
    protected final void _setLocation (int x, int y) {
        this._setLocation(x, y, this.z);
    }

    /**
     * Changes the coordinates of this point by replacing them with new values.
     * @param x - new coordinate value on X axis
     * @param y - new coordinate value on Y axis
     * @param z - new coordinate value on Z axis
     */
    protected final void _setLocation (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates new point as a copy of this one and changes it's coordinates
     * by shifting it along the axis by given distance.
     * @param dx - distance on X axis to move new point
     * @param dy - distance on Y axis to move new point
     * @return copy of this point shifted by given distances
     */
    public Point translate (int dx, int dy) {
        return translate(dx, dy, 0);
    }

    /**
     * Creates new point as a copy of this one and changes it's coordinates
     * by shifting it along the axis by given distance.
     * @param dx - distance on X axis to move new point
     * @param dy - distance on Y axis to move new point
     * @param dz - distance on Z axis to move new point
     * @return copy of this point shifted by given distances
     */
    public Point translate (int dx, int dy, int dz) {
        Point p;
        try {
            p = this.clone();
            p._move(dx, dy, dz);

        }
        catch (CloneNotSupportedException ex) {
            p = new Point(this.x, this.y, this.z);
            p._move(dx, dy, dz);
        }
        return p;
    }

    /**
     * Returns whether or not is this point behaving as 2D point.
     * @return true if z coordinate is equal to 0
     */
    public boolean getIs2D() {
        return this.z == 0;
    }

    /**
     * Computes distance of this point to given one.
     * @param p - distant point
     * @return value of distance
     */
    public int getDistanceTo(Point p) {
        return Point.getDistance3D(this, p);
    }

    /**
     * Computes distance of this point to given one.
     * @param p - distant point
     * @return value of distance as double precision number
     */
    public double getRealDistanceTo(Point p) {
        return Point.getRealDistance3D(this, p);
    }

    @Override
    public String toString() {
        return "Point(x,y,z) = " + x +", " + y +", "  + z +";" ;
    }

    @Override
    public Point clone() throws CloneNotSupportedException {
        return (Point) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) return false;
        Point p = Point.class.cast(o);
        return this.x == p.x() && this.y == p.y() && this.z == p.z();
    }

    @Override
    public int hashCode() {
        int hash = 1 + super.hashCode();
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        hash = 67 * hash + this.z;
        return hash;
    }


    /**
     * Compares coordinates of this point to another one.
     * This method compares coordinates on one of the three axes
     * by subtracting them. Coordinates of which axes are being compared
     * can be set by {@code Point.setSortKey()}. Default is x axis.
     * @param p - point for comparison
     * @return subtraction of coordinates
     * @see jamie.engine.geometry.basic.Point.setSortKey() <br/>
     *      Comparable java interface
     */
    @Override
    public int compareTo(Point p) {
        int result;
        switch (Point.key) {
            case x:
                result = this.x - p.x();
            break;
            case y:
                result = this.y - p.y();
            break;
            case z:
                result = this.z - p.z();
            break;
            default:
                result = 0;
            break;
        }
        return result;
    }

}
