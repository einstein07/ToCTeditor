/*
 * @(#) DataModel.java   1.0   Nov 18, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataModel {
    private List<TemplatePart> templateItems;
    public DataModel(){
        templateItems = new ArrayList<>();
    }
    public void addPart(TemplatePart part){
        templateItems.add(part);
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
    public int getSize(){
        return this.templateItems.size();
    }

    public List<TemplatePart> getData(){
        return templateItems /**Collections.unmodifiableList(templateItems)*/;
    }

}
