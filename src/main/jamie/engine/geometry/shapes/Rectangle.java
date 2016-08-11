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

import jamie.engine.geometry.basic.Dimension;
import jamie.engine.geometry.basic.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Roman Vais
 */
public class Rectangle
        implements Serializable, Shape, Cloneable {

    private Point bl;   // bottom left
    private Point tr;   // top right

    public Rectangle() {
        this(new Point(), new Point());
    }

    public Rectangle(int width, int heigth) {
        this(new Point(), width, heigth);
    }

    public Rectangle(Dimension d) {
        this(new Point(), d);
    }

    public Rectangle(Point p, Dimension d) {
        this(p, p.translate(d.getWidth(), d.getHeight()));
    }

    public Rectangle(Point p, int width, int heigth) {
        this(p, p.translate(width, heigth));
    }

    public Rectangle(Point bl, Point tr) {
        int x, y;

        if (tr.x() < bl.x()) {
            x = tr.x();
            tr.setLocation(bl.x(), tr.y());
            bl.setLocation(x, bl.y());
        }

        if (tr.y() < bl.y()) {
            y = tr.y();
            tr.setLocation(tr.x(), bl.y());
            bl.setLocation(bl.x(), y);
        }

        this.bl = bl;
        this.tr = tr;
    }

    private void clonePoints() {
        try {
            this.bl = this.bl.clone();
            this.tr = this.tr.clone();
        }
        catch (CloneNotSupportedException ex) {
            this.bl = new Point(this.bl.x(), this.bl.y());
            this.tr = new Point(this.tr.x(), this.tr.y());
        }

    }

    public Point getBottomLeftCorner() {
        try {
            return bl.clone();
        }
        catch (CloneNotSupportedException ex) {
            return new Point(this.bl.x(), this.bl.y());
        }
    }

    public Dimension getDimension() {
        return new Dimension(bl, tr);
    }

    @Override
    public boolean contains(Point p) {
        boolean xAxis, yAxis;
        xAxis = (this.tr.x() - p.x()) >= 0 && (this.bl.x() - p.x()) <= 0;
        yAxis = (this.tr.y() - p.y()) >= 0 && (this.bl.y() - p.y()) <= 0;
        return xAxis && yAxis;
    }

    @Override
    public boolean contains(Rectangle r) {
        return this.contains(r.getBottomLeftCorner())
                && this.contains(r.getBottomLeftCorner());
    }

    @Override
    public boolean contains(Shape s) {
        return this.contains(s.getBoundingBox());
    }

    @Override
    public boolean intersects(Rectangle r) {
        return this.intersects((Shape) r);
    }

    @Override
    public boolean intersects(Shape s) {
        boolean intersects = false;
        Iterator<Point> it = s.getVertices().iterator();

        while (intersects == false && it.hasNext()) {
            intersects = this.contains(it.next());
        }

        return intersects;
    }

    @Override
    public Rectangle getBoundingBox() {
        Rectangle r;
        try {
            r = this.clone();
        }
        catch (CloneNotSupportedException ex) {
            r = new Rectangle(this.bl, this.tr);
        }
        r.clonePoints();
        return r;
    }

    @Override
    public Cube getBoundingCube() {
        Point bln, trf;
        try {
            bln = this.bl.clone();
            trf = this.tr.clone();
        }
        catch (CloneNotSupportedException ex) {
            bln = new Point(this.bl.x(), this.bl.y());
            trf = new Point(this.tr.x(), this.tr.y());
        }

        return new Cube(bln, trf);
    }

    @Override
    public List<Point> getVertices() {
        ArrayList<Point> list = new ArrayList();
        int w = Math.abs(this.tr.x() - this.bl.x());
        Point a, b, c, d;

        try {
            a = this.bl.clone();
            b = this.bl.clone();
            c = this.tr.clone();
            d = this.tr.clone();

        }
        catch (CloneNotSupportedException ex) {
            a = new Point(this.bl.x(), this.bl.y());
            b = new Point(this.bl.x(), this.bl.y());
            c = new Point(this.tr.x(), this.tr.y());
            d = new Point(this.tr.x(), this.tr.y());
        }

        b.move(w, 0);
        d.move(-w, 0);

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

        return Collections.unmodifiableList(list);
    }

    @Override
    public Rectangle clone() throws CloneNotSupportedException {
        return (Rectangle) super.clone();
    }

}
