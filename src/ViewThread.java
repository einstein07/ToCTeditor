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
    CreateTemplate homeScreen;
    TemplateItems templateItems;
    CreateItem createItem;

    boolean callCreateTemplate;
    boolean callTemplateItems;
    boolean callCreateItem;

    private TemplatePart currentPart;

    public ViewThread(CreateTemplate homeScreen, TemplateItems templateItems, CreateItem createItem) {
        this.homeScreen = homeScreen;
        this.templateItems = templateItems;
        this.createItem = createItem;

        this.callCreateTemplate = false;
        this.callTemplateItems = false;
        this.callCreateItem = false;

        homeScreen.setupGUI();
    }

    @Override
    public void run() {
        while (true) {
            currentPart = ToCTeditor.controller.getCurrentPart();
            //System.out.println("Called template items equals: " + callTemplateItems);
            if (callTemplateItems) {
                templateItems.setupGUI();
                templateItems.getPartPanelEditor(currentPart);
                callTemplateItems = false;
            } else if (callCreateItem) {
                createItem.setupGUI();
                callCreateItem = false;
            } else if (callCreateTemplate) {
                homeScreen.setupGUI();
                callCreateTemplate = false;
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
