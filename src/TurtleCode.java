import za.co.mahlaza.research.grammarengine.base.models.interfaces.InternalSlotRootAffix;
import za.co.mahlaza.research.grammarengine.base.models.template.*;
import za.co.mahlaza.research.grammarengine.base.models.template.Concord;
import za.co.mahlaza.research.grammarengine.base.models.template.Copula;
import za.co.mahlaza.research.grammarengine.base.models.template.Locative;
import za.co.mahlaza.research.grammarengine.base.models.template.Phrase;
import za.co.mahlaza.research.grammarengine.base.models.template.PolymorphicWord;
import za.co.mahlaza.research.grammarengine.base.models.template.Punctuation;
import za.co.mahlaza.research.grammarengine.base.models.template.Slot;
import za.co.mahlaza.research.grammarengine.base.models.template.UnimorphicAffix;
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
    public String getPartTurtle(Part part){
        String turtle = "";
        //String type = getPartType(part);
        if (part != null) {
            if (part.getType().equals("Slot")) {
                turtle = "<" + part.getPartName() + "> a toct:Slot\n" +
                        "    ; toct:hasLabel \"" + part.getLabel() + "\"^^xsd:string\n" +
                        "    ; toct:hasNextPart " + part.getNextPart() + " .";
            } else if (part.getType().equals("Unimorphic word")) {
                turtle = "<" + part.getPartName() + "> a toct:UnimorphicWord\n" +
                        "    ; toct:hasValue \"" + part.getValue() + "\"^^xsd:string\n" +
                        "    ; toct:hasNextPart <" + part.getNextPart() + "> .";
            } else if (part.getType().equals("Polymorphic word")) {
                turtle = "<" + part.getPartName() + "> a toct:PolymorphicWord\n" +
                        "    ; toct:relies on <" + part.getReliesOn() + ">\n" +
                        "    ; toct:hasFirstPart <" + part.getFirstPart()  + ">\n" +
                        "    ; toct:hasLastPart <" + part.getLastPart() + ">\n" +
                        "    ; toct:hasNextPart <" + part.getNextPart() + "> .";
            }
            else if ( part.getType().equals("Phrase") ){
                 turtle = "<" + part.getPartName() + "> a toct:Phrase\n" +
                         "    ; toct:hasValue \"" + part.getValue() + "\"^^xsd:string\n" +
                         "    ; toct:hasNextPart <" + part.getNextPart() + "> .";
             }
            else if (part.getType().equals("Punctuation")) {
                turtle = "<" + part.getPartName() + "> a toct:Punctuation\n" +
                        "    ; toct:hasValue \"" + part.getValue()  + "^^xsd:string .";
            }
            else if (part.getType().equals("Concord")){
                turtle = "<" + part.getPartName() + "> a toct:Concord\n" +
                        "    ; cao:hasConcordType <" + part.getType() + ">\n" +
                        "    ; toct:hasLabel \"" + part.getLabel() + "\"^^xsd:string\n" +
                        "    ; toct:hasNextPart <" + part.getNextPart() + "> .";
            }
            else if (part.getType().equals("Copula")){
                turtle = "<" + part.getPartName() + "> a toct:Copula\n" +
                        "    ; toct:hasLabel \"" + part.getLabel() + "\"^^xsd:string\n" +
                        "    ; toct:hasNextPart <" + part.getNextPart() + "> .";
            }
            else if (part.getType().equals("Locative")){
                turtle = "<" + part.getPartName() + "> a toct:Locative\n" +
                        "    ; toct:hasLabel \"" + part.getLabel() + "\"\n" +
                        "    ; toct:hasNextPart <" + part.getNextPart() + "> .";
            }
            else if (part.getType().equals("Unimorphic affix")){
                turtle = "<" + part.getPartName() + "> a toct:UnimorphicAffix\n" +
                        "    ; toct:hasValue \"" + part.getValue() + "\"^^xsd:string\n" +
                        "    ; toct:hasNextPart <" + part.getNextPart() + "> .";
            }
            else if (part.getType().equals("Root")){
                turtle = "<" + part.getPartName() + "> a toct:Root\n" +
                        "    ; toct:hasValue \"" + part.getValue() + "\"^^xsd:string .";
            }

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

    public String getMorphemeId(InternalSlotRootAffix affix) {
        String id;
        if (affix  instanceof AffixChunk) {
            id = "Affix chunk";
        }
        else if (affix instanceof Concord){
            id = ((Concord)affix).getIdentification();
        }
        else if (affix instanceof Copula){
            id = ((Copula)affix).getIdentification();
        }
        else if (affix instanceof Locative){
            id = ((Locative)affix).getIdentification();
        }
        else if (affix instanceof  Slot) {
            id = ((Slot)affix).getIdentification();
        }
        else if (affix instanceof UnimorphicAffix){
            id = "Unimorphic affix";
        }
        else{
            id = "Unknown type";
        }
        return id;
    }

    public String getMorphemeType(InternalSlotRootAffix affix) {
        String type;
        if (affix  instanceof AffixChunk) {
            type = "Affix chunk";
        }
        else if (affix instanceof Concord){
            type = "Concord";
        }
        else if (affix instanceof Copula){
            type = "Copula";
        }
        else if (affix instanceof Locative){
            type = "Locative";
        }
        else if (affix instanceof  Slot) {
            type = "Slot";
        }
        else if (affix instanceof UnimorphicAffix){
            type = "Unimorphic affix";
        }
        else{
            type = "Unknown type";
        }
        return type;
    }
}
