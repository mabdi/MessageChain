package messagechain.view;

import burp.BurpExtender;
import burp.IContextMenuFactory;
import burp.IContextMenuInvocation;
import burp.ITab;
import messagechain.view.abstracts.AbstractTab;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.*;
import java.util.List;

/**
 * Created by admin on 07/29/2017.
 */
public class View implements ITab, IContextMenuFactory {
    private JTabbedPane tabsPane;

    public View(){
        tabsPane = new JTabbedPane();
        tabsPane.addTab("...",new JLabel("Goat"));
        tabsPane.getModel().addChangeListener(new ChangeListener() {
            // from https://stackoverflow.com/questions/20229682/add-tab-in-jtabbedpane-like-google-chrome
            private boolean ignore = false;

            @Override
            public void stateChanged(ChangeEvent e) {
                if (!ignore) {
                    ignore = true;
                    try {
                        if ("...".equals(tabsPane.getTitleAt(tabsPane.getSelectedIndex()))) {
                            BurpExtender.newContext();
                            tabsPane.setSelectedIndex(tabsPane.getTabCount()-2);
                        }
                    } finally {
                        ignore = false;
                    }
                }
            }
        });

    }

    public void addUI(AbstractTab tab) {
        // from https://stackoverflow.com/questions/11553112/how-to-add-close-button-to-a-jtabbedpane-tab
        int index = tabsPane.getTabCount()-1;
        final String title = tab.getTabTitle();
        tabsPane.insertTab(title,null, tab,null,index);
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        JLabel lblTitle = new JLabel(title);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        JLabel btnClose = new JLabel("x");
        btnClose.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tabsPane.indexOfTab(title);
                if(index>=0){
                    tabsPane.removeTabAt(index);
                    if(tabsPane.getSelectedIndex() == tabsPane.getTabCount() -1) {
                        tabsPane.setSelectedIndex(tabsPane.getTabCount() - 2);
                    }
                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(lblTitle, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(btnClose, gbc);
        pnlTab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlTab.setBackground(Color.blue);
        tabsPane.setTabComponentAt(index, pnlTab);
    }

    @Override
    public String getTabCaption() {
        return BurpExtender.getExtensionName();
    }

    @Override
    public Component getUiComponent() {
        return tabsPane;
    }

    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
        JMenu jmi = new JMenu("Send to Message Chain");
        JMenuItem jmi1 = new JMenuItem("1");
        JMenuItem jmi2 = new JMenuItem("Add to New");
        jmi.add(jmi1);
        jmi.add(jmi2);
        List<JMenuItem> menuItems = new ArrayList<>();
        menuItems.add(jmi);
        return menuItems;
    }
}
