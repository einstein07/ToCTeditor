import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class RearrangeOrderOfPanelsTest2 {
    public JComponent makeUI() {
        Box box = Box.createHorizontalBox()/**createVerticalBox()*/;
        JScrollPane pnlScroll = new JScrollPane(box, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlScroll.setMaximumSize(new Dimension(350, 100));
        box.setMaximumSize(new Dimension(350, 100));
        DragMouseAdapter dh = new DragMouseAdapter();
        box.addMouseListener(dh);
        box.addMouseMotionListener(dh);

        int idx = 0;
        for (JComponent c : Arrays.asList(/**
                new JLabel("<html>111<br>11<br>11"),
                new JButton("2"), new JCheckBox("3"), new JTextField(14)*/
                new JLabel("onke"), new JLabel("noma"), new JLabel("nakanjani"),
                new JLabel("uzubenathi"), new JLabel("masifayo"), new JLabel("siyacela")

                    )) {
            box.add(createToolbarButton(idx++, c));
        }
        JPanel p = new JPanel();
        p.setMaximumSize(new Dimension(400, 260));
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        p.add(pnlScroll);
        return p;
    }

    private static JComponent createToolbarButton(int i, JComponent c) {
        JLabel l = new JLabel(String.format(" %04d ", i));
        l.setFont(new Font("Sans", Font.PLAIN, 12));
        //l.setOpaque(true);
        //l.setBackground(Color.RED);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLUE, 2)));
        JPanel pnlLabel = new JPanel();
        pnlLabel.setLayout(new BoxLayout(pnlLabel, BoxLayout.LINE_AXIS));
        pnlLabel.add(l);

        /**JPanel pnlComponent = new JPanel();
        pnlComponent.setLayout(new BoxLayout(pnlComponent, BoxLayout.LINE_AXIS));
        pnlComponent.add(c);*/

        c.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.setMaximumSize(new Dimension(100, 50));
        p.setMinimumSize(new Dimension(100, 50));
        p.add(c);
        p.add(pnlLabel);
        p.setOpaque(false);
        return p;
    }

    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane().add(new RearrangeOrderOfPanelsTest2().makeUI());
        f.setSize(400, 250);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

