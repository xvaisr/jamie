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

package jamie.tools.algorithms.graph;

import jamie.tools.structures.Pair;

/**
 *
 * @author Roman Vais
 */
public class Edge {

    private final Pair<Node, Node> e;
    private final boolean notOriented;

    public Edge(Node a, Node b) {
        this(a, b, false);
    }

    public Edge(Node a, Node b, boolean oriented) {
        this.e = new Pair(a, b);
        this.notOriented = !oriented;
    }

}
