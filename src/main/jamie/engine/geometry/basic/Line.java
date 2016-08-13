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

/**
 *
 * @author Roman Vais
 */
public class Line {
    // k, l are points making the line and u, no are vectors (why make duplicate class?)
    private final Point k, l, u, no;
    public double size;

    public Line(Point k, Point l) {
        this.k = k;
        this.l = l;
        // get directional vector
        this.u = new Point((l.x() - k.x()), (l.y() - k.y()), (l.z() - k.z()));
        // get normal vector (for 2D only)
        this.no = new Point(this.u.y(), -this.u.x());
    }

    public double getSize() {
        return this.size;
    }

    public Point getK() {
        try {
            return this.k.clone();
        }
        catch (CloneNotSupportedException ex) {
            return new Point(this.k.x(), this.k.y(), this.k.z());
        }
    }

    public Point getL() {
        try {
            return this.l.clone();
        }
        catch (CloneNotSupportedException ex) {
            return new Point(this.l.x(), this.l.y(), this.l.z());
        }
    }

    public Point getVector() {
        try {
            return this.u.clone();
        }
        catch (CloneNotSupportedException ex) {
            return new Point(this.u.x(), this.u.y());
        }
    }

    public Point getNormalVector() {
        return ((this.k.getIs2D() && this.l.getIs2D())? this.no : new Point());
    }
}
