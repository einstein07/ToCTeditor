/*
 * @(#) CreateTemplate.java   1.0   Nov 10, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTemplate {
    private int frameX;
    private int frameY;
    private ToCTeditorFrame frame;

    //private ControlThread controlThread;
    CreateTemplate(ToCTeditorFrame frame/**, ControlThread controlThread*/){
        this.frameX = frame.getFrameX();
        this.frameY = frame.getFrameX();
        this.frame = frame;

        //this.controlThread = controlThread;
    }

    public void setupGUI() {
        // Frame init and dimensions
        JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS));
        g.setSize(frameX,frameY);
        g.add(Box.createRigidArea(new Dimension(0,100)));
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setMaximumSize(new Dimension(600,250));

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Create New Template");
        //label.setForeground(Color.blue);
        label.setFont(new Font("Sans", Font.PLAIN, 24));

        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        /**
         * Template name text field
         */
        HintTextField txtEntryTemplateName = new HintTextField("Template name");
        txtEntryTemplateName.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtEntryTemplateName.setMaximumSize(new Dimension(400,30));
        txtEntryTemplateName.setFont(new Font("Sans", Font.PLAIN, 14));

        txtEntryTemplateName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Inside action performed for textfield");
                String text = txtEntryTemplateName.getText().trim();
                //ToCTeditor.controller.setPartName(text);
                System.out.println("Current text: " + text);
            }
        });

        /**
         * Supported language dropdown button
         */
        String[] languages = { "Supported language", "Zulu", "Xhosa", "Swati", "Ndebele" };
        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox supportedLanguage = new JComboBox(languages);
        supportedLanguage.setSelectedIndex(0);
        supportedLanguage.setAlignmentX(Component.CENTER_ALIGNMENT);
        supportedLanguage.setMaximumSize(new Dimension(400,30));
        supportedLanguage.setFont(new Font("Sans", Font.PLAIN, 14));

        /**
         * Create button
         */
        JButton btnCreate = new JButton("Create");
        btnCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCreate.setFont(new Font("Sans", Font.PLAIN, 15));
        btnCreate.setMaximumSize(new Dimension(400,30));


        // add the listener to the jbutton to handle the "pressed" event
        btnCreate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //[snip]
                ToCTeditor.gui.setCallTemplateItems(true);
            }
        });



        // Add heading label to list panel
        listPane.add(label);
        listPane.add(Box.createRigidArea(new Dimension(0,30)));
        // Add template name text field to list panel
        listPane.add(txtEntryTemplateName);
        listPane.add(Box.createRigidArea(new Dimension(0,10)));
        // Add supported language dropdown button to list panel
        listPane.add(supportedLanguage);
        listPane.add(Box.createRigidArea(new Dimension(0,50)));
        // Add create button to list panel
        listPane.add(btnCreate);
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        listPane.setBackground(Color.LIGHT_GRAY);

        // Add list panel to main panel
        pnlMain.add(listPane);
        pnlMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        pnlMain.setBackground(Color.LIGHT_GRAY);

        g.add(pnlMain);

        //frame.getContentPane().add(g);
        //frame.add(g); //add contents to window
        frame.setContentPane(g);
    }
}
