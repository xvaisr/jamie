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

package com.github.xvaisr.jamie.engine.geometry.shapes2D;

import com.github.xvaisr.jamie.engine.geometry.basic.Point;
import com.github.xvaisr.jamie.tools.algorithms.graph.Graph;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Roman Vais
 */
public class Triangle implements Serializable, Cloneable, Shape {

    private Point centroid;
    private ArrayList<Point> verticies;
    private ArrayList<LineSegment> edges;

    public Triangle() {
        this(new Point(-1, -1), new Point(1, -1), new Point(0, 1));
    }

    public Triangle(Point a, Point b, Point c) {
        this.verticies = new ArrayList();
        this.verticies.add(a);
        this.verticies.add(b);
        this.verticies.add(c);

        // TODO: compute centroid of this triangle
        this.centroid = new Point();
    }


    @Override
    public boolean contains(Point p) {
        Rectangle r = this.getBoundingBox();
        if (!r.contains(p)) {
            return false;
        }

        boolean in = false;
        Iterator<Point> itp = this.getVertices().iterator();

        while(in == false && itp.hasNext()) {
            Point q = itp.next();
            in = q.equals(p);
        }

        if (in) {
            return in;
        }

        LineSegment ls = new LineSegment(p, this.centroid);
        Iterator<LineSegment> ite = this.getEdges().iterator();

        while(in == false && ite.hasNext()) {
            LineSegment edge = ite.next();
            in = ls.getIntersects(edge);
        }

        return !in;
    }

    @Override
    public boolean contains(Rectangle r) {
        return this.contains((Shape) r);

    }

    @Override
    public boolean contains(Shape s) {
        if (!this.getBoundingBox().contains(s.getBoundingBox())) {
            return false;
        }

        // if bounding box contains the shape but shapes do not intersect, than other shape is contained by this one
        return !this.intersects(s);
    }

    @Override
    public boolean intersects(Rectangle r) {
        return this.intersects((Shape) r);
    }

    @Override
    public boolean intersects(Shape s) {
        boolean in = false;

        LineSegment mineEdge, itsEdge;
        Iterator<LineSegment> mine, its;
        mine = this.getEdges().iterator();
        its = s.getEdges().iterator();

        while(in == false && mine.hasNext()) {
            mineEdge = mine.next();
            while (in == false && its.hasNext()) {
                itsEdge = its.next();

                in = mineEdge.getIntersects(itsEdge);
            }
        }

        return in;
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
    public List<Point> getVertices() {
        return Collections.unmodifiableList(this.verticies);
    }

    @Override
    public List<LineSegment> getEdges() {
        // serves for lazy evaluation
        if (this.edges == null)  {
            this.edges = new ArrayList();
        }

        if (this.edges.isEmpty()) { // serves for lazy evaluation

            Point v1, v2, st;
            Iterator<Point> it;

            v2 = null;
            st = null;

            it = this.verticies.iterator();
            if (it.hasNext()) {
                v2 = it.next();
                st = v2;
            }

            while (it.hasNext()) {
                v1 = v2;
                v2 = it.next();

                this.edges.add(new LineSegment(v1, v2));
            }

            if (v2 != st && st != null) {
                this.edges.add(new LineSegment(v2, st));
            }
        }

        return Collections.unmodifiableList(this.edges);
    }

    @Override
    public Graph getGraph() {
        return null;
    }

    @Override
    public LineSegment clone() throws CloneNotSupportedException {
        LineSegment ls = (LineSegment) super.clone();
        return ls;
    }
}
