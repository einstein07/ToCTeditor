/*
 * @(#) ToCTeditor.java   1.0   Nov 10, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
public class ToCTeditor {
    static ControlThread controller;
    static ViewThread gui;
    static DataModel dataModel;

    public static void main(String[] args) {

        ToCTeditorFrame frame = new ToCTeditorFrame(1000, 600);
        CreateTemplate homeScreen = new CreateTemplate(frame);
        TemplateItems templateItems = new TemplateItems(frame);
        CreateItem createItem = new CreateItem(frame);


        dataModel = new DataModel();

        dataModel.addPart(new Slot("slot1", "s1", "concord1"));
        dataModel.addPart(new Concord("concord1", "c1", "subjectVerb", "root1"));
        dataModel.addPart(new Root("root1", "nke"));

        /**controller = new ControlThread(dataModel);
        controller.start();*/
        gui = new ViewThread(homeScreen, templateItems, createItem, dataModel);
        gui.start();

    }
}
