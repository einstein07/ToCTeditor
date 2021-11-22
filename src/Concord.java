/*
 * @(#) Concord.java   1.0   Nov 11, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */


public class Concord extends TemplatePart{

    private String label;
    private String type;
    private String nextPart;

    public Concord(String partName, String label, String type, String nextPart) {
        super(partName);
        this.label = label;
        this.type = type;
        this.nextPart = nextPart;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNextPart() {
        return nextPart;
    }

    public void setNextPart(String nextPart) {
        this.nextPart = nextPart;
    }

    public String getTurtle(){
        return "<" + getPartName() + "> a toct:Concord\n" +
                "    ; cao:hasConcordType <" + getType() + ">\n" +
                "    ; toct:hasLabel \"" + getLabel() + "\"^^xsd:string\n" +
                "    ; toct:hasNextPart <" + getNextPart() + "> .";
    }
}
