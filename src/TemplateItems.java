/*
 * @(#) TemplateItems.java   1.0   Nov 12, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

import za.co.mahlaza.research.grammarengine.base.interfaces.SlotFiller;
import za.co.mahlaza.research.grammarengine.base.models.feature.ConcordType;
import za.co.mahlaza.research.grammarengine.base.models.feature.Feature;
import za.co.mahlaza.research.grammarengine.base.models.interfaces.InternalSlotRootAffix;
import za.co.mahlaza.research.grammarengine.base.models.interfaces.Word;
import za.co.mahlaza.research.grammarengine.base.models.template.*;
import za.co.mahlaza.research.grammarengine.base.models.template.Concord;
import za.co.mahlaza.research.grammarengine.base.models.template.Copula;
import za.co.mahlaza.research.grammarengine.base.models.template.Locative;
import za.co.mahlaza.research.grammarengine.base.models.template.Phrase;
import za.co.mahlaza.research.grammarengine.base.models.template.PolymorphicWord;
import za.co.mahlaza.research.grammarengine.base.models.template.Punctuation;
import za.co.mahlaza.research.grammarengine.base.models.template.Root;
import za.co.mahlaza.research.grammarengine.base.models.template.Slot;
import za.co.mahlaza.research.grammarengine.base.models.template.UnimorphicAffix;
import za.co.mahlaza.research.grammarengine.base.models.template.UnimorphicWord;
import za.co.mahlaza.research.grammarengine.nguni.zu.ZuluFeatureParser;
import za.co.mahlaza.research.templateparsing.TemplateReader;
import za.co.mahlaza.research.templateparsing.TemplateWriter;
import za.co.mahlaza.research.templateparsing.URIS;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class TemplateItems {
    private int frameX;
    private int frameY;
    private ToCTeditorFrame frame;


    JPanel pnlItems;

    JPanel pnlEditorPreview;
    JPanel pnlItemEditor;
    JPanel pnlAnnotate;
    JPanel pnlTurtlePreview;
    JPanel pnlEditorTurtleTitle;
    JPanel pnlAnnotateTurtle;

    JToggleButton tbtnTurtle;

    JComponent superParent;
    JComponent parent2;
    public TemplateItems(ToCTeditorFrame frame/**, ControlThread controlThread*/) {
        this.frameX = frame.getFrameX();
        this.frameY = frame.getFrameY();
        this.frame = frame;
        //this.controlThread = controlThread;
    }

    public void setupGUI(JPanel currentPartProperties, JPanel turtlePreview){


        // Frame init and dimensions
        JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS));
        g.setSize(frameX,frameY);
        g.add(Box.createRigidArea(new Dimension(0,12)));
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setMinimumSize(new Dimension(800,550));
        pnlMain.setMaximumSize(new Dimension(800,550));

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
        pnlItems = new JPanel();
        pnlItems.setLayout(new BoxLayout(pnlItems, BoxLayout.LINE_AXIS));
        pnlItems.setMaximumSize(new Dimension(700,145));
        pnlItems.setBackground(Color.lightGray);
        pnlItems.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlItems.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        //ArrayList<TemplatePart> templateItems = new ArrayList<>();

        /**templateItems.add(new Slot("slot1", "s1", "concord1"));
         templateItems.add(new Concord("concord1", "c1", "subjectVerb", "root1"));
         templateItems.add(new Root("root1", "nke"));*/

        //pnlItems.add(setupTemplateItems(ToCTeditor.dataModel.getData()));
        pnlItems.add(setupTemplatePortions(ToCTeditor.dataModel.getTemplatePortions()));



        /**
         * Panel for Editor and Turtle titles
         */
        pnlEditorTurtleTitle = new JPanel();
        pnlEditorTurtleTitle.setLayout(new BoxLayout(pnlEditorTurtleTitle, BoxLayout.LINE_AXIS));
        pnlEditorTurtleTitle.setMaximumSize(new Dimension(700,15));
        pnlEditorTurtleTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlEditorTurtleTitle.setBackground(Color.lightGray);

        /**
         * Editor Preview panel
         */
        pnlEditorPreview = new JPanel();
        pnlEditorPreview.setLayout(new BoxLayout(pnlEditorPreview, BoxLayout.LINE_AXIS));
        pnlEditorPreview.setMinimumSize(new Dimension(700,300));
        pnlEditorPreview.setMaximumSize(new Dimension(700,300));
        pnlEditorPreview.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlEditorPreview.setBackground(Color.lightGray);

        setupEditorTurtlePanel(currentPartProperties, turtlePreview);

        /**
         * Back button
         */
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Sans", Font.PLAIN, 14));
        btnBack.setMaximumSize(new Dimension(170,30));

        // add the listener to the jbutton to handle the "pressed" event
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Any unsaved changes will be lost. Do you want to continue?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                    ToCTeditor.dataModel.clearTemplatePortions();
                    ToCTeditor.gui = new ViewThread();
                    ToCTeditor.gui.setCallCreateTemplate(true);
                    ToCTeditor.gui.start();
                }

            }
        });

        /**
         * Add button
         */
        JButton btnAdd = new JButton("Add Template Item");
        btnAdd.setFont(new Font("Sans", Font.PLAIN, 14));
        btnAdd.setMaximumSize(new Dimension(170,30));

        // add the listener to the jbutton to handle the "pressed" event
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                ToCTeditor.gui = new ViewThread();

                ToCTeditor.gui.setCallCreateItem(true, false);

                ToCTeditor.gui.start();
                /**if (ToCTeditor.gui.isAlive()){
                 try {
                 ToCTeditor.gui.join();
                 ToCTeditor.gui.start();
                 } catch (InterruptedException ex) {
                 ex.printStackTrace();
                 }
                 }
                 else{
                 ToCTeditor.gui.start();
                 }*/

            }
        });

        /**
         * Add button
         */
        JButton btnValidate = new JButton("Validate Template");
        btnValidate.setFont(new Font("Sans", Font.PLAIN, 14));
        btnValidate.setMaximumSize(new Dimension(170,30));

        // add the listener to the jbutton to handle the "pressed" event
        btnValidate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

            }
        });

        /**
         * Save template button
         */
        JButton btnSaveTemplate = new JButton("Save Template");
        btnSaveTemplate.setFont(new Font("Sans", Font.PLAIN, 14));
        btnSaveTemplate.setMaximumSize(new Dimension(170,30));

        // add the listener to the jbutton to handle the "pressed" event
        btnSaveTemplate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /**String templatePath = "";
                 try {
                 // Create a file object
                 File f = new File("program");
                 // Get the absolute path of file f
                 String absolute = f.getAbsolutePath();
                 TemplateWriter.Init(new ZuluFeatureParser());
                 TemplateWriter.saveTemplate(ToCTeditor.dataModel.getTemplate(), ToCTeditor.dataModel.getTemplate().getURI(), ToCTeditor.dataModel.getTemplate().getSerialisedName());
                 JOptionPane.showMessageDialog(null, "File saved successfully!");
                 }
                 catch(Exception err){
                 }*/
                PrintWriter writer = null;
                try {
                    String templateName = ToCTeditor.dataModel.getTemplate().getSerialisedName() + ".ttl";
                    System.out.println(templateName);
                    writer = new PrintWriter(templateName, "UTF-8");
                    if (ToCTeditor.dataModel.getTemplate() != null){
                        System.out.println("template: " + ToCTeditor.dataModel.getTemplate().toString());
                        System.out.println("template language: " + ToCTeditor.dataModel.getTemplate().getLanguage().getSerialisedName());
                    }
                    else{
                        System.out.println("template null");
                    }
                    writer.println(ToCTeditor.turtleGen.getTemplateTurtle().trim());
                    writer.close();

                    JOptionPane.showMessageDialog(null, "File saved successfully!");

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

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
        pnlButtons.add(Box.createRigidArea(new Dimension(6,0)));
        pnlButtons.add(btnAdd);
        pnlButtons.add(Box.createRigidArea(new Dimension(8,0)));
        pnlButtons.add(btnValidate);
        pnlButtons.add(Box.createRigidArea(new Dimension(6,0)));
        pnlButtons.add(btnSaveTemplate);




        // Add heading label to list panel
        listPane.add(pnlItemsTitle);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        // Add template name text field to list panel
        listPane.add(pnlItems);
        listPane.add(Box.createRigidArea(new Dimension(0,10)));
        //listPane.add(pnlToggleButton);
        listPane.add(Box.createRigidArea(new Dimension(0,10)));
        listPane.add(pnlEditorTurtleTitle);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        // Add supported language dropdown button to list panel
        listPane.add(pnlEditorPreview);
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
        /********************************/
        frame.repaint();
        /*****************************/
    }

    public void setupEditorTurtlePanel(JPanel currentPartProperties, JPanel turtlePreview){
        JLabel lblEditorTitle = new JLabel("Item property editor");
        lblEditorTitle.setFont(new Font("Sans", Font.PLAIN, 15));
        lblEditorTitle.setMaximumSize(new Dimension(345,15));

        JLabel lblAnnotateTemplate = new JLabel("Annotate Template");
        lblAnnotateTemplate.setFont(new Font("Sans", Font.PLAIN, 15));
        lblAnnotateTemplate.setMaximumSize(new Dimension(345,15));

        JLabel lblTurtleTitle = new JLabel("Item turtle preview");
        lblTurtleTitle.setFont(new Font("Sans", Font.PLAIN, 15));
        //lblTurtleTitle.setMaximumSize(new Dimension(345,15));
        //lblTurtleTitle.setHorizontalTextPosition(SwingConstants.LEFT);


        /**
         * Editor Panel
         */
        pnlItemEditor = new JPanel();
        pnlItemEditor.setLayout(new BoxLayout(pnlItemEditor, BoxLayout.Y_AXIS));

        pnlItemEditor.setBackground(Color.lightGray);
        pnlItemEditor.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        pnlItemEditor.add(currentPartProperties);

        pnlAnnotateTurtle = new JPanel();
        pnlAnnotateTurtle.setLayout(new BoxLayout(pnlAnnotateTurtle, BoxLayout.Y_AXIS));
        pnlAnnotateTurtle.setMinimumSize(new Dimension(345,300));
        pnlAnnotateTurtle.setMaximumSize(new Dimension(345,300));
        pnlAnnotateTurtle.setBackground(Color.lightGray);
        //pnlAnnotateTurtle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        /**
         * Panel for turtle syntax toggle button
         */
        JPanel pnlToggleButton = new JPanel();
        pnlToggleButton.setLayout(new BoxLayout(pnlToggleButton, BoxLayout.LINE_AXIS));
        pnlToggleButton.setMaximumSize(new Dimension(343,20));
        pnlToggleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlToggleButton.setBackground(Color.lightGray);


        if (tbtnTurtle == null) {
            tbtnTurtle = new JToggleButton("Show turtle preview");
            tbtnTurtle.setFont(new Font("Sans", Font.PLAIN, 14));
            tbtnTurtle.setMaximumSize(new Dimension(343, 20));
            //tbtnTurtle.setSelected(true);



            /**pnlToggleButton.add(Box.createRigidArea(new Dimension(10,0)));
             pnlToggleButton.add(btnSaveTemplate);*/

            ItemListener itemListener = new ItemListener() {
                public void itemStateChanged(ItemEvent itemEvent) {
                    int state = itemEvent.getStateChange();
                    if (state == ItemEvent.SELECTED) {
                        ToCTeditor.gui = new ViewThread();
                        ToCTeditor.gui.setToggleBtnState(state);

                        ToCTeditor.gui.setCallTemplateItems(true);

                        ToCTeditor.gui.start();
                    } else {
                        ToCTeditor.gui = new ViewThread();
                        ToCTeditor.gui.setToggleBtnState(state);

                        ToCTeditor.gui.setCallTemplateItems(true);

                        ToCTeditor.gui.start();
                    }
                }
            };

            tbtnTurtle.addItemListener(itemListener);
        }

        /**
         * Add label and toggle button panel
         */

        pnlToggleButton.add(tbtnTurtle);

        if ( tbtnTurtle != null && tbtnTurtle.isSelected() ){
            /**
             * Annotate template Panel
             */
            pnlAnnotate = new JPanel();
            pnlAnnotate.setLayout(new BoxLayout(pnlAnnotate, BoxLayout.Y_AXIS));

            pnlAnnotate.setMaximumSize(new Dimension(345,80));
            pnlAnnotate.setBackground(Color.lightGray);
            //pnlAnnotate.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            JTextArea annotate = new JTextArea();
            annotate.setMinimumSize(new Dimension(345,80));
            annotate.setMaximumSize(new Dimension(345, 80));
            annotate.setAlignmentX(Component.CENTER_ALIGNMENT);
            annotate.setFont(new Font("Sans", Font.PLAIN, 12));
            annotate.setForeground(Color.blue);
            addChangeListener(annotate, e -> updateTemplateAnnotation( annotate.getText()));
            JScrollPane pnlScroll = new JScrollPane(annotate, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            pnlScroll.setMinimumSize(new Dimension(345,80));
            pnlScroll.setMaximumSize(new Dimension(345, 80));
            pnlScroll.setBackground(Color.lightGray);
            pnlAnnotate.add(pnlScroll);

            /**
             * Turtle title panel
             */
            JPanel pnlTurtleTitle = new JPanel();
            pnlTurtleTitle.setLayout(new BoxLayout(pnlTurtleTitle, BoxLayout.LINE_AXIS));
            pnlTurtleTitle.setMinimumSize(new Dimension(343,15));
            pnlTurtleTitle.setMaximumSize(new Dimension(343,15));
            pnlTurtleTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            pnlTurtleTitle.setBackground(Color.lightGray);


            /**
             * Turtle Preview Panel
             */
            pnlTurtlePreview = new JPanel();
            pnlTurtlePreview.setLayout(new BoxLayout(pnlTurtlePreview, BoxLayout.Y_AXIS));
            pnlTurtlePreview.setMinimumSize(new Dimension(345,180));
            pnlTurtlePreview.setMaximumSize(new Dimension(345,180));
            pnlTurtlePreview.setBackground(Color.lightGray);
            //pnlTurtlePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            pnlTurtlePreview.add(turtlePreview);

            pnlAnnotateTurtle.add(pnlAnnotate);
            pnlAnnotateTurtle.add(Box.createRigidArea(new Dimension(0,5)));
            pnlAnnotateTurtle.add(pnlToggleButton);
            pnlAnnotateTurtle.add(Box.createRigidArea(new Dimension(0,5)));
            pnlTurtleTitle.add(lblTurtleTitle);
            pnlAnnotateTurtle.add(pnlTurtleTitle);
            pnlAnnotateTurtle.add(pnlTurtlePreview);

        }
        else{
            /**
             * Annotate template Panel
             */
            pnlAnnotate = new JPanel();
            pnlAnnotate.setLayout(new BoxLayout(pnlAnnotate, BoxLayout.Y_AXIS));

            pnlAnnotate.setMaximumSize(new Dimension(345,275));
            pnlAnnotate.setBackground(Color.lightGray);
            //pnlAnnotate.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            JTextArea annotate = new JTextArea();
            annotate.setMinimumSize(new Dimension(345,275));
            annotate.setMaximumSize(new Dimension(345, 275));
            annotate.setAlignmentX(Component.CENTER_ALIGNMENT);
            annotate.setFont(new Font("Sans", Font.PLAIN, 12));
            annotate.setForeground(Color.blue);
            addChangeListener(annotate, e -> updateTemplateAnnotation( annotate.getText()));
            JScrollPane pnlScroll = new JScrollPane(annotate, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            pnlScroll.setMinimumSize(new Dimension(345,275));
            pnlScroll.setMaximumSize(new Dimension(345, 275));
            pnlScroll.setBackground(Color.lightGray);
            pnlAnnotate.add(pnlScroll);

            pnlAnnotateTurtle.add(pnlAnnotate);
            pnlAnnotateTurtle.add(Box.createRigidArea(new Dimension(0,5)));
            pnlAnnotateTurtle.add(pnlToggleButton);
        }

        /**
         * Add title labels to labels panel
         */
            pnlEditorTurtleTitle.add(lblEditorTitle);
            pnlEditorTurtleTitle.add(Box.createRigidArea(new Dimension(10,0)));
            pnlEditorTurtleTitle.add(lblAnnotateTemplate);

            pnlItemEditor.setMinimumSize(new Dimension(345,300));
            pnlItemEditor.setMaximumSize(new Dimension(345,300));

        /**
         * Add panels to editor preview panel
         */
           pnlEditorPreview.add(pnlItemEditor);
           pnlEditorPreview.add(Box.createRigidArea(new Dimension(10,0)));
           pnlEditorPreview.add(pnlAnnotateTurtle);


        //if (ToCTeditor.gui.getToggleBtnState() == 1){

            /**
             * Add title labels to labels panel
             */
        //    pnlEditorTurtleTitle.add(lblEditorTitle);
        //    pnlEditorTurtleTitle.add(Box.createRigidArea(new Dimension(10,0)));
        //    pnlEditorTurtleTitle.add(lblAnnotateTemplate);

        //    pnlItemEditor.setMinimumSize(new Dimension(345,300));
        //    pnlItemEditor.setMaximumSize(new Dimension(345,300));

            /**
             * Add panels to editor preview panel
             */
        //   pnlEditorPreview.add(pnlItemEditor);
        //    pnlEditorPreview.add(Box.createRigidArea(new Dimension(10,0)));
        //    pnlEditorPreview.add(pnlAnnotateTurtle);
            //pnlEditorPreview.add(pnlTurtlePreview);
        //}
        //else{
            /**
             * Add title labels to labels panel
             */
        //    pnlEditorTurtleTitle.add(lblEditorTitle);
        //    pnlEditorTurtleTitle.add(Box.createRigidArea(new Dimension(10,0)));

        //    pnlItemEditor.setMaximumSize(new Dimension(700,250));
        //    pnlEditorPreview.add(pnlItemEditor);
        //}

    }
    public void updateEditorTurtlePanel(JPanel currentPartProperties, JPanel currentTurtleCode){

        JLabel lblEditorTitle = new JLabel("Item property editor");
        lblEditorTitle.setFont(new Font("Sans", Font.PLAIN, 15));
        lblEditorTitle.setMaximumSize(new Dimension(345,15));

        JLabel lblTurtleTitle = new JLabel("Item turtle preview");
        lblTurtleTitle.setFont(new Font("Sans", Font.PLAIN, 15));
        lblTurtleTitle.setMaximumSize(new Dimension(345,15));

        int titleCount = pnlEditorTurtleTitle.getComponentCount();
        for (int i = titleCount - 1; i >= 0 ; i--){
            pnlEditorTurtleTitle.remove(i);
        }

        if (ToCTeditor.gui.getToggleBtnState() == 1){



            /**
             * Add title labels to labels panel
             */
            pnlEditorTurtleTitle.add(lblEditorTitle);
            pnlEditorTurtleTitle.add(Box.createRigidArea(new Dimension(10,0)));
            pnlEditorTurtleTitle.add(lblTurtleTitle);

            int compCount = pnlEditorPreview.getComponentCount();
            for (int i = compCount - 1; i >= 0 ; i--){
                pnlEditorPreview.remove(i);
            }

            pnlItemEditor.setMaximumSize(new Dimension(345,250));
            pnlTurtlePreview.setMaximumSize(new Dimension(345,250));

            pnlTurtlePreview.remove(0);
            pnlTurtlePreview.add(currentTurtleCode);

            pnlEditorPreview.add(pnlItemEditor);
            pnlEditorPreview.add(Box.createRigidArea(new Dimension(10,0)));
            pnlEditorPreview.add(pnlTurtlePreview);

            this.pnlEditorPreview.revalidate();
        }
        else{
            /**
             * Add title label to labels panel
             */
            pnlEditorTurtleTitle.add(lblEditorTitle);
            int compCount = pnlEditorPreview.getComponentCount();
            for (int i = compCount - 1; i >= 0 ; i--){
                pnlEditorPreview.remove(i);
            }
            pnlItemEditor.setMaximumSize(new Dimension(700,250));
            pnlEditorPreview.add(pnlItemEditor);
            this.pnlEditorPreview.revalidate();
        }

    }
    public void updateEditorPanel(JPanel currentPartProperties){
        pnlItemEditor.remove(0);
        this.pnlItemEditor.add(currentPartProperties);
        this.pnlItemEditor.getParent().revalidate();
    }
    public void updateTurtlePanel(JPanel currentPartProperties){
        if ( tbtnTurtle != null && tbtnTurtle.isSelected() ) {
            this.pnlTurtlePreview.remove(0);
            this.pnlTurtlePreview.add(currentPartProperties);
            this.pnlTurtlePreview.getParent().revalidate();
        }
    }

    public void updateItems(){

        this.pnlItems.remove(0);
        pnlItems.add(setupTemplatePortions(ToCTeditor.dataModel.getTemplatePortions()));
        this.pnlItems.getParent().revalidate();
    }

    public JComponent setupTemplatePortions(List<TemplatePortion> list) {
        Box box = Box.createHorizontalBox();

        JScrollPane pnlScroll = new JScrollPane(box, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnlScroll.setMinimumSize(new Dimension(700, 145));
        pnlScroll.setMaximumSize(new Dimension(700, 145));

        DragMouseAdapter dh = new DragMouseAdapter();
        box.addMouseListener(dh);
        box.addMouseMotionListener(dh);

        for (TemplatePortion templatePortion : list) {
            String type = ToCTeditor.turtleGen.getPartType(templatePortion);

            if ( type.equals("Polymorphic word") && ((PolymorphicWord)templatePortion).getAllMorphemes().size() > 0){
                box.add(createPortionComponent(templatePortion, type, box));
            }
            else{
                JLabel name = new JLabel(templatePortion.getSerialisedName());
                JLabel lblType = new JLabel(type);
                box.add(createItemComponent(templatePortion, name, lblType, false));
            }
        }
        JPanel p = new JPanel();
        p.setMaximumSize(new Dimension(700, 145));
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        p.setBackground(Color.lightGray);
        p.add(pnlScroll);
        return p;
    }

    private JPanel createPortionComponent(TemplatePortion templatePortion, String type, JComponent box) {

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.white);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 5, 0, 5),
                BorderFactory.createLineBorder(Color.BLUE, 2)));

        /**
         * Set up panel for menu button, and then add menu button to the panel
         */
        JPanel pnlKebab = new JPanel();
        pnlKebab.setLayout(new BoxLayout(pnlKebab, BoxLayout.LINE_AXIS));
        pnlKebab.setBackground(Color.white);

        JPopupMenu menu = new JPopupMenu();
        menu.setMaximumSize(new Dimension(200, 100));
        menu.add("Duplicate").addActionListener(e -> {
                    ToCTeditor.dataModel.duplicateTemplatePortion(templatePortion.getSerialisedName());
                    ToCTeditor.dataModel.updateNextPart();
                    updateItems();
                }
        );
        //menu.add(new JMenuItem("Change type"));
        menu.add("Change type").addActionListener(e -> {
            ToCTeditor.gui = new ViewThread(ToCTeditor.homeScreen, ToCTeditor.templateItems, ToCTeditor.createItem, ToCTeditor.dataModel);
            ToCTeditor.gui.setIndex(ToCTeditor.dataModel.getTemplatePortionIndex(templatePortion));
            ToCTeditor.gui.setCallCreateItem(true, true);
            ToCTeditor.gui.start();
            }
        );

        menu.add("Remove").addActionListener( e -> {
                ToCTeditor.dataModel.removeTemplatePortion(templatePortion.getSerialisedName());
                ToCTeditor.dataModel.updateNextPart();
                updateItems();
        });

        final JButton button = new JButton();
        /**
         * Images folder is stored in the bin folder.
         * Reason why found here (last answer is the only one that works with a Makefile):
         * https://stackoverflow.com/questions/13151979/null-pointer-exception-when-an-imageicon-is-added-to-jbutton-in-netbeans
         */
        button.setIcon(createImageIcon("/images/kebab.png"));
        button.setBorderPainted(false);
        button.setBackground(Color.white);
        button.setMinimumSize(new Dimension(35,8));
        button.setMaximumSize(new Dimension(35,8));


        button.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ev){
                menu.show(button, 0, button.getHeight());
            }
        });

        /**
         * Set up panel for the item name, and then add a label to the panel
         */

        JLabel name = new JLabel(templatePortion.getSerialisedName());
        name.setFont(new Font("Sans", Font.PLAIN, 14));

        JPanel pnlName = new JPanel();
        pnlName.setLayout(new BoxLayout(pnlName, BoxLayout.Y_AXIS));
        pnlName.setBackground(Color.white);
        pnlName.add(name);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel nameAndButton = new JPanel();
        nameAndButton.setLayout(new BoxLayout(nameAndButton, BoxLayout.LINE_AXIS));
        nameAndButton.add((JLabel)name);
        nameAndButton.add(Box.createRigidArea(new Dimension(5,0)));
        nameAndButton.add(button);
        nameAndButton.setBackground(Color.white);
        /**
         * Set up morphemes
         */
        JPanel pnlMorphemes = new JPanel();
        pnlMorphemes.setLayout(new BoxLayout(pnlMorphemes, BoxLayout.LINE_AXIS));
        DragMouseAdapter dh = new DragMouseAdapter(box, p);
        pnlMorphemes.addMouseListener(dh);
        int maxWidth = 0;
        pnlMorphemes.addMouseMotionListener(dh);
        List<InternalSlotRootAffix> morphemesList = ((PolymorphicWord)templatePortion).getAllMorphemes();
        for (int i = 0; i < morphemesList.size(); i++){
            JLabel mName = new JLabel(morphemesList.get(i).getSerialisedName());
            JLabel mType = new JLabel(ToCTeditor.turtleGen.getMorphemeType(morphemesList.get(i)));
            maxWidth += 116;
            pnlMorphemes.add(createItemComponent(templatePortion, mName, mType, true));
        }

        pnlMorphemes.setBackground(Color.white);
        pnlKebab.setMinimumSize(new Dimension(maxWidth ,8));
        pnlKebab.setMaximumSize(new Dimension(maxWidth ,8));
        pnlKebab.add(Box.createRigidArea(new Dimension((maxWidth),0)));

        pnlKebab.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlName.setMinimumSize(new Dimension(maxWidth ,15));
        pnlName.setMaximumSize(new Dimension(maxWidth ,15));

        nameAndButton.setMinimumSize(new Dimension(maxWidth ,15));
        pnlName.add(nameAndButton);
        pnlMorphemes.setMinimumSize(new Dimension(maxWidth, 42));
        pnlMorphemes.setMaximumSize(new Dimension(maxWidth, 42));
        p.add(pnlName);
        pnlName.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(pnlMorphemes);
        pnlMorphemes.setAlignmentX(Component.CENTER_ALIGNMENT);

        /**
         * Set up panel for the item type, and then add a label to the panel
         */
        JLabel lblType = new JLabel(type);
        lblType.setFont(new Font("Sans", Font.PLAIN, 8));

        JPanel pnlType = new JPanel();
        pnlType.setLayout(new BoxLayout(pnlType, BoxLayout.LINE_AXIS));
        pnlType.setMaximumSize(new Dimension(maxWidth,8));
        pnlType.add(lblType);
        pnlType.setBackground(Color.white);
        p.add(pnlType);

        p.setMaximumSize(new Dimension(maxWidth , 90));
        p.setMinimumSize(new Dimension(maxWidth, 90));
        p.setOpaque(false);
        return p;
    }

    private JPanel createItemComponent(TemplatePortion portion, JComponent name, JComponent type, boolean isMorpheme) {

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.white);


        /**
         * Set up panel for menu button, and then add menu button to the panel
         */
        JPanel pnlKebab = new JPanel();
        pnlKebab.setLayout(new BoxLayout(pnlKebab, BoxLayout.LINE_AXIS));
        if (isMorpheme){
            p.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 2, 0, 2),
                    BorderFactory.createLineBorder(Color.BLUE, 2)));
            pnlKebab.setMinimumSize(new Dimension(92,8));
            pnlKebab.setMaximumSize(new Dimension(92,8));
        }
        else{
            p.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 5, 0, 5),
                    BorderFactory.createLineBorder(Color.BLUE, 2)));
            pnlKebab.setMaximumSize(new Dimension(100,20));
        }

        pnlKebab.setBackground(Color.white);

        JPopupMenu menu = new JPopupMenu();
        menu.setMaximumSize(new Dimension(200, 100));
        menu.add("Duplicate").addActionListener(e -> {

                ToCTeditor.dataModel.duplicateTemplatePortion(((JLabel)name).getText().trim());
                ToCTeditor.dataModel.updateNextPart();
                updateItems();
            }
        );
        //menu.add(new JMenuItem("Change type"));
        menu.add("Change type").addActionListener(e -> {
            if (isMorpheme){
                List <InternalSlotRootAffix> morphemes = ((PolymorphicWord)portion).getAllMorphemes();
                for (int i = 0; i < morphemes.size(); i++){
                    if (morphemes.get(i).getSerialisedName().equals(((JLabel)name).getText().trim())){
                        ToCTeditor.gui = new ViewThread();

                        ToCTeditor.createMorpheme.setMorphemeIndex(i);
                        ToCTeditor.createMorpheme.setChangeType(true);
                        ToCTeditor.gui.setIndex(ToCTeditor.dataModel.getTemplatePortionIndex(portion));
                        ToCTeditor.gui.setCallCreateMorpheme(true);

                        ToCTeditor.gui.start();
                        break;
                    }
                }
            } else{
                ToCTeditor.gui = new ViewThread();

                ToCTeditor.gui.setIndex(ToCTeditor.dataModel.getTemplatePortionIndex(portion));
                ToCTeditor.gui.setCallCreateItem(true, true);

                ToCTeditor.gui.start();
            }
        });

        menu.add("Remove").addActionListener( e -> {
            if (isMorpheme){
                List <InternalSlotRootAffix> morphemes = ((PolymorphicWord)portion).getAllMorphemes();
                for (int i = 0; i < morphemes.size(); i++){
                    if (morphemes.get(i).getSerialisedName().equals(((JLabel)name).getText().trim())){
                        /**((PolymorphicWord)ToCTeditor.dataModel.getTemplatePortion(portion))*/((PolymorphicWord)portion).removeMorpheme(i);
                        updateItems();
                        break;
                    }
                }
            }
            else{
                ToCTeditor.dataModel.removeTemplatePortion(portion.getSerialisedName());
                ToCTeditor.dataModel.updateNextPart();
                updateItems();
            }
        });

        final JButton button = new JButton();
        /**
         * Images folder is stored in the bin folder.
         * Reason why found here (last answer is the only one that works with a Makefile):
         * https://stackoverflow.com/questions/13151979/null-pointer-exception-when-an-imageicon-is-added-to-jbutton-in-netbeans
         */
        button.setIcon(createImageIcon("/images/kebab.png"));
        button.setBorderPainted(false);
        button.setBackground(Color.white);
        if (isMorpheme){
            pnlKebab.add(Box.createRigidArea(new Dimension(50,0)));
            button.setMinimumSize(new Dimension(35,8));
            button.setMaximumSize(new Dimension(35,8));
        }
        else {
            pnlKebab.add(Box.createRigidArea(new Dimension(30,0)));
            button.setMaximumSize(new Dimension(35, 15));
        }
        button.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ev){
                menu.show(button, 0, button.getHeight());
            }
        });


        pnlKebab.add(button);
        /**
         * Set up panel for the item name, and then add a label to the panel
         */
        name.setFont(new Font("Sans", Font.PLAIN, 14));

        JPanel pnlName = new JPanel();
        pnlName.setLayout(new BoxLayout(pnlName, BoxLayout.Y_AXIS));
        if (isMorpheme){
            pnlName.setMinimumSize(new Dimension(92,15));
            pnlName.setMaximumSize(new Dimension(92,15));
        }
        else{
            pnlName.setMaximumSize(new Dimension(100,50));
        }
        pnlName.add(name);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlName.setBackground(Color.white);

        /**
         * Set up panel for the item type, and then add a label to the panel
         */
        type.setFont(new Font("Sans", Font.PLAIN, 8));

        JPanel pnlType = new JPanel();
        pnlType.setLayout(new BoxLayout(pnlType, BoxLayout.LINE_AXIS));
        pnlType.add(type);
        pnlType.setBackground(Color.white);


        if (isMorpheme){
            pnlType.setMinimumSize(new Dimension(92,8));
            pnlType.setMaximumSize(new Dimension(92,8));
            p.setMaximumSize(new Dimension(92, 42));
        }
        else{
            pnlType.setMaximumSize(new Dimension(100,30));
            p.setMaximumSize(new Dimension(100, 90));
        }
        p.add(pnlKebab);
        pnlKebab.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(pnlName);
        pnlName.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(pnlType);
        pnlType.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.setOpaque(false);
        return p;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Working get part editor method
     * @param currentTemplatePortion
     * @return
     */
    public JPanel getPartPanelEditor(TemplatePortion currentTemplatePortion) {

        String type = ToCTeditor.turtleGen.getPartType(currentTemplatePortion);
        JPanel currentPanel;

        if (type.equals("Slot")){
            Slot part = (Slot)currentTemplatePortion;
            currentPanel = setupSlotEditor(part);
        }
        else if (type.equals("Unimorphic word")){
            UnimorphicWord part = (UnimorphicWord)currentTemplatePortion;
            currentPanel = setupUnimorphicWordEditor(part);
        }
        else if (type.equals("Punctuation")){
            Punctuation part = (Punctuation)currentTemplatePortion;
            currentPanel = setupPunctuationEditor(part);
        }
        else if (type.equals("Polymorphic word")){
            PolymorphicWord part = (PolymorphicWord)currentTemplatePortion;
            currentPanel = setupPolymorphicWordEditor(part);
        }
        else if ( type.equals("Phrase") ){
            Phrase part = (Phrase)currentTemplatePortion;
            currentPanel = setupPhraseEditor(part);
        }
        /**else if ( type.equals("Phrase") ){
         Phrase part = (Phrase)currentTemplatePortion;
         currentPanel = setupPhraseEditor(part);
         }
         else if ( type.equals("Copula") ){
         Copula part = (Copula)currentPart;
         currentPanel = setupCopulaEditor(part);
         }
         else if ( type.equals("Unimorphic affix")){
         UnimorphicAffix part = (UnimorphicAffix)currentPart;
         currentPanel = setupUnimorphicAffixEditor(part);
         }
         else if (type.equals("Root")){
         Root part = (Root)currentPart;
         currentPanel = setupRootEditor(part);
         }*/
        else{
            currentPanel = new JPanel();
            currentPanel.setMaximumSize(new Dimension(345,250));
            currentPanel.setBackground(Color.lightGray);
        }
        return currentPanel;
    }

    public JPanel getInternalElementPanelEditor(InternalSlotRootAffix currentInternalElement) {
        JPanel currentPanel;
        if (currentInternalElement.getType().equals("Slot")){
            Slot part = (Slot)currentInternalElement;
            currentPanel = setupSlotEditor(part);
        }
        else if ( currentInternalElement.getType().equals("Concord") ){
            Concord part = (Concord) currentInternalElement;
            currentPanel = setupConcordEditor(part);
        }
        else if ( currentInternalElement.getType().equals("Copula") ){
            Copula part = (Copula)currentInternalElement;
            currentPanel = setupCopulaEditor(part);
        }
        else if ( currentInternalElement.getType().equals("UnimorphicAffix")){
            UnimorphicAffix part = (UnimorphicAffix)currentInternalElement;
            currentPanel = setupUnimorphicAffixEditor(part);
        }
        else if (currentInternalElement.getType().equals("Root")){
            Root part = (Root)currentInternalElement;
            currentPanel = setupRootEditor(part);
        }
        else if (currentInternalElement.getType().equals("Locative")){
            Locative part = (Locative)currentInternalElement;
            currentPanel = setupLocativeEditor(part);
        }
        else{
            currentPanel = new JPanel();
            currentPanel.setBackground(Color.lightGray);
        }
        return currentPanel;
    }

    /**
     * Working getPartPanelTurtle method
     * @param currentTemplatePortion
     * @return
     */
    public JPanel getPartPanelTurtle(TemplatePortion currentTemplatePortion) {
        //String type = ToCTeditor.turtleGen.getPartType(currentPart);
        JPanel currentPanel = new JPanel();
        currentPanel.setLayout(new BoxLayout(currentPanel, BoxLayout.Y_AXIS));
        currentPanel.setBackground(Color.lightGray);
        currentPanel.setMaximumSize(new Dimension(345, 180));
        JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setMinimumSize(new Dimension(345, 180));
        txtArea.setMaximumSize(new Dimension(345, 180));
        txtArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtArea.setFont(new Font("Sans", Font.PLAIN, 12));
        txtArea.setForeground(Color.blue);
        JScrollPane pnlScroll = new JScrollPane(txtArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlScroll.setMinimumSize(new Dimension(345, 180));
        pnlScroll.setMaximumSize(new Dimension(345, 180));
        pnlScroll.setBackground(Color.lightGray);

        String turtle;
        if (currentTemplatePortion != null) {
            turtle = ToCTeditor.turtleGen.getPartTurtle(currentTemplatePortion);
        }
        else {
            turtle = ToCTeditor.turtleGen.getTemplateTurtle();
        }
        if (turtle.length() > 0 ){
            txtArea.setText(turtle);
            currentPanel.add(pnlScroll);
        }
        return currentPanel;
    }

    public JPanel getInternalElementPanelTurtle(InternalSlotRootAffix currentInternalElement) {
        //String type = ToCTeditor.turtleGen.getPartType(currentPart);
        JPanel currentPanel = new JPanel();
        currentPanel.setLayout(new BoxLayout(currentPanel, BoxLayout.Y_AXIS));
        currentPanel.setBackground(Color.lightGray);
        currentPanel.setMaximumSize(new Dimension(345, 250));
        JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setMaximumSize(new Dimension(345, 250));
        txtArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtArea.setFont(new Font("Sans", Font.PLAIN, 12));
        txtArea.setForeground(Color.blue);
        JScrollPane pnlScroll = new JScrollPane(txtArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlScroll.setMaximumSize(new Dimension(345, 250));
        pnlScroll.setBackground(Color.lightGray);

        String turtle;
        if (currentInternalElement != null) {
            turtle = ToCTeditor.turtleGen.getInternalElementTurtle(currentInternalElement);
        }
        else {
            turtle = ToCTeditor.turtleGen.getTemplateTurtle();
        }
        if (turtle.length() > 0 ){
            txtArea.setText(turtle);
            currentPanel.add(pnlScroll);
        }
        return currentPanel;
    }

    /**
     * Working Slot editor method
     * @param part
     * @return panel which consists of all the fields that represent a Slot
     */
    public JPanel setupSlotEditor(Slot part){
        /**
         * Slot Editor Panel
         */
        JPanel pnlSlotEditor = new JPanel();
        pnlSlotEditor.setLayout(new BoxLayout(pnlSlotEditor, BoxLayout.Y_AXIS));
        pnlSlotEditor.setMinimumSize(new Dimension(345,250));
        pnlSlotEditor.setMaximumSize(new Dimension(345,250));
        pnlSlotEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);

        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));

        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));

        if (part.getSerialisedName() != null){
            txtPartName.setText(part.getSerialisedName());
        }

        addChangeListener(txtPartName, e -> updateName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create label panel and add components to panel
         */
        JPanel pnlLabel = new JPanel();
        pnlLabel.setLayout(new BoxLayout(pnlLabel, BoxLayout.LINE_AXIS));
        pnlLabel.setMaximumSize(new Dimension(340,30));
        pnlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLabel.setBackground(Color.lightGray);

        JLabel lblLabel = new JLabel("Label:");
        lblLabel.setFont(new Font("Sans", Font.PLAIN, 14));
        lblLabel.setMaximumSize(new Dimension(100, 30));

        JTextField txtLabel = new JTextField();
        txtLabel.setFont(new Font("Sans", Font.PLAIN, 14));
        txtLabel.setMaximumSize(new Dimension(225, 30));

        String word = "";
        if (part.getValue().length() == 0 || part.getValue() == null){
            if (part.getLabel().length() != 0 || part.getLabel() != null ){
                word = part.getLabel();
            }
        }else{
            word = part.getValue();
        }
        txtLabel.setText(word);
        addChangeListener(txtLabel, e -> updateValue(part, txtLabel.getText()));

        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLabel.add(lblLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLabel.add(txtLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlNextPart = new JPanel();
        pnlNextPart.setLayout(new BoxLayout(pnlNextPart, BoxLayout.LINE_AXIS));
        pnlNextPart.setMaximumSize(new Dimension(340,30));
        pnlNextPart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlNextPart.setBackground(Color.lightGray);


        JLabel lblNextPart = new JLabel("Next Part:");
        lblNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        lblNextPart.setMaximumSize(new Dimension(100, 30));

        JTextField txtNextPart = new JTextField();
        txtNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        txtNextPart.setMaximumSize(new Dimension(225, 30));

        if (part.getNextWordPart() != null){
            txtNextPart.setText(part.getNextWordPart().getSerialisedName());
        }
        addChangeListener(txtNextPart, e -> updateNextPart(part, txtNextPart.getText()));

        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(lblNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(txtNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));

        /***
         * Add all panels to main panel - i.e. Slot editor panel
         */
        pnlSlotEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlSlotEditor.add(pnlPartName);
        pnlSlotEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlSlotEditor.add(pnlLabel);
        pnlSlotEditor.add(Box.createRigidArea(new Dimension(0,10)));
        //pnlSlotEditor.add(pnlNextPart);

        return pnlSlotEditor;
    }
    private void updateNextPart(TemplatePortion part, String updatedText) {
        if (ToCTeditor.dataModel.findTemplatePortion(updatedText) != null){
            part.setNextWordPart(ToCTeditor.dataModel.findTemplatePortion(updatedText));
        }
        /**else if ( ToCTeditor.dataModel.findTemplatePortion(updatedText) != null ){
         part.setNextPart(ToCTeditor.dataModel.findWordPortion(updatedText));
         }*/
        updateTurtlePanel(getPartPanelTurtle(part));
        updateItems();
    }
    private void updateValue(TemplatePortion part, String updatedText) {
        String type = ToCTeditor.turtleGen.getPartType(part);
        if (type.equals("Slot")){
            UnimorphicWord sf = new UnimorphicWord(updatedText);

            ((Slot)part).insertValue(sf);
        }
        else if ( type.equals("Unimorphic word")){
            ((UnimorphicWord)part).setValue(updatedText);
        }
        else if ( type.equals("Phrase")){
            ((Phrase)part).setValue(updatedText);
        }
        else if (type.equals("Punctuation")){
            ((Punctuation)part).setValue(updatedText);
        }

        updateTurtlePanel(getPartPanelTurtle(part));
        updateItems();
    }
    private void updateName(TemplatePortion part, String updatedText) {
        String fullName = "";
        Scanner sl = new Scanner(updatedText);
        while(sl.hasNext()) {
            fullName += sl.next();
        }
        part.setSerialisedName(fullName);
        updateTurtlePanel(getPartPanelTurtle(part));
        updateItems();
    }
    private void updateTemplateAnnotation(String updatedText) {
        ToCTeditor.dataModel.setTemplateAnnotation(updatedText);
    }

    private void updateComboVals(TemplatePortion part, String updatedText) {
        /**part.setSerialisedName(updatedText);
        updateTurtlePanel(getPartPanelTurtle(part));
        updateItems();*/
    }
    /**
     * Working Unimorphic word editor
     * @param part
     * @return
     */
    public JPanel setupUnimorphicWordEditor(UnimorphicWord part){
        /**
         * Unimorphic Word Editor Panel
         */
        JPanel pnlUnimorphicWordEditor = new JPanel();
        pnlUnimorphicWordEditor.setLayout(new BoxLayout(pnlUnimorphicWordEditor, BoxLayout.Y_AXIS));
        pnlUnimorphicWordEditor.setMaximumSize(new Dimension(345,250));
        pnlUnimorphicWordEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);


        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));

        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));

        if (part.getSerialisedName() != null) {
            txtPartName.setText(part.getSerialisedName());
        }
        addChangeListener(txtPartName, e -> updateName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create value panel and add components to panel
         */
        JPanel pnlValue = new JPanel();
        pnlValue.setLayout(new BoxLayout(pnlValue, BoxLayout.LINE_AXIS));
        pnlValue.setMaximumSize(new Dimension(340,30));
        pnlValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlValue.setBackground(Color.lightGray);

        JLabel lblValue = new JLabel("Value:");
        lblValue.setFont(new Font("Sans", Font.PLAIN, 14));
        lblValue.setMaximumSize(new Dimension(100, 30));

        JTextField txtValue = new JTextField();
        txtValue.setFont(new Font("Sans", Font.PLAIN, 14));
        txtValue.setMaximumSize(new Dimension(225, 30));

        if (part.getValue() != null) {
            txtValue.setText(part.getValue());
        }
        addChangeListener(txtValue, e -> updateValue(part, txtValue.getText()));

        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(lblValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(txtValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        /**
         * Create next part panel and add components to panel
         */
        JPanel pnlNextPart = new JPanel();
        pnlNextPart.setLayout(new BoxLayout(pnlNextPart, BoxLayout.LINE_AXIS));
        pnlNextPart.setMaximumSize(new Dimension(340,30));
        pnlNextPart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlNextPart.setBackground(Color.lightGray);


        JLabel lblNextPart = new JLabel("Next Part:");
        lblNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        lblNextPart.setMaximumSize(new Dimension(100, 30));

        JTextField txtNextPart = new JTextField();
        txtNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        txtNextPart.setMaximumSize(new Dimension(225, 30));

        if (part.getNextWordPart() != null) {
            txtNextPart.setText(part.getNextWordPart().getSerialisedName());
        }
        addChangeListener(txtNextPart, e -> updateNextPart(part, txtNextPart.getText()));

        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(lblNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(txtNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));

        /***
         * Add all panels to main panel - i.e. Unimorphic word editor panel
         */
        pnlUnimorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlUnimorphicWordEditor.add(pnlPartName);
        pnlUnimorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlUnimorphicWordEditor.add(pnlValue);
        pnlUnimorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        //pnlUnimorphicWordEditor.add(pnlNextPart);

        return pnlUnimorphicWordEditor;
    }

    /**
     * Working Punctuation editor method
     * @param part
     * @return
     */
    public JPanel setupPunctuationEditor(Punctuation part){
        /**
         * Punctuation Editor Panel
         */
        JPanel pnlPunctuationEditor = new JPanel();
        pnlPunctuationEditor.setLayout(new BoxLayout(pnlPunctuationEditor, BoxLayout.Y_AXIS));
        pnlPunctuationEditor.setMaximumSize(new Dimension(345,250));
        pnlPunctuationEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);


        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));

        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));

        txtPartName.setText(part.getSerialisedName());
        addChangeListener(txtPartName, e -> updateName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create value panel and add components to panel
         */
        JPanel pnlValue = new JPanel();
        pnlValue.setLayout(new BoxLayout(pnlValue, BoxLayout.LINE_AXIS));
        pnlValue.setMaximumSize(new Dimension(340,30));
        pnlValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlValue.setBackground(Color.lightGray);

        JLabel lblValue = new JLabel("Value:");
        lblValue.setFont(new Font("Sans", Font.PLAIN, 14));
        lblValue.setMaximumSize(new Dimension(100, 30));

        JTextField txtValue = new JTextField();
        txtValue.setFont(new Font("Sans", Font.PLAIN, 14));
        txtValue.setMaximumSize(new Dimension(225, 30));

        txtValue.setText(part.getValue());
        addChangeListener(txtValue, e -> updateValue(part, txtValue.getText()));

        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(lblValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(txtValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));

        /***
         * Add all panels to main panel - i.e. Punctuation editor panel
         */
        pnlPunctuationEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPunctuationEditor.add(pnlPartName);
        pnlPunctuationEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPunctuationEditor.add(pnlValue);

        return pnlPunctuationEditor;
    }
    public JPanel setupPhraseEditor(Phrase part){
        /**
         * Phrase Editor Panel
         */
        JPanel pnlPhraseEditor = new JPanel();
        pnlPhraseEditor.setLayout(new BoxLayout(pnlPhraseEditor, BoxLayout.Y_AXIS));
        pnlPhraseEditor.setMaximumSize(new Dimension(345,250));
        pnlPhraseEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);


        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));

        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));
        txtPartName.setText(part.getSerialisedName());
        /**
         * Add change lister to text field to update underlying template portion according to user input
         */
        addChangeListener(txtPartName, e -> updateName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create value panel and add components to panel
         */
        JPanel pnlValue = new JPanel();
        pnlValue.setLayout(new BoxLayout(pnlValue, BoxLayout.LINE_AXIS));
        pnlValue.setMaximumSize(new Dimension(340,30));
        pnlValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlValue.setBackground(Color.lightGray);

        JLabel lblValue = new JLabel("Value:");
        lblValue.setFont(new Font("Sans", Font.PLAIN, 14));
        lblValue.setMaximumSize(new Dimension(100, 30));

        JTextField txtValue = new JTextField();
        txtValue.setFont(new Font("Sans", Font.PLAIN, 14));
        txtValue.setMaximumSize(new Dimension(225, 30));
        txtValue.setText(part.getValue());
        /**
         * Add change lister to text field to update underlying template portion according to user input
         */
        addChangeListener(txtValue, e -> updateValue(part, txtValue.getText()));

        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(lblValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(txtValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        /**
         * Create next part panel and add components to panel
         */
        JPanel pnlNextPart = new JPanel();
        pnlNextPart.setLayout(new BoxLayout(pnlNextPart, BoxLayout.LINE_AXIS));
        pnlNextPart.setMaximumSize(new Dimension(340,30));
        pnlNextPart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlNextPart.setBackground(Color.lightGray);


        JLabel lblNextPart = new JLabel("Next Part:");
        lblNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        lblNextPart.setMaximumSize(new Dimension(100, 30));

        JTextField txtNextPart = new JTextField();
        txtNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        txtNextPart.setMaximumSize(new Dimension(225, 30));
        /**if (part.getNextPart() != null) {
            txtNextPart.setText(part.getNextPart().getSerialisedName());
        }*/

        /**
         * Add change lister to text field to update underlying template portion according to user input
         */
        addChangeListener(txtNextPart, e -> updateNextPart(part, txtNextPart.getText()));

        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(lblNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(txtNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));

        /***
         * Add all panels to main panel - i.e. Phrase editor panel
         */
        pnlPhraseEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPhraseEditor.add(pnlPartName);
        pnlPhraseEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPhraseEditor.add(pnlValue);
        pnlPhraseEditor.add(Box.createRigidArea(new Dimension(0,10)));
        //pnlPhraseEditor.add(pnlNextPart);

        return pnlPhraseEditor;
    }
    /**
     * Working Polymorphic word editor
     * @param part
     * @return
     */
    public JPanel setupPolymorphicWordEditor(PolymorphicWord part){
        /**
         * Polymorphic Word Editor Panel
         */
        JPanel pnlPolymorphicWordEditor = new JPanel();
        pnlPolymorphicWordEditor.setLayout(new BoxLayout(pnlPolymorphicWordEditor, BoxLayout.Y_AXIS));
        pnlPolymorphicWordEditor.setMaximumSize(new Dimension(345,250));
        pnlPolymorphicWordEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);


        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));

        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));
        if (part.getSerialisedName() != null) {
            txtPartName.setText(part.getSerialisedName());
        }

        addChangeListener(txtPartName, e -> updateName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create relies on panel and add components to panel
         */
        JPanel pnlReliesOn = new JPanel();
        pnlReliesOn.setLayout(new BoxLayout(pnlReliesOn, BoxLayout.LINE_AXIS));
        pnlReliesOn.setMaximumSize(new Dimension(340,30));
        pnlReliesOn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlReliesOn.setBackground(Color.lightGray);

        JLabel lblReliesOn = new JLabel("Relies on:");
        lblReliesOn.setFont(new Font("Sans", Font.PLAIN, 14));
        lblReliesOn.setMaximumSize(new Dimension(100, 30));

        List<String> reliesOnList = new ArrayList<>();
        if (part.getItemsItReliesOn() != null && part.getItemsItReliesOn().size() > 0) {
            reliesOnList = part.getItemsItReliesOn();
        }
        List<String> portionNames = new ArrayList<>();
        for (int i = 0; i < ToCTeditor.dataModel.getTemplatePortions().size(); i++) {
            if ( i != ToCTeditor.gui.getIndex())
                portionNames.add(ToCTeditor.dataModel.getTemplatePortions().get(i).getSerialisedName());
        }

        JComboBox txtReliesOn = new CheckCombo().getContent(portionNames, reliesOnList);
        txtReliesOn.setFont(new Font("Sans", Font.PLAIN, 14));
        txtReliesOn.setMaximumSize(new Dimension(225, 30));
        txtReliesOn.setAlignmentX(Component.CENTER_ALIGNMENT);
        //txtReliesOn.addActionListener(new ItemChangeListener(part));
        txtReliesOn.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            CheckComboStore store = (CheckComboStore)cb.getSelectedItem();
            CheckComboRenderer ccr = (CheckComboRenderer)cb.getRenderer();
            ccr.checkBox.setSelected((store.state = !store.state));
            List<String> newReliesOnList = new ArrayList<>();
            for (int i = 0; i < cb.getItemCount(); i++){
                if (((CheckComboStore)cb.getItemAt(i)).getState() == Boolean.TRUE){
                    newReliesOnList.add(((CheckComboStore)cb.getItemAt(i)).getId());
                }
            }
            part.setReliesOnLabels(newReliesOnList);
            updateTurtlePanel(getPartPanelTurtle(part));
            updateItems();
        });

        //txtReliesOn.addItemListener(new ItemChangeListener());


        pnlReliesOn.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlReliesOn.add(lblReliesOn);
        pnlReliesOn.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlReliesOn.add(txtReliesOn);
        pnlReliesOn.add(Box.createRigidArea(new Dimension(5, 0)));


        /**
         * Create first part panel and add components to panel
         */
        JPanel pnlFirstPart = new JPanel();
        pnlFirstPart.setLayout(new BoxLayout(pnlFirstPart, BoxLayout.LINE_AXIS));
        pnlFirstPart.setMaximumSize(new Dimension(340,30));
        pnlFirstPart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlFirstPart.setBackground(Color.lightGray);


        JLabel lblFirstPart = new JLabel("First Part:");
        lblFirstPart.setFont(new Font("Sans", Font.PLAIN, 14));
        lblFirstPart.setMaximumSize(new Dimension(100, 30));

        JTextField txtFirstPart = new JTextField();
        txtFirstPart.setEditable(false);
        txtFirstPart.setBackground(Color.lightGray);
        txtFirstPart.setFont(new Font("Sans", Font.PLAIN, 14));
        txtFirstPart.setMaximumSize(new Dimension(225, 30));

        if (part.getAllMorphemes().size() > 0) {
            txtFirstPart.setText(part.getFirstItem().getSerialisedName());
        }

        pnlFirstPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlFirstPart.add(lblFirstPart);
        pnlFirstPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlFirstPart.add(txtFirstPart);
        pnlFirstPart.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create last part panel and add components to panel
         */
        JPanel pnlLastPart = new JPanel();
        pnlLastPart.setLayout(new BoxLayout(pnlLastPart, BoxLayout.LINE_AXIS));
        pnlLastPart.setMaximumSize(new Dimension(340,30));
        pnlLastPart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLastPart.setBackground(Color.lightGray);


        JLabel lblLastPart = new JLabel("Last Part:");
        lblLastPart.setFont(new Font("Sans", Font.PLAIN, 14));
        lblLastPart.setMaximumSize(new Dimension(100, 30));

        JTextField txtLastPart = new JTextField();
        txtLastPart.setEditable(false);
        txtLastPart.setBackground(Color.lightGray);
        txtLastPart.setFont(new Font("Sans", Font.PLAIN, 14));
        txtLastPart.setMaximumSize(new Dimension(225, 30));

        if ( part.getAllMorphemes().size() > 0 ) {
            txtLastPart.setText(part.getLastItem().getSerialisedName());
        }
        pnlLastPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLastPart.add(lblLastPart);
        pnlLastPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLastPart.add(txtLastPart);
        pnlLastPart.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create has part panel and add components to panel
         */
        JPanel pnlHasPart = new JPanel();
        pnlHasPart.setLayout(new BoxLayout(pnlHasPart, BoxLayout.LINE_AXIS));
        pnlHasPart.setMaximumSize(new Dimension(340,30));
        pnlHasPart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlHasPart.setBackground(Color.lightGray);

        if (part.getAllMorphemes().size() > 2){
            JLabel lblHasParts = new JLabel("Has Part:");
            lblHasParts.setFont(new Font("Sans", Font.PLAIN, 14));
            lblHasParts.setMaximumSize(new Dimension(100, 30));

            JComboBox cbHasPart = new JComboBox();
            cbHasPart.setEditable(false);
            cbHasPart.setBackground(Color.lightGray);
            cbHasPart.setFont(new Font("Sans", Font.PLAIN, 14));
            cbHasPart.setMaximumSize(new Dimension(225, 30));

            for ( int i = 1; i < part.getAllMorphemes().size() -1; i++ ) {
                cbHasPart.addItem(part.getItemAt(i).getSerialisedName());
            }

            pnlHasPart.add(Box.createRigidArea(new Dimension(5,0)));
            pnlHasPart.add(lblHasParts);
            pnlHasPart.add(Box.createRigidArea(new Dimension(5,0)));
            pnlHasPart.add(cbHasPart);
            pnlHasPart.add(Box.createRigidArea(new Dimension(5,0)));
        }


        /**
         * Create next part panel and add components to panel
         */
        JPanel pnlNextPart = new JPanel();
        pnlNextPart.setLayout(new BoxLayout(pnlNextPart, BoxLayout.LINE_AXIS));
        pnlNextPart.setMaximumSize(new Dimension(340,30));
        pnlNextPart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlNextPart.setBackground(Color.lightGray);


        JLabel lblNextPart = new JLabel("Next Part:");
        lblNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        lblNextPart.setMaximumSize(new Dimension(100, 30));

        JTextField txtNextPart = new JTextField();
        txtNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        txtNextPart.setMaximumSize(new Dimension(225, 30));

        /**if (part.getNextPart() != null) {
            txtNextPart.setText(part.getNextPart().getSerialisedName());
        }*/

        addChangeListener(txtNextPart, e -> updateNextPart(part, txtNextPart.getText()));

        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(lblNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(txtNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create add morphemes panel and add button to panel
         */
        JPanel pnlAddMorpheme = new JPanel();
        pnlAddMorpheme.setLayout(new BoxLayout(pnlAddMorpheme, BoxLayout.LINE_AXIS));
        pnlAddMorpheme.setMaximumSize(new Dimension(340,30));
        pnlAddMorpheme.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlAddMorpheme.setBackground(Color.lightGray);

        /**
         * Add button
         */
        JButton btnAdd = new JButton("Add Word Portion");
        btnAdd.setFont(new Font("Sans", Font.PLAIN, 15));
        btnAdd.setMaximumSize(new Dimension(330,30));

        // add the listener to the jbutton to handle the "pressed" event
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                //ToCTeditor.gui = new ViewThread(ToCTeditor.homeScreen, ToCTeditor.templateItems, ToCTeditor.createItem, ToCTeditor.dataModel);
                int currIndex = ToCTeditor.gui.getIndex();
                ToCTeditor.gui = new ViewThread();
                ToCTeditor.gui.setIndex(currIndex);
                ToCTeditor.gui.setCallCreateMorpheme(true);

                ToCTeditor.gui.start();

            }
        });
        pnlAddMorpheme.add(Box.createRigidArea(new Dimension(5,0)));
        pnlAddMorpheme.add(btnAdd);

        /***
         * Add all panels to main panel - i.e. Unimorphic word editor panel
         */
        pnlPolymorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPolymorphicWordEditor.add(pnlPartName);
        pnlPolymorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPolymorphicWordEditor.add(pnlReliesOn);
        pnlPolymorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPolymorphicWordEditor.add(pnlFirstPart);
        pnlPolymorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPolymorphicWordEditor.add(pnlLastPart);
        /**if (part.getAllMorphemes().size() > 2) {
            pnlPolymorphicWordEditor.add(Box.createRigidArea(new Dimension(0, 10)));
            pnlPolymorphicWordEditor.add(pnlHasPart);
        }*/
        pnlPolymorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        //pnlPolymorphicWordEditor.add(pnlNextPart);
        pnlPolymorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPolymorphicWordEditor.add(pnlAddMorpheme);

        return pnlPolymorphicWordEditor;
    }

    /************************************************************************************************************/
    // Word portions
    /************************************************************************************************************/
    public JPanel setupConcordEditor(Concord part){
        /**
         * Concord Editor Panel
         */
        JPanel pnlConcordEditor = new JPanel();
        pnlConcordEditor.setLayout(new BoxLayout(pnlConcordEditor, BoxLayout.Y_AXIS));
        pnlConcordEditor.setMaximumSize(new Dimension(345,250));
        pnlConcordEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);


        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));


        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));
        txtPartName.setText(part.getSerialisedName());
        addChangeListener(txtPartName, e -> updateInternalElementName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create label panel and add components to panel
         */
        JPanel pnlLabel = new JPanel();
        pnlLabel.setLayout(new BoxLayout(pnlLabel, BoxLayout.LINE_AXIS));
        pnlLabel.setMaximumSize(new Dimension(340,30));
        pnlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLabel.setBackground(Color.lightGray);

        JLabel lblLabel = new JLabel("Label:");
        lblLabel.setFont(new Font("Sans", Font.PLAIN, 14));
        lblLabel.setMaximumSize(new Dimension(100, 30));

        JTextField txtLabel = new JTextField();
        txtLabel.setFont(new Font("Sans", Font.PLAIN, 14));
        txtLabel.setMaximumSize(new Dimension(225, 30));
        txtLabel.setText(part.getLabel());
        addChangeListener(txtLabel, e -> updateInternalElementValue(part, txtLabel.getText()));

        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLabel.add(lblLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLabel.add(txtLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create add features panel and add button to panel
         */
        JPanel pnlFeatureList = new JPanel();
        pnlFeatureList.setLayout(new BoxLayout(pnlFeatureList, BoxLayout.LINE_AXIS));
        pnlFeatureList.setMaximumSize(new Dimension(340,30));
        pnlFeatureList.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlFeatureList.setBackground(Color.lightGray);

        /**
         * drop down
         */
        JComboBox cbFeatureList = new JComboBox();
        cbFeatureList.setFont(new Font("Sans", Font.PLAIN, 15));
        cbFeatureList.setMaximumSize(new Dimension(330,30));

        cbFeatureList.addItem("Remove Feature List");
        cbFeatureList.addItem("Add New Feature");

        //@TODO Concord type must have a get feature list method to pupulate combo box
        /**for ( int i = 1; i < part.getAllMorphemes().size() -1; i++ ) {
            cbHasPart.addItem(part.getItemAt(i).getSerialisedName());
        }*/

        /**cbFeatureList.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            String selected = (CheckComboStore)cb.getSelectedItem();
            CheckComboRenderer ccr = (CheckComboRenderer)cb.getRenderer();
            ccr.checkBox.setSelected((store.state = !store.state));
            List<String> newReliesOnList = new ArrayList<>();
            for (int i = 0; i < cb.getItemCount(); i++){
                if (((CheckComboStore)cb.getItemAt(i)).getState() == Boolean.TRUE){
                    newReliesOnList.add(((CheckComboStore)cb.getItemAt(i)).getId());
                }
            }
            part.setReliesOnLabels(newReliesOnList);
            updateTurtlePanel(getPartPanelTurtle(part));
            updateItems();
        });*/
        // add the listener to the jbutton to handle the "pressed" event

        pnlFeatureList.add(Box.createRigidArea(new Dimension(5,0)));
        pnlFeatureList.add(cbFeatureList);

        /**
         * Create concord type panel and add components to panel
         */
        JPanel pnlType = new JPanel();
        pnlType.setLayout(new BoxLayout(pnlType, BoxLayout.LINE_AXIS));
        pnlType.setMaximumSize(new Dimension(340,30));
        pnlType.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlType.setBackground(Color.lightGray);


        JLabel lblType = new JLabel("Concord type:");
        lblType.setFont(new Font("Sans", Font.PLAIN, 14));
        lblType.setMaximumSize(new Dimension(100, 30));

        JTextField txtType = new JTextField();
        txtType.setFont(new Font("Sans", Font.PLAIN, 14));
        txtType.setMaximumSize(new Dimension(225, 30));
        if (part.getConcordType() != null)
            txtType.setText(part.getConcordType().getTypeString());
        else
            txtType.setText("");
        txtType.setEditable(false);
        //addChangeListener(txtType, e -> updateConcordType(part, txtType.getText()));

        pnlType.add(Box.createRigidArea(new Dimension(5,0)));
        pnlType.add(lblType);
        pnlType.add(Box.createRigidArea(new Dimension(5,0)));
        pnlType.add(txtType);
        pnlType.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create next part name panel and add components to panel
         */
        JPanel pnlNextPart = new JPanel();
        pnlNextPart.setLayout(new BoxLayout(pnlNextPart, BoxLayout.LINE_AXIS));
        pnlNextPart.setMaximumSize(new Dimension(340,30));
        pnlNextPart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlNextPart.setBackground(Color.lightGray);


        JLabel lblNextPart = new JLabel("Next Part:");
        lblNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        lblNextPart.setMaximumSize(new Dimension(100, 30));



        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(lblNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));

        /***
         * Add all panels to main panel - i.e. Slot editor panel
         */
        pnlConcordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlConcordEditor.add(pnlPartName);
        pnlConcordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlConcordEditor.add(pnlLabel);
        pnlConcordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlConcordEditor.add(pnlType);
        pnlConcordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        //pnlConcordEditor.add(pnlFeatureList);
        //pnlConcordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        //pnlConcordEditor.add(pnlNextPart);

        return pnlConcordEditor;
    }

    public JPanel setupFeatureEditor(Concord part) {
        /**
         * Feature Editor Panel
         */
        JPanel pnlFeatureEditor = new JPanel();
        pnlFeatureEditor.setLayout(new BoxLayout(pnlFeatureEditor, BoxLayout.Y_AXIS));
        pnlFeatureEditor.setMaximumSize(new Dimension(345, 250));
        pnlFeatureEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlFeatureName = new JPanel();
        pnlFeatureName.setLayout(new BoxLayout(pnlFeatureName, BoxLayout.LINE_AXIS));
        pnlFeatureName.setMaximumSize(new Dimension(340, 30));
        pnlFeatureName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlFeatureName.setBackground(Color.lightGray);


        JLabel lblFeatureName = new JLabel("Feature name:");
        lblFeatureName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblFeatureName.setMaximumSize(new Dimension(100, 30));


        JTextField txtFeatureName = new JTextField();
        txtFeatureName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtFeatureName.setMaximumSize(new Dimension(225, 30));
        txtFeatureName.setText(part.getSerialisedName());
        addChangeListener(txtFeatureName, e -> updateInternalElementName(part, txtFeatureName.getText()));

        pnlFeatureName.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlFeatureName.add(lblFeatureName);
        pnlFeatureName.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlFeatureName.add(txtFeatureName);
        pnlFeatureName.add(Box.createRigidArea(new Dimension(5, 0)));

        /**
         * Create label panel and add components to panel
         */
        JPanel pnlLabel = new JPanel();
        pnlLabel.setLayout(new BoxLayout(pnlLabel, BoxLayout.LINE_AXIS));
        pnlLabel.setMaximumSize(new Dimension(340, 30));
        pnlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLabel.setBackground(Color.lightGray);

        JLabel lblLabel = new JLabel("Type:");
        lblLabel.setFont(new Font("Sans", Font.PLAIN, 14));
        lblLabel.setMaximumSize(new Dimension(100, 30));

        JTextField txtLabel = new JTextField();
        txtLabel.setFont(new Font("Sans", Font.PLAIN, 14));
        txtLabel.setMaximumSize(new Dimension(225, 30));
        txtLabel.setText(part.getLabel());
        addChangeListener(txtLabel, e -> updateInternalElementValue(part, txtLabel.getText()));

        pnlLabel.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlLabel.add(lblLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5, 0)));
        pnlLabel.add(txtLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5, 0)));

        /***
         * Add all panels to main panel - i.e. Slot editor panel
         */
        pnlFeatureEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlFeatureEditor.add(pnlFeatureName);
        pnlFeatureEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlFeatureEditor.add(pnlLabel);
        pnlFeatureEditor.add(Box.createRigidArea(new Dimension(0,10)));

        return pnlFeatureEditor;
    }
    public JPanel setupCopulaEditor(Copula part){
        /**
         * Copula Editor Panel
         */
        JPanel pnlCopulaEditor = new JPanel();
        pnlCopulaEditor.setLayout(new BoxLayout(pnlCopulaEditor, BoxLayout.Y_AXIS));
        pnlCopulaEditor.setMaximumSize(new Dimension(345,250));
        pnlCopulaEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);


        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));

        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));
        txtPartName.setText(part.getSerialisedName());
        addChangeListener(txtPartName, e -> updateInternalElementName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create label panel and add components to panel
         */
        JPanel pnlLabel = new JPanel();
        pnlLabel.setLayout(new BoxLayout(pnlLabel, BoxLayout.LINE_AXIS));
        pnlLabel.setMaximumSize(new Dimension(340,30));
        pnlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLabel.setBackground(Color.lightGray);

        JLabel lblLabel = new JLabel("Label:");
        lblLabel.setFont(new Font("Sans", Font.PLAIN, 14));
        lblLabel.setMaximumSize(new Dimension(100, 30));

        JTextField txtLabel = new JTextField();
        txtLabel.setFont(new Font("Sans", Font.PLAIN, 14));
        txtLabel.setMaximumSize(new Dimension(225, 30));
        txtLabel.setText(part.getValue());
        addChangeListener(txtLabel, e -> updateInternalElementValue(part, txtLabel.getText()));

        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLabel.add(lblLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLabel.add(txtLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlNextPart = new JPanel();
        pnlNextPart.setLayout(new BoxLayout(pnlNextPart, BoxLayout.LINE_AXIS));
        pnlNextPart.setMaximumSize(new Dimension(340,30));
        pnlNextPart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlNextPart.setBackground(Color.lightGray);


        JLabel lblNextPart = new JLabel("Next Part:");
        lblNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        lblNextPart.setMaximumSize(new Dimension(100, 30));

        /**JTextField txtNextPart = new JTextField();
         txtNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
         txtNextPart.setMaximumSize(new Dimension(225, 30));
         txtNextPart.setText(part.getNextPart());

         pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
         pnlNextPart.add(lblNextPart);
         pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
         pnlNextPart.add(txtNextPart);
         pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));*/

        /***
         * Add all panels to main panel - i.e. Slot editor panel
         */
        pnlCopulaEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlCopulaEditor.add(pnlPartName);
        pnlCopulaEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlCopulaEditor.add(pnlLabel);
        pnlCopulaEditor.add(Box.createRigidArea(new Dimension(0,10)));
        //pnlCopulaEditor.add(pnlNextPart);

        return pnlCopulaEditor;
    }
    public JPanel setupRootEditor(Root part){
        /**
         * Root Editor Panel
         */
        JPanel pnlRootEditor = new JPanel();
        pnlRootEditor.setLayout(new BoxLayout(pnlRootEditor, BoxLayout.Y_AXIS));
        pnlRootEditor.setMaximumSize(new Dimension(345,250));
        pnlRootEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);


        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));

        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));
        txtPartName.setText(part.getSerialisedName());
        addChangeListener(txtPartName, e -> updateInternalElementName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create value panel and add components to panel
         */
        JPanel pnlValue = new JPanel();
        pnlValue.setLayout(new BoxLayout(pnlValue, BoxLayout.LINE_AXIS));
        pnlValue.setMaximumSize(new Dimension(340,30));
        pnlValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlValue.setBackground(Color.lightGray);

        JLabel lblValue = new JLabel("Value:");
        lblValue.setFont(new Font("Sans", Font.PLAIN, 14));
        lblValue.setMaximumSize(new Dimension(100, 30));

        JTextField txtValue = new JTextField();
        txtValue.setFont(new Font("Sans", Font.PLAIN, 14));
        txtValue.setMaximumSize(new Dimension(225, 30));
        txtValue.setText(part.getValue());
        addChangeListener(txtValue, e -> updateInternalElementValue(part, txtValue.getText()));

        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(lblValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(txtValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));

        /***
         * Add all panels to main panel - i.e. Root editor panel
         */
        pnlRootEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlRootEditor.add(pnlPartName);
        pnlRootEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlRootEditor.add(pnlValue);

        return pnlRootEditor;
    }

    public JPanel setupUnimorphicAffixEditor(UnimorphicAffix part){
        /**
         * Unimorphic Affix Editor Panel
         */
        JPanel pnlUnimorphicAffixEditor = new JPanel();
        pnlUnimorphicAffixEditor.setLayout(new BoxLayout(pnlUnimorphicAffixEditor, BoxLayout.Y_AXIS));
        pnlUnimorphicAffixEditor.setMaximumSize(new Dimension(345,250));
        pnlUnimorphicAffixEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);


        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));

        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));
        txtPartName.setText(part.getSerialisedName());
        addChangeListener(txtPartName, e -> updateInternalElementName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create value panel and add components to panel
         */
        JPanel pnlValue = new JPanel();
        pnlValue.setLayout(new BoxLayout(pnlValue, BoxLayout.LINE_AXIS));
        pnlValue.setMaximumSize(new Dimension(340,30));
        pnlValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlValue.setBackground(Color.lightGray);

        JLabel lblValue = new JLabel("Value:");
        lblValue.setFont(new Font("Sans", Font.PLAIN, 14));
        lblValue.setMaximumSize(new Dimension(100, 30));

        JTextField txtValue = new JTextField();
        txtValue.setFont(new Font("Sans", Font.PLAIN, 14));
        txtValue.setMaximumSize(new Dimension(225, 30));
        txtValue.setText(part.getValue());
        addChangeListener(txtValue, e -> updateInternalElementValue(part, txtValue.getText()));

        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(lblValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(txtValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));

        /***
         * Add all panels to main panel - i.e. Unimorphic Affix editor panel
         */
        pnlUnimorphicAffixEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlUnimorphicAffixEditor.add(pnlPartName);
        pnlUnimorphicAffixEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlUnimorphicAffixEditor.add(pnlValue);

        return pnlUnimorphicAffixEditor;
    }

    public JPanel setupLocativeEditor(Locative part){
        /**
         * Locative Editor Panel
         */
        JPanel pnlLocativeEditor = new JPanel();
        pnlLocativeEditor.setLayout(new BoxLayout(pnlLocativeEditor, BoxLayout.Y_AXIS));
        pnlLocativeEditor.setMaximumSize(new Dimension(345,250));
        pnlLocativeEditor.setBackground(Color.lightGray);

        /**
         * Create part name panel and add components to panel
         */
        JPanel pnlPartName = new JPanel();
        pnlPartName.setLayout(new BoxLayout(pnlPartName, BoxLayout.LINE_AXIS));
        pnlPartName.setMaximumSize(new Dimension(340,30));
        pnlPartName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPartName.setBackground(Color.lightGray);


        JLabel lblPartName = new JLabel("Part name:");
        lblPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        lblPartName.setMaximumSize(new Dimension(100, 30));

        JTextField txtPartName = new JTextField();
        txtPartName.setFont(new Font("Sans", Font.PLAIN, 14));
        txtPartName.setMaximumSize(new Dimension(225, 30));
        txtPartName.setText(part.getSerialisedName());
        addChangeListener(txtPartName, e -> updateInternalElementName(part, txtPartName.getText()));

        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(lblPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));
        pnlPartName.add(txtPartName);
        pnlPartName.add(Box.createRigidArea(new Dimension(5,0)));

        /**
         * Create value panel and add components to panel
         */
        JPanel pnlValue = new JPanel();
        pnlValue.setLayout(new BoxLayout(pnlValue, BoxLayout.LINE_AXIS));
        pnlValue.setMaximumSize(new Dimension(340,30));
        pnlValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlValue.setBackground(Color.lightGray);

        JLabel lblValue = new JLabel("Value:");
        lblValue.setFont(new Font("Sans", Font.PLAIN, 14));
        lblValue.setMaximumSize(new Dimension(100, 30));

        JTextField txtValue = new JTextField();
        txtValue.setFont(new Font("Sans", Font.PLAIN, 14));
        txtValue.setMaximumSize(new Dimension(225, 30));
        txtValue.setText(part.getValue());
        addChangeListener(txtValue, e -> updateInternalElementValue(part, txtValue.getText()));

        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(lblValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));
        pnlValue.add(txtValue);
        pnlValue.add(Box.createRigidArea(new Dimension(5,0)));

        /***
         * Add all panels to main panel - i.e. Unimorphic Affix editor panel
         */
        pnlLocativeEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlLocativeEditor.add(pnlPartName);
        pnlLocativeEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlLocativeEditor.add(pnlValue);

        return pnlLocativeEditor;
    }

    private void updateInternalElementName(InternalSlotRootAffix internalElement, String updatedText) {
        String fullName = "";
        Scanner sl = new Scanner(updatedText);
        while(sl.hasNext()) {
            fullName += sl.next();
        }
        internalElement.setSerialisedName(fullName);
        updateTurtlePanel(getInternalElementPanelTurtle(internalElement));
        updateItems();
    }

    private void updateInternalElementValue(InternalSlotRootAffix part, String updatedText) {
        if (part.getType().equals("Slot")){
            UnimorphicWord sf = new UnimorphicWord(updatedText);

            ((Slot)part).insertValue(sf);
        }
        else if ( part.getType().equals("Concord")){
            ((Concord)part).setLabel(updatedText);
        }
        else if ( part.getType().equals("Copula")){
            ((Copula)part).setValue(updatedText);
        }
        else if (part.getType().equals("Locative")){
            ((Locative)part).setValue(updatedText);
        }
        else if (part.getType().equals("Root")){
            ((Root)part).setValue(updatedText);
        }
        else if (part.getType().equals("UnimorphicAffix")){
            ((UnimorphicAffix)part).setValue(updatedText);
        }

        updateTurtlePanel(getInternalElementPanelTurtle(part));
        updateItems();
    }

    private void updateConcordType(Concord concord, String updatedText) {
        concord.addFeature(new ConcordType(updatedText));
        updateTurtlePanel(getInternalElementPanelTurtle(concord));
        updateItems();
    }

    /**
     * Installs a listener to receive notification when the text of any
     * {@code JTextComponent} is changed. Internally, it installs a
     * {@link DocumentListener} on the text component's {@link Document},
     * and a {@link PropertyChangeListener} on the text component to detect
     * if the {@code Document} itself is replaced.
     *
     * @param text any text component, such as a {@link JTextField}
     *        or {@link JTextArea}
     * @param changeListener a listener to receieve {@link ChangeEvent}s
     *        when the text is changed; the source object for the events
     *        will be the text component
     * @throws NullPointerException if either parameter is null
     */
    public static void addChangeListener(JTextComponent text, ChangeListener changeListener) {
        Objects.requireNonNull(text);
        Objects.requireNonNull(changeListener);
        DocumentListener dl = new DocumentListener() {
            private int lastChange = 0, lastNotifiedChange = 0;

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lastChange++;
                SwingUtilities.invokeLater(() -> {
                    if (lastNotifiedChange != lastChange) {
                        lastNotifiedChange = lastChange;
                        changeListener.stateChanged(new ChangeEvent(text));
                    }
                });
            }
        };
        text.addPropertyChangeListener("document", (PropertyChangeEvent e) -> {
            Document d1 = (Document)e.getOldValue();
            Document d2 = (Document)e.getNewValue();
            if (d1 != null) d1.removeDocumentListener(dl);
            if (d2 != null) d2.addDocumentListener(dl);
            dl.changedUpdate(null);
        });
        Document d = text.getDocument();
        if (d != null) d.addDocumentListener(dl);
    }

}