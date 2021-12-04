/*
 * @(#) ControllerThread.java   1.0   Nov 18, 2021
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
import za.co.mahlaza.research.grammarengine.base.models.template.TemplatePortion;
import za.co.mahlaza.research.grammarengine.base.models.template.UnimorphicWord;

import java.util.ArrayList;
import java.util.List;

public class ControlThread extends Thread {
    private DataModel dataModel;
    private TemplatePart prevPart;
    private TemplatePart currentPart;
    private int index;

    private static Part activePart;


    public ControlThread(DataModel dataModel) {
        this.dataModel = dataModel;
        index = -1;
    }
    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }


    public TemplatePart getCurrentPart() {
        return currentPart;
    }




    @Override
    public void run() {
        while (true){
            //System.out.print("Part status: "+ isPartNameChanged);
            //if (parts.size() > 0 && activePart.isPartNameChanged()){

              //  int index = ToCTeditor.gui.getIndex();
                //dataModel.getPart(index).setPartName(partName);
                //((Slot)dataModel.getItem(index)).setIdentification(partName);
              //  activePart.isPartNameChanged = false;
                //System.out.println("part name changed to:" + partName );
            //}
            /**if (currentPart != null){
                if (!currentPart.equals(prevPart)) {
                    currentPart = dataModel.getPart(index);
                    prevPart = currentPart;
                }
            }*/
            /**if ( dataModel.getSize() > 0 ){
                currentPart = dataModel.getPart(index);
                if (prevPart != null && currentPart != null)  {
                    if (!currentPart.equals(prevPart)) {
                        currentPart = dataModel.getPart(index);
                        ToCTeditor.gui.setCallTemplateItems(true);
                        prevPart = currentPart;
                    }
                }
            }*/
        }
    }
}
