package messagechain.model;

import messagechain.view.abstracts.AbstractTab;
import messagechain.view.tabs.TabContext;

/**
 * Created by User on 4/26/2018.
 */
public class Context {
    private String name;

    public AbstractTab getUI() {
        return new TabContext(this);
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
