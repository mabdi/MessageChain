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
        initUI();
    }

    protected void initUI() {
        setLayout(new BorderLayout());
        tabsPane = new JTabbedPane();
        TabMessages tabMessages = new TabMessages(context);
        TabFilter tabFilter = new TabFilter(context);
        TabParams tabParams = new TabParams(context);

        tabsPane.addTab("Messages",tabMessages);
        tabsPane.addTab("Filters", tabFilter);
        tabsPane.addTab("Params",tabParams);
        add(tabsPane,BorderLayout.CENTER);
    }

    @Override
    public String getTabTitle() {
        return context.getName();
    }
}
