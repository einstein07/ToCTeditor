/*
 * @(#) UnimorphicAffix.java   1.0   Nov 11, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
public class UnimorphicAffix extends TemplatePart{

    private String value;
    private String nextPart;

    public String getNextPart() {
        return nextPart;
    }

    public void setNextPart(String nextPart) {
        this.nextPart = nextPart;
    }

    public UnimorphicAffix(String partName, String value) {
        super(partName);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTurtle(){
        return "<" + getPartName() + "> a toct:UnimorphicAffix\n" +
                "    ; toct:hasValue \"" + getValue() + "\"^^xsd:string\n" +
                "    ; toct:hasNextPart <" + getNextPart() + "> .";
    }
}
