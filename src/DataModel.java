/*
 * @(#) DataModel.java   1.0   Nov 18, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

import za.co.mahlaza.research.grammarengine.base.models.interfaces.InternalSlotRootAffix;
import za.co.mahlaza.research.grammarengine.base.models.template.Concord;
import za.co.mahlaza.research.grammarengine.base.models.template.Copula;
import za.co.mahlaza.research.grammarengine.base.models.template.PolymorphicWord;
import za.co.mahlaza.research.grammarengine.base.models.template.Punctuation;
import za.co.mahlaza.research.grammarengine.base.models.template.Slot;
import za.co.mahlaza.research.grammarengine.base.models.template.Template;
import za.co.mahlaza.research.grammarengine.base.models.template.TemplatePortion;
import za.co.mahlaza.research.grammarengine.base.models.template.UnimorphicWord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataModel {
    //private List<TemplatePart> templateItems;
    private List<TemplatePortion> templateWords;
    //private List<InternalSlotRootAffix> morphemes;

    public static List<Part> parts;

    Template template;
    public DataModel(){
        //templateItems = new ArrayList<>();
        templateWords = new ArrayList<>();
        parts = new ArrayList<>();
    }

    public void setTemplate(Template template){
        this.template = template;
        this.templateWords = template.getTemplatePortions();
        for (TemplatePortion part: templateWords){
            addPart(part);
        }
    }
    public void addPart(Part part){
        //templateItems.add(part);
    }

    /**public void addPart(TemplatePortion part){
        templateWords.add(part);
    }*/
    public void addPart(TemplatePortion part){
        String type = ToCTeditor.turtleGen.getPartType(part);
        if (type.equals("Slot")){
            int i = parts.size();
            parts.add(new Part(((Slot)part).getIdentification()));
            parts.get(i).setLabel( ((Slot)part).getLabel());
            parts.get(i).setValue( ((Slot)part).getValue() );
            parts.get(i).setNextPart( "placeholder" );
            parts.get(i).setMorpheme(false);
            parts.get(i).setType( "Slot" );
        }
        if (type.equals("Punctuation")){
            int i = parts.size();
            parts.add(new Part("placeholder"));
            parts.get(i).setValue( ((Punctuation)part).toString() );
            parts.get(i).setMorpheme ( false );
            parts.get(i).setType( "Punctuation" );
        }
        else if (type.equals("Polymorphic word")){
            int i = parts.size();
            parts.add(new Part(((PolymorphicWord)part).getIdentification()));
            parts.get(i).setReliesOn( "placeholder" );
            parts.get(i).setFirstPart( "placeholder" );
            parts.get(i).setLastPart( "placeholder" );
            parts.get(i).setNextPart( "placeholder" );
            parts.get(i).setMorpheme( false );
            parts.get(i).setType( "Polymorphic word" );

            List<InternalSlotRootAffix> morphemes = ((PolymorphicWord)part).getAllMorphemes();
            //System.out.println("Polymophic type: " + ((PolymorphicWord)part).getIdentification() + "Affix boxes: " + morphemes.size());
            for (InternalSlotRootAffix affix : morphemes) {
                String affixType = ToCTeditor.turtleGen.getMorphemeType(affix);
                if (affixType.equals("Concord")){
                    int j = parts.size();
                    parts.add(new Part(((Concord)affix).getIdentification()));
                    parts.get(j).setLabel( ((Concord)affix).getLabel() );
                    parts.get(j).setConcordType( ((Concord)affix).getConcordType().getTypeString() );
                    parts.get(j).setMorpheme( true );
                    parts.get(j).setParentId( parts.get(i).getPartName() );
                    parts.get(j).setType( "Concord" );
                }
                else if (affixType.equals("Copula")){
                    int j = parts.size();
                    parts.add(new Part(((Copula)affix).getIdentification()));
                    parts.get(j).setLabel( "placeholder" );
                    parts.get(j).setNextPart( "placeholder" );
                    parts.get(j).setMorpheme( true );
                    parts.get(j).setParentId( parts.get(i).getPartName() );
                    parts.get(i).setType( "Copula" );
                }
                else if (affixType.equals("Slot")){
                    int j = parts.size();
                    parts.add(new Part(((Slot)affix).getIdentification()));
                    parts.get(j).setLabel( ((Slot)affix).getLabel() );
                    parts.get(j).setValue( ((Slot)affix).getValue() );
                    parts.get(j).setNextPart( "placeholder" );
                    parts.get(j).setType("Slot");
                    parts.get(j).setMorpheme(true);
                    parts.get(j).setParentId( parts.get(i).getPartName() );
                }
            }
        }
        else if (type.equals("Unimorphic word")){
            int i = parts.size();
            parts.add(new Part(((UnimorphicWord)part).getIdentification()));
            parts.get(i).setValue( ((UnimorphicWord)part).getValue() );
            parts.get(i).setNextPart ("placeholder" );
            parts.get(i).setMorpheme ( false );
            parts.get(i).setType ( "Unimorphic word" );
        }

    }

    public Part getPart(int index){
        if (index >= 0 && index < this.parts.size()){
            return parts.get(index);
        }
        else{
            //System.out.println("Index Out of bounds");
            return null;
        }
    }

    public TemplatePortion getItem(int index){
        if (index >= 0 && index < this.templateWords.size()){
            return templateWords.get(index);
        }
        else{
            //System.out.println("Index Out of bounds");
            return null;
        }
    }

    public int getSize(){
        return this.parts.size();
    }

    public int getTemplateSize(){
        return this.templateWords.size();
    }

    public List<Part> getData(){
        return parts /**Collections.unmodifiableList(templateItems)*/;
    }

    public List<TemplatePortion> getTemplateWords(){
        return templateWords;
    }

}
