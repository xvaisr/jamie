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

package jamie.graphics.ui.merge;

import jamie.graphics.ui.components.Container;
import jamie.graphics.ui.components.containers.EmptyContainer;
import java.awt.Dimension;

/**
 *
 * @author Roman Vais
 */
public class UserIntreface {

    //public static final Dimension RESOLUTION_x = new Dimension(, );

    public static final Dimension RESOLUTION_1024x768 = new Dimension(1024, 768);
    public static final Dimension RESOLUTION_1280x800 = new Dimension(1280, 800);
    public static final Dimension RESOLUTION_1280x1024 = new Dimension(1280, 1024);
    public static final Dimension RESOLUTION_1366x768 = new Dimension(1366, 768);
    public static final Dimension RESOLUTION_1600x900 = new Dimension(1600, 900);
    public static final Dimension RESOLUTION_1680x1050 = new Dimension(1680, 1050);
    public static final Dimension RESOLUTION_1920x1080 = new Dimension(1920, 1080);

    private static final Dimension RESOLUTION_DEFAULT = RESOLUTION_1920x1080;
    private static final UserIntreface ui = new UserIntreface();

    private Container spaceHolder;

    private UserIntreface() {
        spaceHolder = new EmptyContainer();
    };

    public static UserIntreface getInstance() {
        return UserIntreface.ui;
    };

    public void setBaseResolution(Dimension resolution) {
        spaceHolder.setSize(resolution);
    }

    public void setBaseResolution(int w, int h) {
        spaceHolder.setSize(w, h);
    }

    public void buildInterface() {
        
    }


}
