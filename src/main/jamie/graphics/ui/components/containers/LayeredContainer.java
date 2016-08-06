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

package jamie.graphics.ui.components.containers;

import jamie.graphics.ui.components.AbstractContainer;
import jamie.graphics.ui.components.Component;
import jamie.graphics.ui.components.Container;
import java.util.ArrayList;

/**
 *
 * @author Roman Vais
 */
public class LayeredContainer extends AbstractContainer implements Container {

    private final ArrayList<Container> layers;

    public LayeredContainer() {
        this.layers = new ArrayList();

    }

    @Override
    public void addComponent(Component comp) {
        this.addComponent(comp, 0);
    }

    public void addComponent(Component comp, int layer) {
        synchronized (this.layers) {
            while (this.layers.size() <= layer) {
                Container c = new EmptyContainer();
                c.setParent(this);
                this.layers.add(c);
            }
        }
        this.layers.get(layer).addComponent(comp);
    }

    public int getLayerCount() {
        synchronized (this.layers) {
            return this.layers.size();
        }
    }

    @Override
    public ArrayList<Component> getComponents() {
        ArrayList<Component> list = new ArrayList();

        for (Container c : this.layers) {
            list.addAll(c.getComponents());
        }

        return list;
    }

    @Override
    public boolean removeComponent(Component comp) {
        boolean rm = false;
        for (Container c : this.layers) {
            rm = c.removeComponent(comp);
            if (rm) break;
        }
        return rm;
    }

    public boolean removeComponent(Component comp, int layer) {
        return layers.get(layer).removeComponent(comp);
    }

    @Override
    public void removeAllComponents() {
        for (Container c : this.layers) {
            c.removeAllComponents();
        }
    }

    @Override
    protected void updateContent() {
        for (Container c : this.layers)
            c.updateBox();
    }



}
