import za.co.mahlaza.research.grammarengine.base.models.template.Template;
import za.co.mahlaza.research.grammarengine.nguni.zu.ZuluFeatureParser;
import za.co.mahlaza.research.templateparsing.TemplateReader;
import za.co.mahlaza.research.templateparsing.URIS;

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


    static boolean DEBUG = true;
    static ControlThread controller;
    static ViewThread gui;
    static DataModel dataModel;

    static TurtleCode turtleGen;

    static ToCTeditorFrame frame;
    static CreateTemplate homeScreen;
    static TemplateItems templateItems;
    static CreateItem createItem;
    static CreateMorpheme createMorpheme;

    static int prevToggleBtnState;
    // By default, set the gui to show the turtle preview
    static int toggleBtnState;

    public static void main(String[] args) {


        frame = new ToCTeditorFrame(1000, 600);
        homeScreen = new CreateTemplate(frame);
        templateItems = new TemplateItems(frame);
        createItem = new CreateItem(frame);
        createMorpheme = new CreateMorpheme(frame);

        turtleGen = new TurtleCode();
        dataModel = new DataModel();

        homeScreen.setupGUI();

        /**
        dataModel.addPart(new Slot("slot1", "s1", "concord1"));
        dataModel.addPart(new Concord("concord1", "c1", "subjectVerb", "root1"));
        dataModel.addPart(new Root("root1", "nke"));
        dataModel.addPart(new PolymorphicWord("p1", "slot1", "o", "nke", "q1"));
        */
        //controller = new ControlThread(dataModel);
        //controller.start();
        //gui = new ViewThread(homeScreen, templateItems, createItem, dataModel);
        //gui.start()

        // By default, set the gui to show the turtle preview
        toggleBtnState = 1;
        prevToggleBtnState = 1;

        /**
        try{
            testParseTemplate();
        }
        catch(Exception e){
            e.printStackTrace();
        }*/

    }

    public static void testParseTemplate() throws Exception {
        String templatePath = "/home/root07/Documents/Academics/Templates/template1.1.ttl";
        String templateName = "templ1.1";
        String templateURI = "http://people.cs.uct.ac.za/~zmahlaza/templates/owlsiz/";

        TemplateReader.Init(new ZuluFeatureParser());
        TemplateReader.setTemplateOntologyNamespace(URIS.ToCT_NS);
        Template template = TemplateReader.parseTemplate(templateName, templateURI, templatePath);
    }
}
