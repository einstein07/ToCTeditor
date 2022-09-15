import za.co.mahlaza.research.grammarengine.base.models.template.TemplatePortion;

/*
 * @(#) ViewThread.java   1.0   Nov 18, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
public class ViewThread extends Thread {
    //private CreateTemplate homeScreen;
    //TemplateItems templateItems;
    //private CreateItem createItem;
    //private DataModel dataModel;

    boolean callCreateTemplate;
    boolean callTemplateItems;
    boolean callCreateItem;
    boolean callCreateMorpheme;

    private Part currentPart;
    private TemplatePortion currentTemplatePortion;

    private int index;
    private int toggleBtnState;
    private int prevToggleBtnState;

    public ViewThread() {

        this.callCreateTemplate = false;
        this.callTemplateItems = false;
        this.callCreateItem = false;
        this.callCreateMorpheme = false;

        // By default, set the gui to show the turtle preview
        //this.toggleBtnState = 1;
        //this.prevToggleBtnState = 1;

    }

    public ViewThread(CreateTemplate homeScreen, TemplateItems templateItems, CreateItem createItem, DataModel datamodel) {
        /**this.homeScreen = homeScreen;
        this.templateItems = templateItems;
        this.createItem = createItem;
        this.dataModel = datamodel;*/

        this.callCreateTemplate = false;
        this.callTemplateItems = false;
        this.callCreateItem = false;
        this.callCreateMorpheme = false;

        // By default, set the gui to show the turtle preview
        //this.toggleBtnState = 1;
        //this.prevToggleBtnState = 1;


        //this.index = -1;
        //homeScreen.setupGUI();
    }

    public int getToggleBtnState() {
        return ToCTeditor.toggleBtnState;
    }

    public void setToggleBtnState(int toggleBtnState) {
        ToCTeditor.toggleBtnState = ToCTeditor.toggleBtnState;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Part getCurrentPart() {
        currentPart = ToCTeditor.dataModel.getPart(index);
        return currentPart;
    }


    public TemplatePortion getCurrentTemplatePortion(){
        currentTemplatePortion = ToCTeditor.dataModel.getTemplatePortion(index);
        return currentTemplatePortion;
    }


    public void setCallCreateTemplate(boolean callCreateTemplate) {
        this.callCreateTemplate = callCreateTemplate;
    }

    public void setCallTemplateItems(boolean callTemplateItems) {
        this.callTemplateItems = callTemplateItems;
    }

    public void setCallCreateItem(boolean callCreateItem) {
        this.callCreateItem = callCreateItem;
    }
    public void setCallCreateMorpheme(boolean callCreateMorpheme) {
        this.callCreateMorpheme = callCreateMorpheme;
    }

    @Override
    public void run() {
        //System.out.println("ToCTeditor v 1.0");
        //while (true) {
        currentTemplatePortion = ToCTeditor.dataModel.getTemplatePortion(index);

            //System.out.println(callTemplateItems);
            if (callCreateTemplate) {
                //currentPart = dataModel.getPart(index);
                //homeScreen.setupGUI();
                ToCTeditor.homeScreen.setupGUI();
                callCreateTemplate = false;
            }
            else if (callTemplateItems) {


                //templateItems.setupGUI(templateItems.getPartPanelEditor(currentPart), templateItems.getPartPanelTurtle(currentPart));
                //templateItems.setupGUI(templateItems.getPartPanelEditor(currentPart), templateItems.getPartPanelTurtle(currentPart));
                //System.out.println("Create template items called");
                ToCTeditor.templateItems.setupGUI(ToCTeditor.templateItems.getPartPanelEditor(currentTemplatePortion), ToCTeditor.templateItems.getPartPanelTurtle(currentTemplatePortion));
                callTemplateItems = false;
            }
            else if (callCreateItem) {
                //currentPart = ToCTeditor.dataModel.getPart(index);
                //createItem.setupGUI();
                ToCTeditor.createItem.setupGUI();
                callCreateItem = false;
            }
            else if (callCreateMorpheme) {
                //currentTemplatePortion = ToCTeditor.dataModel.getTemplatePortion(index);
                ToCTeditor.createMorpheme.setupGUI(currentTemplatePortion);
                callCreateItem = false;
            }
            else if (ToCTeditor.toggleBtnState != ToCTeditor.prevToggleBtnState){
                //ToCTeditor.gui.templateItems.updateEditorTurtlePanel(templateItems.getPartPanelEditor(currentPart), templateItems.getPartPanelTurtle(currentPart));
                ToCTeditor.templateItems.updateEditorTurtlePanel(ToCTeditor.templateItems.getPartPanelEditor(currentTemplatePortion), ToCTeditor.templateItems.getPartPanelTurtle(currentTemplatePortion));
                ToCTeditor.prevToggleBtnState = ToCTeditor.toggleBtnState;
            }


            //templateItems.getPartPanelEditor(currentPart);
        //}
    }


}
