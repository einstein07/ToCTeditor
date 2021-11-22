/*
 * @(#) Root.java   1.0   Nov 11, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

public class Root extends TemplatePart{
    private String value;

    public Root(String partName, String value) {
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
        return "<" + getPartName() + "> a toct:Root\n" +
                "    ; toct:hasValue \"" + getValue() + "\"^^xsd:string .";
    }
}
