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

package jamie.engine.geometry.basic;

import java.io.Serializable;

/**
 *
 * @author Roman Vais
 */
public class Rectangle implements Serializable{

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



}
