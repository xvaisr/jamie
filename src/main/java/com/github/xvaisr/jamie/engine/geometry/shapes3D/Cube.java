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
        int xb, xt, yb, yt, zb, zt;

        xb = bln.x();
        yb = bln.y();
        zb = bln.z();

        xt = trf.x();
        yt = trf.y();
        zt = trf.z();

        if (xt < xb) {
            xb = xt;
            xt = bln.x();
        }

        if (yt < yb) {
            yb = yt;
            yt = bln.y();
        }

        if (zt < zb) {
            zb = zt;
            zt = bln.z();
        }

        this.bln = new Point(xb, yb, zb);
        this.trf = new Point(xt, yt, zt);
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
