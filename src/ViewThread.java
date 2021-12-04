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
    private CreateTemplate homeScreen;
     TemplateItems templateItems;
    private CreateItem createItem;
    private DataModel dataModel;

    boolean callCreateTemplate;
    boolean callTemplateItems;
    boolean callCreateItem;

    private Part currentPart;

    private int index;
    private int toggleBtnState;
    private int prevToggleBtnState;

    public ViewThread(CreateTemplate homeScreen, TemplateItems templateItems, CreateItem createItem, DataModel datamodel) {
        this.homeScreen = homeScreen;
        this.templateItems = templateItems;
        this.createItem = createItem;
        this.dataModel = datamodel;

        this.callCreateTemplate = false;
        this.callTemplateItems = false;
        this.callCreateItem = false;

        // By default, set the gui to show the turtle preview
        this.toggleBtnState = 1;
        this.prevToggleBtnState = 1;


        //this.index = -1;
        homeScreen.setupGUI();
    }

    public int getToggleBtnState() {
        return toggleBtnState;
    }

    public void setToggleBtnState(int toggleBtnState) {
        this.toggleBtnState = toggleBtnState;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Part getCurrentPart() {
        currentPart = dataModel.getPart(index);
        return currentPart;
    }


    @Override
    public void run() {
        System.out.println("ToCTeditor v 1.0");
        while (true) {
            currentPart = dataModel.getPart(index);

            System.out.println(callTemplateItems);
            if (callTemplateItems) {
                currentPart = dataModel.getPart(index);

                //templateItems.setupGUI(templateItems.getPartPanelEditor(currentPart), templateItems.getPartPanelTurtle(currentPart));
                templateItems.setupGUI(templateItems.getPartPanelEditor(currentPart), templateItems.getPartPanelTurtle(currentPart));
                callTemplateItems = false;
            } else if (callCreateItem) {
                //currentPart = dataModel.getPart(index);
                createItem.setupGUI();
                callCreateItem = false;
            } else if (callCreateTemplate) {
                //currentPart = dataModel.getPart(index);
                homeScreen.setupGUI();
                callCreateTemplate = false;
            }
            else if (toggleBtnState != prevToggleBtnState){
                //ToCTeditor.gui.templateItems.updateEditorTurtlePanel(templateItems.getPartPanelEditor(currentPart), templateItems.getPartPanelTurtle(currentPart));
                ToCTeditor.gui.templateItems.updateEditorTurtlePanel(templateItems.getPartPanelEditor(currentPart), templateItems.getPartPanelTurtle(currentPart));
                prevToggleBtnState = toggleBtnState;
            }


            //templateItems.getPartPanelEditor(currentPart);
        }
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
}
