import za.co.mahlaza.research.grammarengine.base.models.interfaces.InternalSlotRootAffix;
import za.co.mahlaza.research.grammarengine.base.models.template.PolymorphicWord;
import za.co.mahlaza.research.grammarengine.base.models.template.TemplatePortion;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class DragMouseAdapter extends MouseAdapter {
    private static final int xoffset = 16;
    private static final Rectangle R1 = new Rectangle();
    private static final Rectangle R2 = new Rectangle();
    private static Rectangle prevRect;
    private final JWindow window = new JWindow();
    private Component draggingComonent;
    private int index = -1;
    private Component gap;
    private Point startPt;
    private Point dragOffset;
    private final int gestureMotionThreshold = DragSource.getDragThreshold();

    JComponent superParent;
    JComponent parent2;
    //ControlThread controlThread;

    public DragMouseAdapter(JComponent comp1, JComponent comp2/**ControlThread controller*/) {
        super();
        superParent = comp1;
        parent2 = comp2;
        //this.controlThread = controller;
        window.setBackground(new Color(0, true));
    }
    public DragMouseAdapter(/**ControlThread controller*/) {
        super();
        //this.controlThread = controller;
        window.setBackground(new Color(0, true));
    }

    @Override public void mousePressed(MouseEvent e) {
        JComponent parent = (JComponent) e.getComponent();
        /**if (parent.getComponentCount() <= 1) {
            startPt = null;
            return;
        }*/

        startPt = e.getPoint();

        /****
         * SM
         */

        Component c = parent.getComponentAt(startPt);

        index = parent.getComponentZOrder(c);
        if (superParent != null && parent2 != null) {
            int in = superParent.getComponentZOrder(parent2);
            //System.out.println("Template portions size: " + ToCTeditor.dataModel.getTemplatePortions().size() + " Index of super element: " + in + " Index of pressed element: " + index);
            if (in >= 0) {
                List<InternalSlotRootAffix> morphemes = ((PolymorphicWord) ToCTeditor.dataModel.getTemplatePortion(in)).getAllMorphemes();
                //System.out.println("morphemes size: " + morphemes.size());
                ToCTeditor.templateItems.updateEditorPanel(ToCTeditor.templateItems.getInternalElementPanelEditor(morphemes.get(index)));
                ToCTeditor.templateItems.updateTurtlePanel(ToCTeditor.templateItems.getInternalElementPanelTurtle(morphemes.get(index)));
            }
        }
        else{

            //System.out.println("Super parent is null");
                ToCTeditor.gui.setIndex(index);
                ToCTeditor.templateItems.updateEditorPanel(ToCTeditor.templateItems.getPartPanelEditor(ToCTeditor.gui.getCurrentTemplatePortion()));
                ToCTeditor.templateItems.updateTurtlePanel(ToCTeditor.templateItems.getPartPanelTurtle(ToCTeditor.gui.getCurrentTemplatePortion()));
        }


    }



    private void startDragging(JComponent parent, Point pt) {
        //get a dragging panel
        Component c = parent.getComponentAt(pt);
        index = parent.getComponentZOrder(c);
        if (Objects.equals(c, parent) || index < 0) {
            return;
        }
        draggingComonent = c;
        Dimension d = draggingComonent.getSize();

        Point dp = draggingComonent.getLocation();
        dragOffset = new Point(pt.x - dp.x, pt.y - dp.y);

        //make a dummy filler
        gap = Box.createRigidArea(d);
        swapComponentLocation(parent, c, gap, index);

        //make a cursor window
        window.add(draggingComonent);
        window.pack();

        updateWindowLocation(pt, parent);
        window.setVisible(true);
    }

    private void updateWindowLocation(Point pt, JComponent parent) {
        Point p = new Point(pt.x - dragOffset.x, pt.y - dragOffset.y);
        SwingUtilities.convertPointToScreen(p, parent);
        window.setLocation(p);
    }

    private static int getTargetIndex(Rectangle r, Point pt, int i) {
        int ht2 = (int)(.5 + r.height * .5);
        R1.setBounds(r.x, r.y,       r.width, ht2);
        R2.setBounds(r.x, r.y + ht2, r.width, ht2);
        if (R1.contains(pt)) {
            prevRect = R1;
            return i - 1 > 0 ? i : 0;
        } else if (R2.contains(pt)) {
            prevRect = R2;
            return i;
        }
        return -1;
    }
    private static void swapComponentLocation(
            Container parent, Component remove, Component add, int idx) {
        parent.remove(remove);
        parent.add(add, idx);
        parent.revalidate();
        parent.repaint();
    }

    private static void updateList( int startIndex, int stopIndex ) {
        TemplatePortion templatePortion = ToCTeditor.dataModel.getTemplatePortion(startIndex);
        ToCTeditor.dataModel.removeTemplatePortion(startIndex);
        ToCTeditor.dataModel.addTemplatePortion(stopIndex, templatePortion);

    }

    private static void updateMorphesList( int in, int startIndex, int stopIndex ) {
        List <InternalSlotRootAffix> morphemes = ((PolymorphicWord) ToCTeditor.dataModel.getTemplatePortion(in)).getAllMorphemes();
        InternalSlotRootAffix moved = morphemes.remove(startIndex);
        morphemes.add(stopIndex, moved);
        for (int i = morphemes.size()-1; i >= 0 ; i--){
            ((PolymorphicWord) ToCTeditor.dataModel.getTemplatePortion(in)).removeMorpheme(i);
        }
        for (int i = 0; i < morphemes.size(); i++){
            ((PolymorphicWord) ToCTeditor.dataModel.getTemplatePortion(in)).addMorpheme(morphemes.get(i));
        }

    }

    @Override public void mouseDragged(MouseEvent e) {
        Point pt = e.getPoint();
        JComponent parent = (JComponent) e.getComponent();

        //MotionThreshold
        double a = Math.pow(pt.x - startPt.x, 2);
        double b = Math.pow(pt.y - startPt.y, 2);
        if (draggingComonent == null &&
                Math.sqrt(a + b) > gestureMotionThreshold) {
            startDragging(parent, pt);
            return;
        }
        if (!window.isVisible() || draggingComonent == null) {
            return;
        }

        //update the cursor window location
        updateWindowLocation(pt, parent);
        if (prevRect != null && prevRect.contains(pt)) {
            return;
        }

        //change the dummy filler location
        for (int i = 0; i < parent.getComponentCount(); i++) {
            Component c = parent.getComponent(i);
            Rectangle r = c.getBounds();
            if (Objects.equals(c, gap) && r.contains(pt)) {
                return;
            }
            int tgt = getTargetIndex(r, pt, i);
            if (tgt >= 0) {
                swapComponentLocation(parent, gap, gap, tgt);
                return;
            }
        }
        parent.remove(gap);
        parent.revalidate();
    }

    @Override public void mouseReleased(MouseEvent e) {
        startPt = null;
        if (!window.isVisible() || draggingComonent == null) {
            return;
        }
        Point pt = e.getPoint();
        JComponent parent = (JComponent) e.getComponent();

        //close the cursor window
        Component cmp = draggingComonent;
        draggingComonent = null;
        prevRect = null;
        startPt = null;
        dragOffset = null;
        window.setVisible(false);

        //swap the dragging panel and the dummy filler
        for (int i = 0; i < parent.getComponentCount(); i++) {
            Component c = parent.getComponent(i);
            if (Objects.equals(c, gap)) {
                if (superParent != null && parent2 != null) {
                    int in = superParent.getComponentZOrder(parent2);
                    System.out.println("Template portions size: " + ToCTeditor.dataModel.getTemplatePortions().size() + " Index of super element: " + in + " Index of pressed element: " + index);
                    if (in >= 0) {
                        updateMorphesList( in, index, i );
                    }
                    System.out.println("Morphes");
                }
                else{
                    updateList(index, i);
                    ToCTeditor.dataModel.updateNextPart();
                    System.out.println("NOT Morphes");
                }
                swapComponentLocation(parent, gap, cmp, i);
                return;
            }
            int tgt = getTargetIndex(c.getBounds(), pt, i);
            if (tgt >= 0) {
                if (superParent != null && parent2 != null) {

                    int in = superParent.getComponentZOrder(parent2);
                    System.out.println("Template portions size: " + ToCTeditor.dataModel.getTemplatePortions().size() + " Index of super element: " + in + " Index of pressed element: " + index);
                    if (in >= 0) {
                        updateMorphesList( in, index, i );
                    }
                    System.out.println("Morphes");
                }
                else{
                    updateList(index, i);
                    ToCTeditor.dataModel.updateNextPart();
                    System.out.println("NOT Morphes");
                }
                swapComponentLocation(parent, gap, cmp, tgt);
                return;
            }
        }
        if (parent.getParent().getBounds().contains(pt)) {
            if (superParent != null && parent2 != null) {
                int in = superParent.getComponentZOrder(parent2);
                System.out.println("Template portions size: " + ToCTeditor.dataModel.getTemplatePortions().size() + " Index of super element: " + in + " Index of pressed element: " + index);
                if (in >= 0) {
                    updateMorphesList( in, index, parent.getComponentCount() );
                }
                System.out.println("Morphes");
            }
            else{
                updateList(index, parent.getComponentCount());
                ToCTeditor.dataModel.updateNextPart();
                System.out.println("NOT Morphes");
            }

            swapComponentLocation(parent, gap, cmp, parent.getComponentCount());
        } else {
            swapComponentLocation(parent, gap, cmp, index);
        }
    }
}