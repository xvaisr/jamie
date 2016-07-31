/**
 * Thesis project, BP, anthill strategy game refactored
 *
 * @author  Roman Vais, xvaisr00
 * @date    2015/05/27
 */
package jamie.graphics.ui.components.containers;

//import GraphicInterface.InterfaceViews.View;
import jamie.graphics.ui.components.AbstractContainer;
import java.awt.Dimension;

/**
 *
 * @author lennylinux
 */
public class EmptyContainer extends AbstractContainer {

    public EmptyContainer() {
        super();
    }

    public EmptyContainer(Dimension size) {
        super();
        super.setSize(size);
    }

    public EmptyContainer(int width, int height) {
        super();
        super.setSize(width, height);
    }


}
