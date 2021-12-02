/*
 * @(#) TemplateItems.java   1.0   Nov 12, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

import za.co.mahlaza.research.grammarengine.base.models.template.Phrase;
import za.co.mahlaza.research.grammarengine.base.models.template.PolymorphicWord;
import za.co.mahlaza.research.grammarengine.base.models.template.Punctuation;
import za.co.mahlaza.research.grammarengine.base.models.template.Slot;
import za.co.mahlaza.research.grammarengine.base.models.template.TemplatePortion;
import za.co.mahlaza.research.grammarengine.base.models.template.UnimorphicWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;


public class TemplateItems {
    private int frameX;
    private int frameY;
    private ToCTeditorFrame frame;

    JPanel pnlEditorPreview;
    JPanel pnlItemEditor;
    JPanel pnlTurtlePreview;
    JPanel pnlEditorTurtleTitle;



    //private ControlThread controlThread;

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

        //ArrayList<TemplatePart> templateItems = new ArrayList<>();

        /**templateItems.add(new Slot("slot1", "s1", "concord1"));
        templateItems.add(new Concord("concord1", "c1", "subjectVerb", "root1"));
        templateItems.add(new Root("root1", "nke"));*/

        //pnlItems.add(setupTemplateItems(ToCTeditor.dataModel.getData()));
        pnlItems.add(setupTemplateItems(ToCTeditor.dataModel.getTemplateWords()));

        /**
         * Panel for turtle syntax toggle button
         */
        JPanel pnlToggleButton = new JPanel();
        pnlToggleButton.setLayout(new BoxLayout(pnlToggleButton, BoxLayout.LINE_AXIS));
        pnlToggleButton.setMaximumSize(new Dimension(700,30));
        pnlToggleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlToggleButton.setBackground(Color.lightGray);


        JToggleButton tbtnTurtle = new JToggleButton("Show turtle preview");
        tbtnTurtle.setFont(new Font("Sans", Font.PLAIN, 14));
        tbtnTurtle.setMaximumSize(new Dimension(343,30));
        //tbtnTurtle.setSelected(true);




        /**
         * Add label and toggle button panel
         */
        tbtnTurtle.setSelected(ToCTeditor.gui.getToggleBtnState() == 1);
        pnlToggleButton.add(tbtnTurtle);
        /**pnlToggleButton.add(Box.createRigidArea(new Dimension(10,0)));
        pnlToggleButton.add(btnSaveTemplate);*/

        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    ToCTeditor.gui.setToggleBtnState(state);
                    //System.out.println("Selected: " + state);
                } else {
                    ToCTeditor.gui.setToggleBtnState(state);
                    //System.out.println("Deselected: " + state);
                }
            }
        };

        tbtnTurtle.addItemListener(itemListener);

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
        pnlEditorPreview.setMaximumSize(new Dimension(700,250));
        pnlEditorPreview.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlEditorPreview.setBackground(Color.lightGray);

        setupEditorTurtlePanel(currentPartProperties, turtlePreview);

        /**
         * Back button
         */
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Sans", Font.PLAIN, 15));
        btnBack.setMaximumSize(new Dimension(226,30));

        // add the listener to the jbutton to handle the "pressed" event
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ToCTeditor.gui.setCallCreateTemplate(true);
            }
        });

        /**
         * Add button
         */
        JButton btnAdd = new JButton("Add Template Item");
        btnAdd.setFont(new Font("Sans", Font.PLAIN, 15));
        btnAdd.setMaximumSize(new Dimension(226,30));

        // add the listener to the jbutton to handle the "pressed" event
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //[snip]
                ToCTeditor.gui.setCallCreateItem(true);
            }
        });

        /**
         * Save template button
         */
        JButton btnSaveTemplate = new JButton("Save Template");
        btnSaveTemplate.setFont(new Font("Sans", Font.PLAIN, 15));
        btnSaveTemplate.setMaximumSize(new Dimension(226,30));

        // add the listener to the jbutton to handle the "pressed" event
        /**btnSaveTemplate.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
         ToCTeditor.gui.setCallCreateTemplate(true);
         }
         });*/

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
        pnlButtons.add(Box.createRigidArea(new Dimension(11,0)));
        pnlButtons.add(btnAdd);
        pnlButtons.add(Box.createRigidArea(new Dimension(11,0)));
        pnlButtons.add(btnSaveTemplate);




        // Add heading label to list panel
        listPane.add(pnlItemsTitle);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        // Add template name text field to list panel
        listPane.add(pnlItems);
        listPane.add(Box.createRigidArea(new Dimension(0,10)));
        listPane.add(pnlToggleButton);
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
    }

    public void setupEditorTurtlePanel(JPanel currentPartProperties, JPanel turtlePreview){
        JLabel lblEditorTitle = new JLabel("Item property editor");
        lblEditorTitle.setFont(new Font("Sans", Font.PLAIN, 15));
        lblEditorTitle.setMaximumSize(new Dimension(345,15));

        JLabel lblTurtleTitle = new JLabel("Item turtle preview");
        lblTurtleTitle.setFont(new Font("Sans", Font.PLAIN, 15));
        lblTurtleTitle.setMaximumSize(new Dimension(345,15));


        /**
         * Editor Panel
         */
        pnlItemEditor = new JPanel();
        pnlItemEditor.setLayout(new BoxLayout(pnlItemEditor, BoxLayout.Y_AXIS));

        pnlItemEditor.setBackground(Color.lightGray);
        pnlItemEditor.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        pnlItemEditor.add(currentPartProperties);

        /**
         * Turtle Preview Panel
         */
        pnlTurtlePreview = new JPanel();
        pnlTurtlePreview.setLayout(new BoxLayout(pnlTurtlePreview, BoxLayout.Y_AXIS));
        pnlTurtlePreview.setMaximumSize(new Dimension(345,250));
        pnlTurtlePreview.setBackground(Color.lightGray);
        pnlTurtlePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        pnlTurtlePreview.add(turtlePreview);

        if (ToCTeditor.gui.getToggleBtnState() == 1){

            /**
             * Add title labels to labels panel
             */
            pnlEditorTurtleTitle.add(lblEditorTitle);
            pnlEditorTurtleTitle.add(Box.createRigidArea(new Dimension(10,0)));
            pnlEditorTurtleTitle.add(lblTurtleTitle);

            pnlItemEditor.setMaximumSize(new Dimension(345,250));

            /**
             * Add panels to editor preview panel
             */
            pnlEditorPreview.add(pnlItemEditor);
            pnlEditorPreview.add(Box.createRigidArea(new Dimension(10,0)));
            pnlEditorPreview.add(pnlTurtlePreview);
        }
        else{
            /**
             * Add title labels to labels panel
             */
            pnlEditorTurtleTitle.add(lblEditorTitle);
            pnlEditorTurtleTitle.add(Box.createRigidArea(new Dimension(10,0)));

            pnlItemEditor.setMaximumSize(new Dimension(700,250));
            pnlEditorPreview.add(pnlItemEditor);
        }

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
        if (ToCTeditor.gui.getToggleBtnState() == 1) {
            pnlTurtlePreview.remove(0);
            this.pnlTurtlePreview.add(currentPartProperties);
            this.pnlTurtlePreview.getParent().revalidate();
        }
    }


    /**public JComponent setupTemplateItems(List<TemplatePart> list) {
        Box box = Box.createHorizontalBox();
        JScrollPane pnlScroll = new JScrollPane(box, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlScroll.setMaximumSize(new Dimension(700, 100));
        pnlScroll.setBackground(Color.lightGray);

        DragMouseAdapter dh = new DragMouseAdapter();
        box.addMouseListener(dh);
        box.addMouseMotionListener(dh);

        for (TemplatePart part : list) {
            JLabel name = new JLabel(part.getPartName());
            JLabel type = new JLabel(getPartType(part));
            box.add(createItemComponent(name, type));
        }
        JPanel p = new JPanel();
        p.setMaximumSize(new Dimension(700, 100));
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        p.setBackground(Color.lightGray);
        p.add(pnlScroll);
        return p;
    }*/

    public JComponent setupTemplateItems(List<TemplatePortion> list) {
        Box box = Box.createHorizontalBox();
        JScrollPane pnlScroll = new JScrollPane(box, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlScroll.setMaximumSize(new Dimension(700, 100));
        pnlScroll.setBackground(Color.lightGray);

        DragMouseAdapter dh = new DragMouseAdapter(/**controlThread*/);
        box.addMouseListener(dh);
        box.addMouseMotionListener(dh);

        for (TemplatePortion part : list) {
            JLabel name = new JLabel(ToCTeditor.turtleGen.getPartId(part));
            JLabel type = new JLabel(ToCTeditor.turtleGen.getPartType(part));
            box.add(createItemComponent(name, type));
        }
        JPanel p = new JPanel();
        p.setMaximumSize(new Dimension(700, 100));
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        p.setBackground(Color.lightGray);
        p.add(pnlScroll);
        return p;
    }

    private JComponent createItemComponent(JComponent name, JComponent type) {

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.white);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLUE, 2)));

        /**
         * Set up panel for menu button, and then add menu button to the panel
         */
        JPanel pnlKebab = new JPanel();
        pnlKebab.setLayout(new BoxLayout(pnlKebab, BoxLayout.LINE_AXIS));
        pnlKebab.setMaximumSize(new Dimension(100,20));
        pnlKebab.setBackground(Color.white);

        JPopupMenu menu = new JPopupMenu();
        menu.setMaximumSize(new Dimension(200, 100));
        menu.add(new JMenuItem("Duplicate"));
        menu.add(new JMenuItem("Change type"));
        menu.add(new JMenuItem("Remove"));

        final JButton button = new JButton();
        /**
         * Images folder is stored in the bin folder.
         * Reason why found here (last answer is the only one that works with a Makefile):
         * https://stackoverflow.com/questions/13151979/null-pointer-exception-when-an-imageicon-is-added-to-jbutton-in-netbeans
         */
        button.setIcon(createImageIcon("/images/kebab.png"));
        button.setBorderPainted(false);
        button.setBackground(Color.white);
        button.setMaximumSize(new Dimension(15,15));
        button.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ev){
                menu.show(button, 0/**button.getWidth()*/, button.getHeight());
            }
        });

        pnlKebab.add(Box.createRigidArea(new Dimension(30,0)));
        pnlKebab.add(button);
        /**
         * Set up panel for the item name, and then add a label to the panel
         */
        name.setFont(new Font("Sans", Font.PLAIN, 14));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel pnlName = new JPanel();
        pnlName.setLayout(new BoxLayout(pnlName, BoxLayout.Y_AXIS));
        pnlName.setMaximumSize(new Dimension(100,50));
        pnlName.add(name);
        pnlName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlName.setBackground(Color.white);

        /**
         * Set up panel for the item type, and then add a label to the panel
         */
        type.setFont(new Font("Sans", Font.PLAIN, 8));
        type.setMaximumSize(new Dimension(40,50));

        JPanel pnlType = new JPanel();
        pnlType.setLayout(new BoxLayout(pnlType, BoxLayout.LINE_AXIS));
        pnlType.setMaximumSize(new Dimension(100,30));
        pnlType.add(type);
        pnlType.add(Box.createRigidArea(new Dimension(60,0)));
        pnlType.setBackground(Color.white);


        p.setMaximumSize(new Dimension(100, 90));
        p.add(pnlKebab);
        p.add(pnlName);
        p.add(pnlType);
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

    /**public JPanel getPartPanelEditor(TemplatePart currentPart) {
        
        String type = getPartType(currentPart);
        JPanel currentPanel;
        //System.out.println("Inside getPanelEditor. Current part type: " + type);
        if (type.equals("Slot")){
            Slot part = (Slot)currentPart;
            currentPanel = setupSlotEditor(part);
        }
        else if (type.equals("Unimorphic word")){
            UnimorphicWord part = (UnimorphicWord)currentPart;
            currentPanel = setupUnimorphicWordEditor(part);
        }
        else if (type.equals("Punctuation")){
            Punctuation part = (Punctuation)currentPart;
            currentPanel = setupPunctuationEditor(part);
        }
        else if (type.equals("Root")){
            Root part = (Root)currentPart;
            currentPanel = setupRootEditor(part);
        }
        else if (type.equals("Polymorphic word")){
            PolymorphicWord part = (PolymorphicWord)currentPart;
            currentPanel = setupPolymorphicWordEditor(part);
        }
        else if ( type.equals("Concord") ){
            Concord part = (Concord)currentPart;
            currentPanel = setupConcordEditor(part);
        }
        else if ( type.equals("Phrase") ){
            Phrase part = (Phrase)currentPart;
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
        else{
            //System.out.println("No current item selected");
            currentPanel = new JPanel();
            currentPanel.setBackground(Color.lightGray);
        }
        return currentPanel;
    }*/

    public JPanel getPartPanelEditor(TemplatePortion currentPart) {

        String type = ToCTeditor.turtleGen.getPartType(currentPart);
        JPanel currentPanel;
        //System.out.println("Inside getPanelEditor. Current part type: " + type);
        if (type.equals("Slot")){
            Slot part = (Slot)currentPart;
            currentPanel = setupSlotEditor(part);
        }
        else if (type.equals("Unimorphic word")){
            UnimorphicWord part = (UnimorphicWord)currentPart;
            currentPanel = setupUnimorphicWordEditor(part);
        }
        else if (type.equals("Punctuation")){
            Punctuation part = (Punctuation)currentPart;
            currentPanel = setupPunctuationEditor(part);
        }
        else if (type.equals("Polymorphic word")){
            PolymorphicWord part = (PolymorphicWord)currentPart;
            currentPanel = setupPolymorphicWordEditor(part);
        }
        else if ( type.equals("Phrase") ){
            Phrase part = (Phrase)currentPart;
            currentPanel = setupPhraseEditor(part);
        }
        else{
            //System.out.println("No current item selected");
            currentPanel = new JPanel();
            currentPanel.setBackground(Color.lightGray);
        }
        return currentPanel;
    }

    /**public JPanel getPartPanelTurtle(TemplatePart currentPart) {
        String type = getPartType(currentPart);
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
        if (type.equals("Slot")){
            Slot part = (Slot)currentPart;
            txtArea.setText(part.getTurtle());
            currentPanel.add(txtArea);
        }
        else if (type.equals("Unimorphic word")){
            UnimorphicWord part = (UnimorphicWord)currentPart;
            txtArea.setText(part.getTurtle());
            currentPanel.add(txtArea);
        }
        else if (type.equals("Punctuation")){
            Punctuation part = (Punctuation)currentPart;
            txtArea.setText(part.getTurtle());
            currentPanel.add(txtArea);
        }
        else if (type.equals("Root")){
            Root part = (Root)currentPart;
            txtArea.setText(part.getTurtle());
            currentPanel.add(txtArea);
        }
        else if (type.equals("Polymorphic word")){
            PolymorphicWord part = (PolymorphicWord)currentPart;
            txtArea.setText(part.getTurtle());
            currentPanel.add(txtArea);
        }
        else if ( type.equals("Concord") ){
            Concord part = (Concord)currentPart;
            txtArea.setText(part.getTurtle());
            currentPanel.add(txtArea);
        }
        else if ( type.equals("Phrase") ){
            Phrase part = (Phrase)currentPart;
            txtArea.setText(part.getTurtle());
            currentPanel.add(txtArea);
        }
        else if ( type.equals("Copula") ){
            Copula part = (Copula)currentPart;
            txtArea.setText(part.getTurtle());
            currentPanel.add(txtArea);
        }
        else if ( type.equals("Unimorphic affix")){
            UnimorphicAffix part = (UnimorphicAffix)currentPart;
            txtArea.setText(part.getTurtle());
            currentPanel.add(txtArea);
        }
        else{
            //System.out.println("No current item selected");
            currentPanel = new JPanel();
            currentPanel.setBackground(Color.lightGray);
        }
        return currentPanel;
    }*/

    public JPanel getPartPanelTurtle(TemplatePortion currentPart) {
        String type = ToCTeditor.turtleGen.getPartType(currentPart);
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

        String turtle = ToCTeditor.turtleGen.getPartTurtle(currentPart);
        if (turtle.length() > 0 ){
            txtArea.setText(turtle);
            currentPanel.add(txtArea);
        }
        return currentPanel;
    }

    /**public String getPartType(TemplatePart part){
        String type;
        if (part instanceof Slot){
            type = "Slot";
        }
        else if ( part instanceof UnimorphicWord){
            type = "Unimorphic word";
        }
        else if ( part instanceof Punctuation){
            type = "Punctuation";
        }
        else if ( part instanceof Root){
            type = "Root";
        }
        else if ( part instanceof  PolymorphicWord ){
            type = "Polymorphic word";
        }
        else if (part instanceof Concord ){
            type = "Concord";
        }
        else if ( part instanceof Phrase ){
            type = "Phrase";
        }
        else if ( part instanceof Copula ){
            type = "Copula";
        }
        else if ( part instanceof UnimorphicAffix ){
            type = "Unimorphic affix";
        }
        else{
            type = "Unknown type";
        }
        return type;
    }*/

    public JPanel setupSlotEditor(Slot part){
        /**
         * Slot Editor Panel
         */
        JPanel pnlSlotEditor = new JPanel();
        pnlSlotEditor.setLayout(new BoxLayout(pnlSlotEditor, BoxLayout.Y_AXIS));
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
        //txtPartName.setText(part.getPartName());
        txtPartName.setText(part.getIdentification());
        /**txtPartName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Inside action performed for textfield");
                String text = txtPartName.getText();
                ToCTeditor.controller.setPartName(text);
                System.out.println("Current text: " + text);
            }
        });*/
        /**txtPartName.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt) {

            }
        });*/

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
        //txtNextPart.setText(part.getNextPart());
        txtNextPart.setText(ToCTeditor.dataModel.getItem( part.getIndex() + 1 ).toString());

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
        pnlSlotEditor.add(pnlNextPart);

        return pnlSlotEditor;
    }

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
        //txtPartName.setText(part.getPartName());
        txtPartName.setText(part.getIdentification());

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
        //txtNextPart.setText(part.getNextPart());
        txtNextPart.setText(part.getValue()); /**Placeholder*/

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
        pnlUnimorphicWordEditor.add(pnlNextPart);

        return pnlUnimorphicWordEditor;
    }

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
        //txtPartName.setText(part.getPartName());
        txtPartName.setText(part.toString()); /**Placeholder*/

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
        //txtValue.setText(part.getValue());
        txtValue.setText(part.toString()); /**Placeholder*/

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
        txtPartName.setText(part.getPartName());

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
        //txtPartName.setText(part.getPartName());
        txtPartName.setText(part.getIdentification());

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

        JTextField txtReliesOn = new JTextField();
        txtReliesOn.setFont(new Font("Sans", Font.PLAIN, 14));
        txtReliesOn.setMaximumSize(new Dimension(225, 30));
        //txtReliesOn.setText(part.getReliesOn());
        txtReliesOn.setText(part.getIdentification()); /**Placeholder*/

        pnlReliesOn.add(Box.createRigidArea(new Dimension(5,0)));
        pnlReliesOn.add(lblReliesOn);
        pnlReliesOn.add(Box.createRigidArea(new Dimension(5,0)));
        pnlReliesOn.add(txtReliesOn);
        pnlReliesOn.add(Box.createRigidArea(new Dimension(5,0)));


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
        txtFirstPart.setFont(new Font("Sans", Font.PLAIN, 14));
        txtFirstPart.setMaximumSize(new Dimension(225, 30));
        //txtFirstPart.setText(part.getFirstPart());
        txtFirstPart.setText(part.getIdentification()); /**Placeholder*/

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
        txtLastPart.setFont(new Font("Sans", Font.PLAIN, 14));
        txtLastPart.setMaximumSize(new Dimension(225, 30));
        //txtLastPart.setText(part.getLastPart());
        txtLastPart.setText(part.getIdentification()); /**Placeholder*/

        pnlLastPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLastPart.add(lblLastPart);
        pnlLastPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLastPart.add(txtLastPart);
        pnlLastPart.add(Box.createRigidArea(new Dimension(5,0)));

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
        //txtNextPart.setText(part.getNextPart());
        txtNextPart.setText(part.getIdentification()); /**Placeholder*/

        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(lblNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(txtNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));

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
        pnlPolymorphicWordEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlPolymorphicWordEditor.add(pnlNextPart);

        return pnlPolymorphicWordEditor;
    }

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
        txtPartName.setText(part.getPartName());

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

        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLabel.add(lblLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));
        pnlLabel.add(txtLabel);
        pnlLabel.add(Box.createRigidArea(new Dimension(5,0)));

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
        txtType.setText(part.getType());

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

        JTextField txtNextPart = new JTextField();
        txtNextPart.setFont(new Font("Sans", Font.PLAIN, 14));
        txtNextPart.setMaximumSize(new Dimension(225, 30));
        txtNextPart.setText(part.getNextPart());

        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(lblNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(txtNextPart);
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
        pnlConcordEditor.add(pnlNextPart);

        return pnlConcordEditor;
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
        //txtPartName.setText(part.getPartName());
        txtPartName.setText(part.getValue()); /**Placeholder*/

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
        //txtNextPart.setText(part.getNextPart());
        txtNextPart.setText(part.getValue()); /**Placeholder*/

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
        pnlPhraseEditor.add(pnlNextPart);

        return pnlPhraseEditor;
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
        txtPartName.setText(part.getPartName());

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
        txtNextPart.setText(part.getNextPart());

        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(lblNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));
        pnlNextPart.add(txtNextPart);
        pnlNextPart.add(Box.createRigidArea(new Dimension(5,0)));

        /***
         * Add all panels to main panel - i.e. Slot editor panel
         */
        pnlCopulaEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlCopulaEditor.add(pnlPartName);
        pnlCopulaEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlCopulaEditor.add(pnlLabel);
        pnlCopulaEditor.add(Box.createRigidArea(new Dimension(0,10)));
        pnlCopulaEditor.add(pnlNextPart);

        return pnlCopulaEditor;
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
        txtPartName.setText(part.getPartName());

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
}
