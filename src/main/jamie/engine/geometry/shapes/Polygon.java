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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roman Vais
 */
public class Polygon
        implements Serializable, Shape {

    private Point midpoint;
    private ArrayList<Point> verticies;

    public Polygon() {
        this(new Point());
    }

    public Polygon(Point midpoint) {
        this(midpoint, Collections.emptyList());
    }

    public Polygon(Point midpoint, List<Point> verticies) {
        this.midpoint = midpoint;
        this.verticies = new ArrayList<>();
        this.verticies.addAll(verticies);
    }

    public Point getMidpoint() {
        Point p;
        try {
            p = this.midpoint.clone();
        }
        catch (CloneNotSupportedException ex) {
            p = new Point(this.midpoint.x(), this.midpoint.y());
        }
        return p;
    }

    public boolean getIsConvex() {
        return false; // TODO: fill method with actual implementation
    }

    @Override
    public boolean contains(Point p) {
        if (!this.getBoundingBox().contains(p)) {
            return false;
        }

        return true; // TODO: finish the implementation of contain method
    }

    @Override
    public boolean contains(Rectangle r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Shape s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean intersects(Rectangle r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean intersects(Shape s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getBoundingBox() {
        ArrayList<Point> set;
        int x, y, w, h;

        set = (ArrayList<Point>) this.verticies.clone();
        Collections.sort(set);
        x = set.get(0).x();
        w = set.get(set.size() - 1).x() - set.get(0).x();

        Point.setSortKey(Point.SortBy.y);
        Collections.sort(set);
        y = set.get(0).y();
        h = set.get(set.size() - 1).y() - set.get(0).y();

        Point.setSortKey(Point.SortBy.y);
        return new Rectangle(new Point(x, y), w, h);
    }

    @Override
    public Cube getBoundingCube() {
        ArrayList<Point> set;
        int x, y, z, w, h, d;

        set = (ArrayList<Point>) this.verticies.clone();
        Collections.sort(set);
        x = set.get(0).x();
        w = set.get(set.size() - 1).x() - set.get(0).x();

        Point.setSortKey(Point.SortBy.y);
        Collections.sort(set);
        y = set.get(0).y();
        h = set.get(set.size() - 1).y() - set.get(0).y();

        Point.setSortKey(Point.SortBy.z);
        Collections.sort(set);
        z = set.get(0).z();
        d = set.get(set.size() - 1).z() - set.get(0).z();

        Point.setSortKey(Point.SortBy.y);
        return new Cube(new Point(x, y, z), w, h, d);

    }

    @Override
    public List<Point> getVertices() {
        return Collections.unmodifiableList(this.verticies);
    }

}
