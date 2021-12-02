/*
 * @(#) DataModel.java   1.0   Nov 18, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

import za.co.mahlaza.research.grammarengine.base.models.template.TemplatePortion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataModel {
    private List<TemplatePart> templateItems;
    private List<TemplatePortion> templateWords;
    public DataModel(){
        templateItems = new ArrayList<>();
        templateWords = new ArrayList<>();
    }
    public void addPart(TemplatePart part){
        templateItems.add(part);
    }

    public void addPart(TemplatePortion part){
        templateWords.add(part);
    }

    public TemplatePart getPart(int index){
        if (index >= 0 && index < this.templateItems.size()){
            return templateItems.get(index);
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
        return this.templateItems.size();
    }

    public int getTemplateSize(){
        return this.templateWords.size();
    }

    public List<TemplatePart> getData(){
        return templateItems /**Collections.unmodifiableList(templateItems)*/;
    }

    public List<TemplatePortion> getTemplateWords(){
        return templateWords;
    }

}
