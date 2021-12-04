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

    static ControlThread controller;
    static ViewThread gui;
    static DataModel dataModel;

    static TurtleCode turtleGen;

    public static void main(String[] args) {

        ToCTeditorFrame frame = new ToCTeditorFrame(1000, 600);
        CreateTemplate homeScreen = new CreateTemplate(frame);
        TemplateItems templateItems = new TemplateItems(frame);
        CreateItem createItem = new CreateItem(frame);

        turtleGen = new TurtleCode();
        dataModel = new DataModel();

        dataModel.addPart(new Part(""));
        /**
        dataModel.addPart(new Slot("slot1", "s1", "concord1"));
        dataModel.addPart(new Concord("concord1", "c1", "subjectVerb", "root1"));
        dataModel.addPart(new Root("root1", "nke"));
        dataModel.addPart(new PolymorphicWord("p1", "slot1", "o", "nke", "q1"));
        */
        //controller = new ControlThread(dataModel);
        //controller.start();
        gui = new ViewThread(homeScreen, templateItems, createItem, dataModel);
        gui.start();


        //testParseTemplate();
    }

    public static void testParseTemplate () {
        System.out.println("Testing. . .");

        String templatePath = "/home/root07/Documents/Academics/Templates/template2.ttl";
        String templateName = "templ2";
        String templateURI = "http://people.cs.uct.ac.za/~zmahlaza/templates/owlsiz/";

        System.out.println("Name:" + templateName + " Path: " + templatePath);
        TemplateReader.Init(new ZuluFeatureParser());
        TemplateReader.setTemplateOntologyNamespace(URIS.ToCT_NS);
        Template template = TemplateReader.parseTemplate(templateName, templateURI, templatePath);
        System.out.println("Done.");
    }
}
