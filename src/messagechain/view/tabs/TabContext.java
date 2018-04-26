package messagechain.view.tabs;

import messagechain.model.Context;
import messagechain.view.abstracts.AbstractTab;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 4/26/2018.
 */
public class TabContext extends AbstractTab {

    private final Context context;
    private JTabbedPane tabsPane;

    public TabContext(Context context) {
        this.context = context;
    }

    @Override
    protected void initUI() {
        setLayout(new BorderLayout());
        tabsPane = new JTabbedPane();
        tabsPane.addTab("Messages",new TabMessages());
        tabsPane.addTab("Filters",new TabFilter());
        tabsPane.addTab("Params",new TabParams());
        add(tabsPane,BorderLayout.CENTER);
    }

    @Override
    public String getTabTitle() {
        return context.getName();
    }
}
