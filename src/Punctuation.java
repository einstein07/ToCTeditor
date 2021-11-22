/*
 * @(#) Punctuation.java   1.0   Nov 11, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
public class Punctuation extends TemplatePart{

    private String value;

    public Punctuation(String partName, String value) {
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
        return "<" + getPartName() + "> a toct:Punctuation\n" +
                "    ; toct:hasValue \"" + getValue() + "^^xsd:string .";
    }
}
