/*
 * @(#) TemplateItems.java   1.0   Sep 17, 2022
 *
 * Sindiso Mkhatshwa (mkhsin035@myuct.ac.za)
 *
 * Nitschke Laboratory, UCT
 *
 * @(#) $Id$
 */

/**
 * Adapted from: https://coderanch.com/t/343024/java/Components-JComboBox
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CheckCombo{
    public JComboBox/*JPanel*/ getContent(List<String> dataList, List<String> reliesOnList){
        List <String> ids = dataList;
        List<Boolean> vals = new ArrayList<>();
        if (reliesOnList.size() > 0) {
            for (int i = 0; i < ids.size(); i++) {
                if (reliesOnList.contains((String) ids.get(i))) {
                    vals.add(Boolean.TRUE);
                } else {
                    vals.add(Boolean.FALSE);
                }
            }
        }
        else{
            for (int i = 0; i < ids.size(); i++) {
                if (i == 0)
                    vals.add(Boolean.TRUE);
                else
                    vals.add(Boolean.FALSE);
            }
        }

        CheckComboStore[] stores = new CheckComboStore[ids.size()];
        for(int j = 0; j < ids.size(); j++)
            stores[j] = new CheckComboStore(ids.get(j), vals.get(j));
        JComboBox combo = new JComboBox(stores);
        combo.setRenderer(new CheckComboRenderer());
        combo.setMinimumSize(new Dimension(225, 30));
        combo.setMaximumSize(new Dimension(225, 30));
        JPanel panel = new JPanel();
        panel.add(combo);
        return combo;
    }
}
class CheckComboRenderer implements ListCellRenderer
{
    JCheckBox checkBox;

    public CheckComboRenderer()
    {
        checkBox = new JCheckBox();
    }
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus)
    {
        CheckComboStore store = (CheckComboStore)value;
        checkBox.setText(store.id);
        checkBox.setSelected(((Boolean)store.state).booleanValue());
        checkBox.setBackground(isSelected ? Color.red : Color.white);
        checkBox.setForeground(isSelected ? Color.white : Color.black);
        return checkBox;
    }
}

class CheckComboStore
{
    String id;
    Boolean state;

    public CheckComboStore(String id, Boolean state)
    {
        this.id = id;
        this.state = state;
    }
    public String getId(){return id;}
    public Boolean getState(){return state;}

}