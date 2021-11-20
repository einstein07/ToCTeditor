import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @(#) CreateItem.java   1.0   Nov 12, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
public class CreateItem {
    private int frameX;
    private int frameY;
    private ToCTeditorFrame frame;

    //ControlThread controlThread;

    public CreateItem(ToCTeditorFrame frame/**, ControlThread controlThread*/) {
        this.frameX = frame.getFrameX();
        this.frameY = frame.getFrameY();
        this.frame = frame;

        //this.controlThread = controlThread;
    }

    public void setupGUI(){

        // Frame init and dimensions
        JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS));
        g.setSize(frameX,frameY);
        g.add(Box.createRigidArea(new Dimension(0,25)));
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setMaximumSize(new Dimension(800,525));

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        /**
         * Title panel
         */

        JPanel pnlItemsTitle = new JPanel();
        pnlItemsTitle.setLayout(new BoxLayout(pnlItemsTitle, BoxLayout.LINE_AXIS));
        pnlItemsTitle.setMaximumSize(new Dimension(700,15));
        pnlItemsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel("Template Items");
        pnlItemsTitle.setBackground(Color.lightGray);
        label.setFont(new Font("Sans", Font.PLAIN, 15));

        pnlItemsTitle.add(label);

        /**
         * Items panel
         */
        JPanel pnlItems = new JPanel();
        pnlItems.setLayout(new BoxLayout(pnlItems, BoxLayout.LINE_AXIS));
        pnlItems.setMaximumSize(new Dimension(700,100));
        pnlItems.setBackground(Color.lightGray);
        pnlItems.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlItems.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        /**
         * Panel for Existing item selection title
         */
        JPanel pnlExistingItemTitle = new JPanel();
        pnlExistingItemTitle.setLayout(new BoxLayout(pnlExistingItemTitle, BoxLayout.LINE_AXIS));
        pnlExistingItemTitle.setMaximumSize(new Dimension(700,15));
        pnlExistingItemTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlExistingItemTitle.setBackground(Color.lightGray);


        JLabel lblExistingItemTitle = new JLabel("Select item from existing");
        lblExistingItemTitle.setFont(new Font("Sans", Font.PLAIN, 15));
        lblExistingItemTitle.setMaximumSize(new Dimension(345,15));

        /**
         * Add title labels to labels panel
         */
        pnlExistingItemTitle.add(lblExistingItemTitle);


        /**
         * Existing Item panel
         */
        JPanel pnlExistingItem = new JPanel();
        pnlExistingItem.setLayout(new BoxLayout(pnlExistingItem, BoxLayout.LINE_AXIS));
        pnlExistingItem.setMaximumSize(new Dimension(700,250));
        pnlExistingItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlExistingItem.setBackground(Color.lightGray);
        pnlExistingItem.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));


        /**
         * Back button
         */
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Sans", Font.PLAIN, 15));
        btnBack.setMaximumSize(new Dimension(345,30));

        // add the listener to the jbutton to handle the "pressed" event
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //[snip]
                TemplateItems obj = new TemplateItems(frame/**, controlThread*/);
                obj.setupGUI();
            }
        });

        /**
         * Add button
         */
        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Sans", Font.PLAIN, 15));
        btnAdd.setMaximumSize(new Dimension(345,30));

        /**
         * Buttons panel
         */
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.LINE_AXIS));
        pnlButtons.setMaximumSize(new Dimension(700,30));
        pnlButtons.setBackground(Color.lightGray);

        /**
         * Add buttons to buttons panel
         */
        pnlButtons.add(btnBack);
        pnlButtons.add(Box.createRigidArea(new Dimension(10,0)));
        pnlButtons.add(btnAdd);




        // Add heading label to list panel
        listPane.add(pnlItemsTitle);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        // Add template name text field to list panel
        listPane.add(pnlItems);
        listPane.add(Box.createRigidArea(new Dimension(0,10)));
        listPane.add(pnlExistingItemTitle);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        // Add supported language dropdown button to list panel
        listPane.add(pnlExistingItem);
        listPane.add(Box.createRigidArea(new Dimension(0,10)));
        // Add create button to list panel
        listPane.add(pnlButtons);
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        listPane.setBackground(Color.LIGHT_GRAY);

        // Add list panel to main panel
        pnlMain.add(listPane);
        pnlMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        pnlMain.setBackground(Color.LIGHT_GRAY);

        g.add(pnlMain);


        frame.setContentPane(g);
    }
}
