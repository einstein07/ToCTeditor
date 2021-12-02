import za.co.mahlaza.research.grammarengine.base.models.template.Phrase;
import za.co.mahlaza.research.grammarengine.base.models.template.PolymorphicWord;
import za.co.mahlaza.research.grammarengine.base.models.template.Punctuation;
import za.co.mahlaza.research.grammarengine.base.models.template.Slot;
import za.co.mahlaza.research.grammarengine.base.models.template.TemplatePortion;
import za.co.mahlaza.research.grammarengine.base.models.template.UnimorphicWord;

/*
 * @(#) TurtleCode.java   1.0   Dec 01, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
public class TurtleCode {
    public String getPartTurtle(TemplatePortion part){
        String turtle = "";
        String type = getPartType(part);
        if ( type.equals("Slot")){
            turtle = "<" + ((Slot) part).getIdentification() + "> a toct:Slot\n" +
                    "    ; toct:hasLabel \"" + ((Slot) part).getLabel() + "\"^^xsd:string\n" +
                    "    ; toct:hasNextPart " + ToCTeditor.dataModel.getItem( ((Slot) part).getIndex() + 1 ) +" .";
        }
        else if ( type.equals("Unimorphic word") ){
            turtle = "<" + ((UnimorphicWord)part).getIdentification() + "> a toct:UnimorphicWord\n" +
                    "    ; toct:hasValue \"" + ((UnimorphicWord)part).getValue() + "\"^^xsd:string\n" +
                    "    ; toct:hasNextPart <" + ((UnimorphicWord)part).getClass() + "> ." ;
        }
        else if ( type.equals("Polymorphic word") ){
            turtle = "<" + ((PolymorphicWord)part).getIdentification() + "> a toct:PolymorphicWord\n" +
                    "    ; toct:relies on <" + ((PolymorphicWord)part).getClass() + ">\n" +
                    "    ; toct:hasFirstPart <" + ((PolymorphicWord)part).getClass() + ">\n" +
                    "    ; toct:hasLastPart <" + ((PolymorphicWord)part).getClass() + ">\n" +
                    "    ; toct:hasNextPart <" + ((PolymorphicWord)part).getClass() + "> .";
        }
        else if ( type.equals("Phrase") ){
            turtle = "<" + ((Phrase)part).getClass() + "> a toct:Phrase\n" +
                    "    ; toct:hasValue \"" + ((Phrase)part).getValue() + "\"^^xsd:string\n" +
                    "    ; toct:hasNextPart <" + ((Phrase)part).getClass() + "> .";
        }
        else if ( type.equals("Punctuation") ){
            turtle = "<" + ((Punctuation)part).getClass() + "> a toct:Punctuation\n" +
                    "    ; toct:hasValue \"" + ((Punctuation)part).toString() + "^^xsd:string .";
        }
        return turtle;
    }
    public String getPartType(TemplatePortion part){
        String type;
        if (part instanceof Slot){
            type = "Slot";
        }
        else if (part instanceof UnimorphicWord){
            type = "Unimorphic word";
        }
        else if (part instanceof PolymorphicWord){
            type = "Polymorphic word";
        }
        else if (part instanceof Phrase){
            type = "Phrase";
        }
        else if (part instanceof Punctuation){
            type = "Punctuation";
        }
        else{
            type = "Unknown type";
        }
        return type;
    }
    public String getPartId(TemplatePortion part){
        String id;
        if (part instanceof Slot){
            id = ((Slot)part).getIdentification();
        }
        else if (part instanceof UnimorphicWord){
            id = ((UnimorphicWord)part).getIdentification();
        }
        else if (part instanceof PolymorphicWord){
            id = ((PolymorphicWord)part).getIdentification();
        }
        else if (part instanceof Phrase){
            id = ((Phrase)part).getValue(); /**Placeholder*/
        }
        else if (part instanceof Punctuation){
            id = ((Punctuation)part).toString(); /**Placeholder*/
        }
        else{
            id = "Unknown type";
        }
        return id;
    }
}
