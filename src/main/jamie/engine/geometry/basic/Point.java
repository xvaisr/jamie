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
 *
 * @author Roman Vais
 */
public class Point implements Cloneable, Serializable {

    private int x;
    private int y;
    private int z;

    public Point() {
        this(0, 0, 0);
    }

    public Point(int x, int y) {
        this(x, y, 0);
    }

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
    public void move (int dx, int dy) {
        this.move(dx, dy, 0);
    }

    /**
     * Changes the coordinates of this point by shifting it along the axis by
     * given distance.
     * @param dx - distance on X axis to move this point
     * @param dy - distance on Y axis to move this point
     * @param dz - distance on Z axis to move this point
     */
    public void move (int dx, int dy, int dz) {
        this.x+= dx;
        this.y+= dy;
        this.z+= dz;
    }

    /**
     * Changes the coordinates of this point by replacing them with new values.
     * @param x - new coordinate value on X axis
     * @param y - new coordinate value on Y axis
     */
    public void setLocation (int x, int y) {
        this.setLocation(x, y, this.z);
    }

    /**
     * Changes the coordinates of this point by replacing them with new values.
     * @param x - new coordinate value on X axis
     * @param y - new coordinate value on Y axis
     * @param z - new coordinate value on Z axis
     */
    public void setLocation (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates new point as a copy of this one and changes it's coordinates
     * by shifting it along the axis by given distance.
     * @param dx - distance on X axis to move new point
     * @param dy - distance on Y axis to move new point
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
     */
    public Point translate (int dx, int dy, int dz) {
        Point p;
        try {
            p = this.clone();
            p.move(dx, dy, dz);

        }
        catch (CloneNotSupportedException ex) {
            p = new Point(this.x, this.y, this.z);
            p.move(dx, dy, dz);
        }
        return p;
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

}
