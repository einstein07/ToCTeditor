/*
 * @(#) ControllerThread.java   1.0   Nov 18, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

public class ControlThread extends Thread {
    private DataModel dataModel;
    private TemplatePart prevPart;
    private TemplatePart currentPart;
    private int index;

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
            /**if (currentPart != null){
                if (!currentPart.equals(prevPart)) {
                    currentPart = dataModel.getPart(index);
                    prevPart = currentPart;
                }
            }*/
            /**if ( dataModel.getSize() > 0 ){
                currentPart = dataModel.getPart(index);
                if (prevPart != null && currentPart != null)  {
                    if (!currentPart.equals(prevPart)) {*/
                        currentPart = dataModel.getPart(index);
                        ToCTeditor.gui.setCallTemplateItems(true);
                /**        prevPart = currentPart;
                    }
                }
            }*/
        }
    }
}
