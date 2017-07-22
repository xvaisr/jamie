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

package com.github.xvaisr.jamie.tools.algorithms.graph;

import com.github.xvaisr.jamie.tools.structures.Pair;
import java.util.Objects;

/**
 *
 * @author Roman Vais
 */
public class Edge<T> {

    private final Pair<Node<T>, Node<T>> e;
    private final boolean notOriented;

    public Edge(Node a, Node b) {
        this(a, b, false);
    }

    public Edge(Node a, Node b, boolean oriented) {
        this.e = new Pair(a, b);
        this.notOriented = !oriented;
    }

    public Node<T> getNodeA() {
        return this.e.getA();
    }

    public Node<T> getNodeB() {
        return this.e.getB();
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge) {
            Edge e = (Edge) o;

            if (e.notOriented) {
                return this.e.contains(e.getNodeA()) && this.e.contains(e.getNodeB());
            }
            else {
                return this.e.getA().equals(e.getNodeA()) && this.e.getB().equals(e.getNodeB());
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.e);
        hash = 17 * hash + (this.notOriented ? 1 : 0);
        return hash;
    }

}
