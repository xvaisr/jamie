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
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Roman Vais
 */
public class Cube implements Serializable, Shape3D {

    private Point bln;   // bottom left near
    private Point trf;   // top right far

    public Cube() {
        this(new Point(), new Point());
    }

    public Cube(int width, int heigth, int depth) {
        this(new Point(), width, heigth, depth);
    }

    public Cube(Dimension3D d) {
        this(new Point(), d);
    }

    public Cube(Point p, Dimension3D d) {
        this(p, p.translate(d.getWidth(), d.getHeight()));
    }

    public Cube(Point p, int width, int heigth, int depth) {
        this(p, p.translate(width, heigth, depth));
    }

    public Cube(Point bln, Point trf) {
        int x, y, z;

        if (trf.x() < bln.x()) {
            x = trf.x();
            trf.setLocation(bln.x(), trf.y());
            bln.setLocation(x, bln.y());
        }

        if (trf.y() < bln.y()) {
            y = trf.y();
            trf.setLocation(trf.x(), bln.y());
            bln.setLocation(bln.x(), y);
        }

        if (trf.y() < bln.y()) {
            y = trf.y();
            trf.setLocation(trf.x(), bln.y());
            bln.setLocation(bln.x(), y);
        }

        if (trf.z() < bln.z()) {
            z = trf.z();
            trf.setLocation(trf.z(), bln.z());
            bln.setLocation(bln.z(), z);
        }

        this.bln = bln;
        this.trf = trf;
    }

    @Override
    public boolean contains(Point p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public boolean intersects(Cube c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean intersects(Shape3D s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cube getBoundingCube() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Point> getVertices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
