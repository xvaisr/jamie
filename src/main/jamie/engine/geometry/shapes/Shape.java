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

package jamie.engine.geometry.shapes;

import jamie.engine.geometry.basic.Point;
import jamie.engine.geometry.shapes.Cube;
import jamie.engine.geometry.shapes.Rectangle;
import java.util.List;

/**
 *
 * @author Roman Vais
 */
public interface Shape {

    public boolean contains(Point p);
    public boolean contains(Rectangle r);
    public boolean contains(Shape s);

    public boolean intersects(Rectangle r);
    public boolean intersects(Shape s);

    public Rectangle getBoundingBox();
    public Cube getBoundingCube();

    public List<Point> getVertices();
}
