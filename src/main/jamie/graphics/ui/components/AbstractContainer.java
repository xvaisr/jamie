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

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author  Roman Vais
 */

public abstract class AbstractContainer extends AbstractComponent implements Container {

    private static final int DEFAULT_WIDTH = 0;
    private static final int DEFAULT_HEIGHT = 0;
    private static final int CAPACITY = 5;

    private volatile Container parent;

    private final ArrayList<Component> components;

    public AbstractContainer() {
        this(null);
    }

    public AbstractContainer(Container parent) {
        super(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        this.parent = parent;
        this.components = new ArrayList(CAPACITY);
        super.setHorizontalTrailing(true);
        super.setVerticalTrailing(true);
    }

    /*
    public final void setNewView(View view) {
        if (view == null) {
            return;
        }

        this.view = view;
        this.aovChanged();
    }



      public void setParent(Container parent);
      implements superclass AbstractComponent
     */

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.updateContent();
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        this.updateContent();
    }
    /*
      public Dimension getSize();
      public int getWidth();
      public int getHeight();
      implements superclass AbstractComponent
    */

    @Override
    public void setPosition(Point p) {
        super.setPosition(p);
        this.updateContent();
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        this.updateContent();
    }

    /*
      public Rectangle getBox();
      implements superclass AbstractComponent
    */

    /*
    @Override
    public void setAlign(EnumAlign align) {
        super.setAlign(align);
        this.updateContent();
    }

    @Override
    public void setVerticalAlign(EnumAlign align) {
        super.setVerticalAlign(align);
        this.updateContent();
    }

    */

    @Override
    public void setMargin(int top, int right, int bottom, int left) {
        super.setMargin(top, right, bottom, left);
        this.updateContent();
    }

    @Override
    public void setHorizontalTrailing(boolean trail) {
        super.setHorizontalTrailing(trail);
        this.updateContent();
    }



    @Override
    public void setVerticalTrailing(boolean trail) {
        super.setVerticalTrailing(trail);
        this.updateContent();
    }

    /*
      public boolean getHorizontalTrailing();
      public boolean getVerticalTrailing();
      implements superclass AbstractComponent
    */

    @Override
    public final void setVisible(boolean visible) {
        super.setVisible(visible);

        synchronized (this.components) {
            for (Component comp : this.components) {
                comp.setVisible(visible);
            }
        }
    }

    /*
      public boolean getVisible();
      implements superclass AbstractComponent
    */

    @Override
    public final void updateBox() {
        super.updateBox();
        this.updateContent();
    }

    protected void updateContent() {
        synchronized (this.components) {
            for (Component comp : this.components) {
                comp.updateBox();
            }
        }
    }

    @Override
    public void addComponent(Component comp) {
        synchronized (this.components) {
            this.components.add(comp);
            comp.setParent(this);
        }
    }

    @Override
    public ArrayList<Component> getComponents() {
        return (ArrayList<Component>) components.clone();
    }

    @Override
    public boolean removeComponent(Component comp) {
        synchronized (this.components) {
            boolean rm = false;
            rm = this.components.remove(comp);
            if (rm) comp.setParent(null);
            return rm;
        }

    }

    @Override
    public void removeAllComponents() {
        synchronized (this.components) {
            this.components.clear();
        }
    }

    /*

    @Override
    public void paintContent(Graphics g) {
        if (!this.getVisible()) {
            return;
        }
        synchronized (this.components) {
            for (Component comp : this.components) {
                comp.paint(g);
            }
        }
    }



    @Override
    public void paint(Graphics g) {
        if (!this.getVisible()) {
            return;
        }
        this.paintContent(g);
    }

    @Override
    public void aovChanged() {
        ViewSpaceHolder spaceholder = this.getViewSpaceHolder();
        if (spaceholder == null) {
            return;
        }

        this.setSize(spaceholder.getAreaOfView().getSize());
    }

    @Override
    public final ViewSpaceHolder getViewSpaceHolder() {
        if (this.view != null) {
            return this.view;
        }
        else if (this.parent != null) {
            return this.parent.getViewSpaceHolder();
        }
        return null;
    }

    @Override
    public void handleGUIEvent(GUIEvent e) {
        synchronized (this.components) {
            for (Component comp : this.components) {
                if (comp instanceof Container) {
                    ((Container) comp).handleGUIEvent(e);
                }
                else if (comp instanceof InteractiveComponent) {
                    ((InteractiveComponent) comp).handleGUIEvent(e);
                }
            }
        }
    }

    */

}
