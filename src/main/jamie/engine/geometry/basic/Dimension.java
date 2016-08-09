/*0,
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
public class Dimension implements Serializable {

    private final int w;
    private final int h;

    public Dimension() {
        this(0, 0);
    }

    public Dimension(int weight, int height) {
        this.w = weight;
        this.h = height;
    }

    public int getWidth() {
        return this.w;
    }

    public int getHeight() {
        return this.h;
    }

    @Override
    public String toString() {
        return this.w + "x" + this.h;
    }






}
