/*
 * @(#) PolymorphicWord.java   1.0   Nov 11, 2021
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

public class PolymorphicWord extends TemplatePart{
    private String reliesOn;
    private String firstPart;
    private String lastPart;
    private String nextPart;

    public PolymorphicWord(String partName, String reliesOn, String firstPart, String lastPart, String nextPart) {
        super(partName);
        this.reliesOn = reliesOn;
        this.firstPart = firstPart;
        this.lastPart = lastPart;
        this.nextPart = nextPart;
    }

    public String getReliesOn() {
        return reliesOn;
    }

    public void setReliesOn(String reliesOn) {
        this.reliesOn = reliesOn;
    }

    public String getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(String firstPart) {
        this.firstPart = firstPart;
    }

    public String getLastPart() {
        return lastPart;
    }

    public void setLastPart(String lastPart) {
        this.lastPart = lastPart;
    }

    public String getNextPart() {
        return nextPart;
    }

    public void setNextPart(String nextPart) {
        this.nextPart = nextPart;
    }
}
