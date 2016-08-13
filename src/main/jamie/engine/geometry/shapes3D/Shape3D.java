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

package jamie.engine.geometry.shapes3D;

import jamie.engine.geometry.basic.Point;
import java.util.List;

/**
 *
 * @author Roman Vais
 */
public interface Shape3D {

    public boolean contains(Point p);
    public boolean contains(Cube c);
    public boolean contains(Shape3D s);

    public boolean intersects(Cube c);
    public boolean intersects(Shape3D s);

    public Cube getBoundingCube();

    public List<Point> getVertices();

}
