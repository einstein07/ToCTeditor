/*
 * @(#) CreateMorpheme.java   1.0   Dec 08, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

import za.co.mahlaza.research.grammarengine.base.models.feature.ConcordType;
import za.co.mahlaza.research.grammarengine.base.models.feature.Feature;
import za.co.mahlaza.research.grammarengine.base.models.interfaces.InternalSlotRootAffix;
import za.co.mahlaza.research.grammarengine.base.models.template.Concord;
import za.co.mahlaza.research.grammarengine.base.models.template.Copula;
import za.co.mahlaza.research.grammarengine.base.models.template.Locative;
import za.co.mahlaza.research.grammarengine.base.models.template.PolymorphicWord;
import za.co.mahlaza.research.grammarengine.base.models.template.Root;
import za.co.mahlaza.research.grammarengine.base.models.template.Slot;
import za.co.mahlaza.research.grammarengine.base.models.template.TemplatePortion;
import za.co.mahlaza.research.grammarengine.base.models.template.UnimorphicAffix;
import za.co.mahlaza.research.grammarengine.base.models.template.UnimorphicWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateMorpheme {
    private int frameX;
    private int frameY;
    private ToCTeditorFrame frame;

    List<InternalSlotRootAffix> wordPortions;
    List<Feature> featuresList;


    /**
     * Existing Item panel
     */
    JPanel pnlExistingItem;

    public CreateMorpheme(ToCTeditorFrame frame) {
        this.frameX = frame.getFrameX();
        this.frameY = frame.getFrameY();
        this.frame = frame;


    }

    public void setupGUI(TemplatePortion templatePortion){

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
        JLabel label = new JLabel("Create morpheme");
        pnlItemsTitle.setBackground(Color.lightGray);
        label.setFont(new Font("Sans", Font.PLAIN, 15));

        pnlItemsTitle.add(label);

        /**
         * Panel for Existing item selection title
         */
        JPanel pnlExistingItemTitle = new JPanel();
        pnlExistingItemTitle.setLayout(new BoxLayout(pnlExistingItemTitle, BoxLayout.LINE_AXIS));
        pnlExistingItemTitle.setMaximumSize(new Dimension(700,15));
        pnlExistingItemTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlExistingItemTitle.setBackground(Color.lightGray);


        JLabel lblExistingItemTitle = new JLabel("Select from existing morphemes");
        lblExistingItemTitle.setFont(new Font("Sans", Font.PLAIN, 15));
        lblExistingItemTitle.setMaximumSize(new Dimension(345,15));



        /**
         * Add title labels to labels panel
         */
        pnlExistingItemTitle.add(lblExistingItemTitle);


        /**
         * Existing Item panel
         */
        //JPanel pnlExistingItem = new JPanel();
        pnlExistingItem = new JPanel();
        pnlExistingItem.setLayout(new BoxLayout(pnlExistingItem, BoxLayout.Y_AXIS));
        pnlExistingItem.setMaximumSize(new Dimension(700,250));
        pnlExistingItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlExistingItem.setBackground(Color.lightGray);
        pnlExistingItem.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        //pnlExistingItem.add(setupExistingItems(ToCTeditor.dataModel.getData()));
        //pnlExistingItem.add(setupExistingItems(ToCTeditor.dataModel.getTemplatePortions()));
        //List<InternalSlotRootAffix> wordPortions = new ArrayList<>();

        if (templatePortion != null) {
            if (((PolymorphicWord) templatePortion).getAllMorphemes().size() > 0) {
                wordPortions = ((PolymorphicWord) templatePortion).getAllMorphemes();
            }
            pnlExistingItem.add(setupExistingItems(wordPortions));

        }
        if (wordPortions == null){
            this.wordPortions = new ArrayList<>();
            this.featuresList = new ArrayList<>();
        }
        /**
         * Create button set visible when template portion is null - i.e. creating new polymorphic word
         */
        JButton btnCreate = new JButton("Create");
        btnCreate.setFont(new Font("Sans", Font.PLAIN, 15));
        btnCreate.setMaximumSize(new Dimension(345,30));
        btnCreate.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add the listener to the jbutton to handle the "pressed" event
        //List<InternalSlotRootAffix> finalWordPortions = wordPortions;
        btnCreate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                if (wordPortions.size() > 1){
                    ToCTeditor.dataModel.addTemplatePortion(new PolymorphicWord(wordPortions, featuresList));
                    int portionNumber = 0;
                    if (ToCTeditor.dataModel.getTemplatePortions().size() > 1){
                        portionNumber = ToCTeditor.dataModel.getTemplatePortions().size();
                        ToCTeditor.dataModel.getTemplatePortion(ToCTeditor.dataModel.getTemplatePortions().size()-2)
                                .setNextWordPart(ToCTeditor.dataModel.getTemplatePortion(ToCTeditor.dataModel.getTemplatePortions().size()-1));
                    }
                    String id = "polyword" + portionNumber;
                    ToCTeditor.dataModel.getTemplatePortion(ToCTeditor.dataModel.getTemplatePortions().size() - 1).setSerialisedName(id);

                    int currIndex = ToCTeditor.dataModel.getTemplatePortions().size() - 1;
                    ToCTeditor.gui = new ViewThread();
                    ToCTeditor.gui.setIndex(currIndex);
                    ToCTeditor.gui.setCallTemplateItems(true);
                    ToCTeditor.gui.start();
                }
                else{
                    JOptionPane.showMessageDialog(
                            frame,
                            "Polymorphic word cannot have less than two word portions.",
                            "Polymorphic word creation error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }


            }
        });

        /**
         * Add button
         */
        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Sans", Font.PLAIN, 15));
        btnAdd.setMaximumSize(new Dimension(345,30));
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add the listener to the jbutton to handle the "pressed" event
        //List<InternalSlotRootAffix> finalWordPortions = wordPortions;
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                if (wordPortions.size() != ((PolymorphicWord) templatePortion).getAllMorphemes().size()){
                    int i = ((PolymorphicWord) templatePortion).getAllMorphemes().size();// - 1;
                    for (; i < wordPortions.size(); i++){
                        ((PolymorphicWord) templatePortion).addMorpheme(wordPortions.get(i));
                    }

                    int currIndex = ToCTeditor.dataModel.getTemplatePortions().size() - 1;
                    ToCTeditor.gui = new ViewThread();
                    ToCTeditor.gui.setIndex(currIndex);

                    ToCTeditor.gui.setCallTemplateItems(true);
                    ToCTeditor.gui.start();
                }
                else{
                    JOptionPane.showMessageDialog(
                            frame,
                            "There is nothing to add.",
                            "Morpheme creation error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }


            }
        });

        /**
         * Cancel button - set visible when template portion is not null - i.e. cancel the addition of a morpheme to
         * the current polymorphic word
         */
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Sans", Font.PLAIN, 15));

        btnCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ToCTeditor.gui = new ViewThread();
                ToCTeditor.gui.setCallTemplateItems(true);
                ToCTeditor.gui.start();

            }
        });


        /**
         * Back button set visible when template portion is null - i.e. go back to choose a template portion to create
         */
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Sans", Font.PLAIN, 15));

        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ToCTeditor.gui = new ViewThread(ToCTeditor.homeScreen, ToCTeditor.templateItems, ToCTeditor.createItem, ToCTeditor.dataModel);

                ToCTeditor.gui.setCallCreateItem(true);

                ToCTeditor.gui.start();
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

        if (templatePortion == null ) {
            pnlButtons.add(btnBack);
            btnBack.setMaximumSize(new Dimension(345, 30));
            pnlButtons.add(Box.createRigidArea(new Dimension(10,0)));
            pnlButtons.add(btnCreate);
            btnCreate.setMaximumSize(new Dimension(345, 30));
        }
        else{
            pnlButtons.add(btnCancel);
            btnCancel.setMaximumSize(new Dimension(345, 30));
            pnlButtons.add(Box.createRigidArea(new Dimension(10,0)));
            pnlButtons.add(btnAdd);
            btnAdd.setMaximumSize(new Dimension(345, 30));
        }

        // Add heading label to list panel
        listPane.add(pnlItemsTitle);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        // Add template name text field to list panel
        listPane.add(createPartOptions(templatePortion));
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
        /********************************/
        frame.repaint();
        /*****************************/
    }

    public JComponent createTemplateItem(TemplatePortion templatePortion, String type){

        JButton btnType = new JButton(type);
        btnType.setFont(new Font("Sans", Font.PLAIN, 13));
        btnType.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnType.setAlignmentY(Component.CENTER_ALIGNMENT);
        btnType.setMaximumSize(new Dimension(150, 50));
        btnType.setBackground(Color.white);

        /**
         * Add the listener to the JButton to handle the "pressed" event
         */

        if (type.equals("Concord")){
            btnType.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    List<Feature> featureList = new ArrayList<>();
                    int portionNumber = 0;
                    if ( wordPortions != null){
                        portionNumber = wordPortions.size();
                    }
                    String id = "concord" + portionNumber;
                    wordPortions.add(new Concord("", featureList));
                    wordPortions.get(wordPortions.size()-1).setSerialisedName(id);

                    int componentCount = pnlExistingItem.getComponentCount();
                    for (int i = (componentCount-1); i >= 0 ; i--){
                        pnlExistingItem.remove(i);
                    }

                    pnlExistingItem.add(Box.createRigidArea(new Dimension(0,5)));
                    pnlExistingItem.add(setupSearchField());
                    pnlExistingItem.add(setupExistingItems(wordPortions));
                    pnlExistingItem.getParent().revalidate();

                }
            });
        }
        else if (type.equals("Copula")){
            btnType.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    List<Feature> featureList = new ArrayList<>();
                    int portionNumber = 0;
                    if ( wordPortions != null){
                        portionNumber = wordPortions.size();
                    }
                    String id = "copula" + portionNumber;
                    wordPortions.add(new Copula("", featureList));
                    wordPortions.get(wordPortions.size()-1).setSerialisedName(id);

                    int componentCount = pnlExistingItem.getComponentCount();
                    for (int i = (componentCount-1); i >= 0 ; i--){
                        pnlExistingItem.remove(i);
                    }

                    pnlExistingItem.add(Box.createRigidArea(new Dimension(0,5)));
                    pnlExistingItem.add(setupSearchField());
                    pnlExistingItem.add(setupExistingItems(wordPortions));
                    pnlExistingItem.getParent().revalidate();

                }
            });
        }
        else if (type.equals("Locative")){
            btnType.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    List<Feature> featureList = new ArrayList<>();
                    int portionNumber = 0;
                    if ( wordPortions != null){
                        portionNumber = wordPortions.size();
                    }
                    String id = "locative" + portionNumber;
                    wordPortions.add(new Locative("", featureList));
                    wordPortions.get(wordPortions.size()-1).setSerialisedName(id);

                    int componentCount = pnlExistingItem.getComponentCount();
                    for (int i = (componentCount-1); i >= 0 ; i--){
                        pnlExistingItem.remove(i);
                    }

                    pnlExistingItem.add(Box.createRigidArea(new Dimension(0,5)));
                    pnlExistingItem.add(setupSearchField());
                    pnlExistingItem.add(setupExistingItems(wordPortions));
                    pnlExistingItem.getParent().revalidate();

                }
            });
        }
        else if (type.equals("Root")){
            btnType.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    List<Feature> featureList = new ArrayList<>();
                    int portionNumber = 0;
                    if ( wordPortions != null){
                        portionNumber = wordPortions.size();
                    }
                    String id = "root" + portionNumber;
                    wordPortions.add(new Root("", featureList));
                    wordPortions.get(wordPortions.size()-1).setSerialisedName(id);

                    int componentCount = pnlExistingItem.getComponentCount();
                    for (int i = (componentCount-1); i >= 0 ; i--){
                        pnlExistingItem.remove(i);
                    }

                    pnlExistingItem.add(Box.createRigidArea(new Dimension(0,5)));
                    pnlExistingItem.add(setupSearchField());
                    pnlExistingItem.add(setupExistingItems(wordPortions));
                    pnlExistingItem.getParent().revalidate();

                }
            });
        }
        else if (type.equals("UnimorphicAffix")){
            btnType.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    List<Feature> featureList = new ArrayList<>();
                    int portionNumber = 0;
                    if ( wordPortions != null){
                        portionNumber = wordPortions.size();
                    }
                    String id = "uniaffix" + portionNumber;
                    wordPortions.add(new UnimorphicAffix("", featureList));
                    wordPortions.get(wordPortions.size()-1).setSerialisedName(id);

                    int componentCount = pnlExistingItem.getComponentCount();
                    for (int i = (componentCount-1); i >= 0 ; i--){
                        pnlExistingItem.remove(i);
                    }

                    pnlExistingItem.add(Box.createRigidArea(new Dimension(0,5)));
                    pnlExistingItem.add(setupSearchField());
                    pnlExistingItem.add(setupExistingItems(wordPortions));
                    pnlExistingItem.getParent().revalidate();

                }
            });
        }
        else if (type.equals("Slot")){
            btnType.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    List<Feature> featureList = new ArrayList<>();
                    int portionNumber = 0;
                    if ( wordPortions != null){
                        portionNumber = wordPortions.size();
                    }
                    String id = "slot" + portionNumber;
                    wordPortions.add(new Slot("", featureList));
                    wordPortions.get(wordPortions.size()-1).setSerialisedName(id);

                    int componentCount = pnlExistingItem.getComponentCount();
                    for (int i = (componentCount-1); i >= 0 ; i--){
                        pnlExistingItem.remove(i);
                    }

                    pnlExistingItem.add(Box.createRigidArea(new Dimension(0,5)));
                    pnlExistingItem.add(setupSearchField());
                    pnlExistingItem.add(setupExistingItems(wordPortions));
                    pnlExistingItem.getParent().revalidate();

                }
            });
        }

        return btnType;
    }

    public JPanel createPartOptions(TemplatePortion templatePortion){
        JPanel pnlItems = new JPanel();
        pnlItems.setLayout(new BoxLayout(pnlItems, BoxLayout.Y_AXIS));
        pnlItems.setMaximumSize(new Dimension(700,120/**170*/));
        pnlItems.setBackground(Color.lightGray);
        pnlItems.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlItems.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JPanel pnlRow1 = new JPanel();
        pnlRow1.setLayout(new BoxLayout(pnlRow1, BoxLayout.LINE_AXIS));
        pnlRow1.setMaximumSize(new Dimension(700,50));
        pnlRow1.setBackground(Color.lightGray);
        pnlRow1.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlRow1.add(Box.createRigidArea(new Dimension(43,0)));
        pnlRow1.add(createTemplateItem(templatePortion,"Concord"));
        pnlRow1.add(Box.createRigidArea(new Dimension(5,0)));

        pnlRow1.add(createTemplateItem(templatePortion,"Copula"));
        pnlRow1.add(Box.createRigidArea(new Dimension(5,0)));

        pnlRow1.add(createTemplateItem(templatePortion,"Locative"));
        pnlRow1.add(Box.createRigidArea(new Dimension(5,0)));

        pnlRow1.add(createTemplateItem(templatePortion,"Root"));
        pnlRow1.add(Box.createRigidArea(new Dimension(5,0)));

        JPanel pnlRow2 = new JPanel();
        pnlRow2.setLayout(new BoxLayout(pnlRow2, BoxLayout.LINE_AXIS));
        pnlRow2.setMaximumSize(new Dimension(700,50));
        pnlRow2.setBackground(Color.lightGray);
        pnlRow2.setAlignmentX(Component.CENTER_ALIGNMENT);


        pnlRow2.add(Box.createRigidArea(new Dimension(43,0)));
        pnlRow2.add(createTemplateItem(templatePortion,"UnimorphicAffix"));
        pnlRow2.add(Box.createRigidArea(new Dimension(5,0)));

        pnlRow2.add(createTemplateItem(templatePortion,"Slot"));
        pnlRow2.add(Box.createRigidArea(new Dimension(5,0)));

        /**
        pnlRow2.add(createTemplateItem("Punctuation"));
        pnlRow2.add(Box.createRigidArea(new Dimension(5,0)));

        pnlRow2.add(createTemplateItem("Root"));
        pnlRow2.add(Box.createRigidArea(new Dimension(5,0)));


        pnlRow2.add(createTemplateItem("Slot"));
        pnlRow2.add(Box.createRigidArea(new Dimension(5,0)));

        JPanel pnlRow3 = new JPanel();
        pnlRow3.setLayout(new BoxLayout(pnlRow3, BoxLayout.LINE_AXIS));
        pnlRow3.setMaximumSize(new Dimension(700,50));
        pnlRow3.setBackground(Color.lightGray);
        pnlRow3.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlRow3.add(Box.createRigidArea(new Dimension(43,0)));
        pnlRow3.add(createTemplateItem("Unimorphic affix"));
        pnlRow3.add(Box.createRigidArea(new Dimension(5,0)));


        pnlRow3.add(createTemplateItem("Unimorphic word"));
        pnlRow3.add(Box.createRigidArea(new Dimension(5,0)));*/


        pnlItems.add(Box.createRigidArea(new Dimension(0,5)));
        pnlItems.add(pnlRow1);
        pnlItems.add(Box.createRigidArea(new Dimension(0,5)));
        pnlItems.add(pnlRow2);
        //pnlItems.add(Box.createRigidArea(new Dimension(0,5)));
        //pnlItems.add(pnlRow3);
        return pnlItems;
    }

    /**public JComponent setupExistingItems(List<TemplatePart> list) {
     Box box = Box.createVerticalBox();
     box.setBackground(Color.lightGray);
     box.setMaximumSize(new Dimension(400, 200));
     JScrollPane pnlScroll = new JScrollPane(box, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
     pnlScroll.setMaximumSize(new Dimension(400, 200));
     pnlScroll.setBackground(Color.lightGray);


     for (TemplatePart part : list) {
     JLabel name = new JLabel(part.getPartName());
     JLabel type = new JLabel(ToCTeditor.gui.templateItems.getPartType(part));
     box.add(createItemComponent(name, type));
     }
     JPanel p = new JPanel();
     p.setMaximumSize(new Dimension(700, 200));
     p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
     p.setBackground(Color.lightGray);
     p.add(Box.createRigidArea(new Dimension(150,0)));
     p.add(pnlScroll);
     return p;
     }*/

    public JComponent setupExistingItems(List<InternalSlotRootAffix> list) {

        Box box = Box.createVerticalBox();
        box.setBackground(Color.lightGray);
        box.setMaximumSize(new Dimension(400, 250/**200*/));
        JScrollPane pnlScroll = new JScrollPane(box, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnlScroll.setMaximumSize(new Dimension(400, 250/**200*/));
        pnlScroll.setBackground(Color.lightGray);

        /**DragMouseAdapter dh = new DragMouseAdapter();
         box.addMouseListener(dh);
         box.addMouseMotionListener(dh);*/


        if (list != null /**&& list.size() > 0*/){
            for (InternalSlotRootAffix part : list) {
                JLabel name = new JLabel(ToCTeditor.turtleGen.getMorphemeId(part));
                JLabel type = new JLabel(ToCTeditor.turtleGen.getMorphemeType(part));
                box.add(createItemComponent(name, type));
            }
        }

        JPanel p = new JPanel();
        p.setMaximumSize(new Dimension(700, 200));
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        p.setBackground(Color.lightGray);
        p.add(Box.createRigidArea(new Dimension(150,0)));
        p.add(pnlScroll);
        return p;
    }
    public JComponent setupSearchField(){
        JPanel pnlSearchField = new JPanel();
        pnlSearchField.setLayout(new BoxLayout(pnlSearchField, BoxLayout.LINE_AXIS));
        pnlSearchField.setMaximumSize(new Dimension(700,30));
        pnlSearchField.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlSearchField.setBackground(Color.LIGHT_GRAY);

        /**
         * Search part text field
         */
        HintTextField txtSearch = new HintTextField("Search from existing items");
        txtSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtSearch.setMaximumSize(new Dimension(400,30));
        txtSearch.setFont(new Font("Sans", Font.PLAIN, 14));

        pnlSearchField.add(Box.createRigidArea(new Dimension(150,0)));
        pnlSearchField.add(txtSearch);


        return pnlSearchField;
    }

    private static JComponent createItemComponent(JComponent name, JComponent type) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLUE, 2)));

        JPanel pnlName = new JPanel();
        pnlName.setLayout(new BoxLayout(pnlName, BoxLayout.Y_AXIS));
        pnlName.setMaximumSize(new Dimension(400,50));
        pnlName.setBackground(Color.white);
        name.setFont(new Font("Sans", Font.PLAIN, 14));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlName.add(name);

        type.setFont(new Font("Sans", Font.PLAIN, 8));
        type.setMaximumSize(new Dimension(400,50));


        JPanel pnlType = new JPanel();
        pnlType.setLayout(new BoxLayout(pnlType, BoxLayout.LINE_AXIS));
        pnlType.setMaximumSize(new Dimension(400,50));
        pnlType.add(type);
        pnlType.add(Box.createRigidArea(new Dimension(60,0)));
        pnlType.setBackground(Color.white);



        p.setMaximumSize(new Dimension(400, 50));
        p.setMinimumSize(new Dimension(400, 50));
        p.add(pnlName);
        p.add(pnlType);
        p.setBackground(Color.lightGray);
        return p;
    }
}
