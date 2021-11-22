/*
 * @(#) Slot.java   1.0   Nov 11, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
public class Slot extends TemplatePart{

    private String label;
    private String nextPart;

    Slot(String partName, String label, String nextPart){
        super(partName);
        this.label = label;
        this.nextPart = nextPart;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNextPart() {
        return nextPart;
    }

    public void setNextPart(String nextPart) {
        this.nextPart = nextPart;
    }

    public String getTurtle(){
        return "<" + getPartName() + "> a toct:Slot\n" +
                "    ; toct:hasLabel \"" + getLabel() + "\"^^xsd:string\n" +
                "    ; toct:hasNextPart " + getNextPart() +" .";
    }
}
