/*0,
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
 * Class encapsulates width and height in single immutable object as integer values. Constructor
 * with width and height as parameters allows for negative values of width and height although
 * how other classes using dimension will behave as a result of negative values is undefined.
 * @author Roman Vais
 */
public class Dimension implements Serializable {

    private final int w;
    private final int h;

    /**
     * Creates immutable instance of Dimension representing width and height of an object.
     * Default constructor without parameters creates dimension with both attributes,
     * width and height, equal to zero.
     */
    public Dimension() {
        this(0, 0);
    }

    /**
     * Creates immutable instance of Dimension representing width and height of an object.
     * Constructor without parameters creates dimension with both attributes,
     * width and height, equal to its corresponding arguments.
     * @param widht - integer value of width
     * @param height - integer value of height
     */
    public Dimension(int widht, int height) {
        this.w = widht;
        this.h = height;
    }

    /**
     * Creates immutable instance of Dimension representing width and height of an object.
     * Constructor without parameters creates dimension with both attributes,
     * width and height, equal to distance on x and y axis between these points where
     * x axis distance represents width and y axis represents height.
     * @param p - point used for computing dimension
     * @param q - point used for computing dimension
     */
    public Dimension(Point p, Point q) {
        this.w = Math.abs(q.x() - p.x());
        this.h = Math.abs(q.y() - p.y());
    }

    /**
     * Returns width of this dimension.
     * @return width of this dimension
     */
    public int getWidth() {
        return this.w;
    }

    /**
     * Returns height of this dimension.
     * @return height of this dimension
     */
    public int getHeight() {
        return this.h;
    }

    @Override
    public String toString() {
        return this.w + "x" + this.h;
    }
}
