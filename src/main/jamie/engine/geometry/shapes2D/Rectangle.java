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
        int xb, xt, yb, yt;

        xb = bl.x();
        yb = bl.y();

        xt = tr.x();
        yt = tr.y();

        if (xt < xb) {
            xb = xt;
            xt = bl.x();
        }

        if (yt < yb) {
            yb = yt;
            yt = bl.y();
        }

        this.bl = new Point(xb, yb);
        this.tr = new Point(xt, yt);
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
    public List<Point> getVertices() {
        ArrayList<Point> list = new ArrayList();
        int w = Math.abs(this.tr.x() - this.bl.x());
        Point a, b, c, d;

        try {
            a = this.bl.clone();
            c = this.tr.clone();

        }
        catch (CloneNotSupportedException ex) {
            a = new Point(this.bl.x(), this.bl.y());
            c = new Point(this.tr.x(), this.tr.y());
        }

        b = this.bl.translate(w, 0);
        d = this.tr.translate(-w, 0);

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

        return Collections.unmodifiableList(list);
    }

    @Override
    public Rectangle clone() throws CloneNotSupportedException {
        Rectangle r = (Rectangle) super.clone();
        r.bl = r.bl.clone();
        r.tr = r.tr.clone();
        return r;
    }

}
