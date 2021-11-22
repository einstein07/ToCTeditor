/*
 * @(#) UnimorphicWord.java   1.0   Nov 11, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
public class UnimorphicWord extends TemplatePart{

    private String value;
    private String nextPart;

    public UnimorphicWord(String partName, String value, String nextPart) {
        super(partName);
        this.value = value;
        this.nextPart = nextPart;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNextPart() {
        return nextPart;
    }

    public void setNextPart(String nextPart) {
        this.nextPart = nextPart;
    }

    public String getTurtle(){
        return "<" + getPartName() + "> a toct:UnimorphicWord\n" +
                "    ; toct:hasValue \"" + getValue() + "\"^^xsd:string\n" +
                "    ; toct:hasNextPart <" + getNextPart() + "> ." ;
    }
}
