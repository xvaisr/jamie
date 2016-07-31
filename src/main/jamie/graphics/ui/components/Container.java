/**
 * Thesis project, BP, anthill strategy game refactored
 *
 * @author  Roman Vais, xvaisr00
 * @date    2015/05/27
 */

package jamie.graphics.ui.components;

import java.util.ArrayList;

/**
 *
 * @author lennylinux
 */
public interface Container extends Component {



    public void addComponent(Component comp);
    public ArrayList<Component> getComponents();
    public boolean removeComponent(Component comp);
    public void removeAllComponents();

    /*
        public void paintContent(Graphics g);

        public void aovChanged();
        public ViewSpaceHolder getViewSpaceHolder();

        public void registerEventListener(GUIEventListener listener);
        public void registerEventListeners();
        public void dispatchGUIEvent(GUIEvent e);
    */



}
