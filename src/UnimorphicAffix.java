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
}
