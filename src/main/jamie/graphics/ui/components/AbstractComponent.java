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

package jamie.graphics.ui.components;
import jamie.graphics.ui.components.parts.Matroska;
import jamie.graphics.ui.components.parts.MatroskaContainer;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * @author  Roman Vais
 */

public abstract class AbstractComponent implements Component, MatroskaContainer {
    private static final int DEFAULT_WIDTH = 0;
    private static final int DEFAULT_HEIGHT = 0;

    private volatile Container parent;
    private volatile Rectangle box;
    private final Matroska matroska;
    private volatile boolean visible;

    public AbstractComponent() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public AbstractComponent(int width, int height) {
        this.parent = null;
        this.box = new Rectangle(width, height);
        this.matroska = new Matroska(this);
        this.matroska.setOriginalBox(this.box);
        this.visible = false;
    }

    public AbstractComponent(Dimension d) {
        this.parent = null;
        this.box = new Rectangle(d);
        this.matroska = new Matroska(this);
        this.matroska.setOriginalBoxSize(d);
        this.visible = false;
    }

    @Override
    public void setParent(Container parent) {
        this.parent = parent;
        this.updateBox();
    }

    @Override
    public Container getParent() {
        return this.parent;
    }

    @Override
    public void setSize(int width, int height) {
        this.matroska.setOriginalBoxSize(width, height);
        this.innerUpdateBox();
    }

    @Override
    public void setSize(Dimension d) {
        this.matroska.setOriginalBoxSize(d);
        this.innerUpdateBox();
    }

    @Override
    public Dimension getSize() {
        return new Dimension(this.box.getSize());
    }

    @Override
    public int getWidth() {
        return this.box.width;
    }

    @Override
    public int getHeight() {
        return this.box.height;
    }

    @Override
    public void setPosition(Point p) {
        this.matroska.setOriginalBoxPosition(p);
        this.innerUpdateBox();
    }

    @Override
    public void setPosition(int x, int y) {
        this.matroska.setOriginalBoxPosition(x, y);
        this.innerUpdateBox();
    }

    @Override
    public Point getPosition() {
        return new Point(this.box.getLocation());
    }

    @Override
    public Rectangle getBox() {
        return new Rectangle(box);
    }

    @Override
    public void setAlign(Align align) {
        this.matroska.setAlign(align);
        this.innerUpdateBox();
    }

    @Override
    public void setVerticalAlign(Align align) {
        this.matroska.setVerticalAlign(align);
        this.innerUpdateBox();
    }

    @Override
    public void setMargin(int top, int right, int bottom, int left) {
        this.matroska.setMargin(top, right, bottom, left);
        this.innerUpdateBox();
    }

    @Override
    public void setHorizontalTrailing(boolean trail) {
        this.matroska.setHorizontalTrailing(trail);
        this.innerUpdateBox();
    }

    @Override
    public boolean getHorizontalTrailing() {
        return this.matroska.getHorizontalTrailing();
    }

    @Override
    public void setVerticalTrailing(boolean trail) {
        this.matroska.setVerticalTrailing(trail);
        this.innerUpdateBox();
    }

    @Override
    public boolean getVerticalTrailing() {
        return this.matroska.getVerticalTrailing();
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
        if (visible) {
            this.updateBox();
        }
    }

    @Override
    public boolean getVisible() {
        return this.visible;
    }

    @Override
    public void updateBox() {
        this.matroska.update();
        this.innerUpdateBox();
    }

    private void innerUpdateBox() {
        this.box = this.matroska.getBox();
    }




}
