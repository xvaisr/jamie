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

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Roman Vais
 */
public class Graph<T> {

    private final boolean oriented;
    private Node<T> lastNode;
    private final Set<Edge<T>> edges;
    private final Set<Node<T>> nodes;

    public Graph() {
        this(false, null);
    }

    public Graph(boolean oriented) {
        this(oriented, null);
    }

    public Graph(boolean oriented, Node<T> rootNode) {
        this.oriented = oriented;
        this.lastNode = rootNode;
        this.edges = new LinkedHashSet();
        this.nodes = new LinkedHashSet();
        this.nodes.add(rootNode);
    }

    public void addNode(Node<T> n) {
        if (lastNode == null && this.nodes.isEmpty()) {
            this.nodes.add(n);
            lastNode = n;
            return;
        }
        else if (lastNode == null) {
            lastNode = this.nodes.iterator().next();
        }

        this.nodes.add(n);
        this.edges.add(new Edge(this.lastNode, n, this.oriented));
        this.lastNode = n;
    }

    public Node<T> getCurrentNode() {
        return this.lastNode;
    }


}
