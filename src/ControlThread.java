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

    /**
     * Template part fields that can be modified/set by user
     */
    private String partName;
    private boolean isPartNameChanged;
    private String label;
    private boolean isLabelChanged;
    private String type;
    private boolean isTypeChanged;
    private String value;
    private boolean isValueChanged;
    private String reliesOn;
    private boolean isRelianceChanged;
    private String firstPart;
    private boolean isFirstPartChanged;
    private String lastPart;
    private boolean isLastPartChanged;
    private String nextPart;
    private boolean isNextPartChanged;

    public ControlThread(DataModel dataModel) {
        this.dataModel = dataModel;
        index = -1;

        this.isPartNameChanged = false;
        this.isLabelChanged = false;
        this.isTypeChanged = false;
        this.isValueChanged = false;
        this.isRelianceChanged = false;
        this.isFirstPartChanged = false;
        this.isLastPartChanged = false;
        this.isNextPartChanged = false;
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

    public void setPartName(String partName) {
        this.partName = partName;
        this.isPartNameChanged = true;
    }

    public void setLabel(String label) {
        this.label = label;
        this.isLabelChanged = true;
    }

    public void setType(String type) {
        this.type = type;
        this.isTypeChanged = true;
    }

    public void setValue(String value) {
        this.value = value;
        this.isValueChanged = true;
    }

    public void setReliesOn(String reliesOn) {
        this.reliesOn = reliesOn;
        this.isRelianceChanged = true;
    }

    public void setFirstPart(String firstPart) {
        this.firstPart = firstPart;
        this.isFirstPartChanged = true;
    }

    public void setLastPart(String lastPart) {
        this.lastPart = lastPart;
        this.isLastPartChanged = true;
    }

    public void setNextPart(String nextPart) {
        this.nextPart = nextPart;
        this.isNextPartChanged = true;
    }

    @Override
    public void run() {
        while (true){
            if (isPartNameChanged){
                int index = ToCTeditor.gui.getIndex();
                dataModel.getPart(index).setPartName(partName);
                isPartNameChanged = false;
                System.out.println("part name changed to:" + partName );
            }
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
