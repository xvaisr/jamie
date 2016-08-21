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
import java.util.List;

/**
 * Interface representing common 2D shape, in another words - polygon.
 * This interface is simplified but inspired by java.awt.Shape. Classes implementing
 * it are intended just for use in jamie only.
 * @author Roman Vais
 */
public interface Shape {

    /**
     * Returns whether or not is given point contained inside of this shape.
     * Method should be implemented so it does check only !!! "required" !!! conditions.
     * @param p - point which is supposed to be contained by this shape.
     * @return true if given point is contained by this shape, its borders or is one of it's vertices.
     */
    public boolean contains(Point p);

    /**
     * Returns whether or not is given rectangle contained inside of this shape.
     * Method should be implemented so it does check only !!! "required" !!! conditions.
     * @param r - rectangle which is supposed to be contained by this shape.
     * @return true if given rectangle is contained by this shape, false if it's outside or intersects
     */
    public boolean contains(Rectangle r);

    /**
     * Returns whether or not is given shape contained inside of this shape.
     * @param s - shape which is supposed to be contained by this shape.
     * @return true if given shape is contained by this shape, false if it's outside or intersects
     */
    public boolean contains(Shape s);

    /**
     * Returns whether or not is given rectangle intersecting this shape.
     * @param r - rectangle which is supposed to be intersecting this shape.
     * @return true if given rectangle is intersecting this shape or it is contained by it false otherwise
     */
    public boolean intersects(Rectangle r);

    /**
     * Returns whether or not is given shape intersecting this shape.
     * @param s - shape which is supposed to be intersecting this shape.
     * @return true if given shape is intersecting this shape or it is contained by it false otherwise
     */
    public boolean intersects(Shape s);

    /**
     * Returns minimal bounding box (rectangle) which can contain this shape.
     * @return minimal bounding box (rectangle) which can contain this shape.
     */
    public Rectangle getBoundingBox();

    /**
     * Returns unmodifiable list of all vertices defining this shape.
     * Because this shape is supposed to be polygon list of vertices must be sorted so the order
     * of individual points, if connected by the edge, would create exactly the same shape as original
     * polygon.
     * @return minimal bounding box (rectangle) which can contain this shape.
     */
    public List<Point> getVertices();

    /**
     * Returns unmodifiable list of all edges defining this shape.
     * Because this shape is supposed to be polygon edges in the list are sorted clockwise or
     * counterclockwise around the shape it self as if you draw this shape/polygon in one stroke.
     * @return list of all edges defining this shape.
     */
    public List<LineSegment> getEdges();

    /**
     * Returns graph defining this shape. (Not implemented yet !)
     * Because this shape is supposed to be polygon graph is enclosed.
     * @return graph defining this shape.
     */
    public Graph getGraph();
}
