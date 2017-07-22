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
package com.github.xvaisr.jamie.engine.geometry.shapes3D;

import com.github.xvaisr.jamie.engine.geometry.basic.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Roman Vais
 */
public class Polygon3D
        implements Serializable, Shape3D {

    private Point midpoint;
    private ArrayList<Point> verticies;

    public Polygon3D() {
        this(new Point());
    }

    public Polygon3D(Point midpoint) {
        this(midpoint, Collections.emptyList());
    }

    public Polygon3D(Point midpoint, List<Point> verticies) {
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
        if (!this.getBoundingCube().contains(p)) {
            return false;
        }

        return true; // TODO: finish the implementation of contain method
    }

    @Override
    public boolean contains(Cube c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Shape3D s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean intersects(Cube r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean intersects(Shape3D s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cube getBoundingCube() {
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
        return new Cube();
    }

    @Override
    public List<Point> getVertices() {
        return Collections.unmodifiableList(this.verticies);
    }

}
