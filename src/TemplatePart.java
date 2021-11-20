/*
 * @(#) TemplatePart.java   1.0   Nov 11, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */
public class TemplatePart {
    private String partName;

    public TemplatePart(String partName) {
        this.partName = partName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
