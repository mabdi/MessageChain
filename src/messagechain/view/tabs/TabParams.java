package messagechain.view.tabs;

import messagechain.model.Context;
import messagechain.view.abstracts.AbstractTab;

/**
 * Created by User on 4/26/2018.
 */
public class TabParams extends AbstractTab {
    private final Context context;

    public TabParams(Context context) {
        this.context = context;
        initUI();
    }

    protected void initUI() {

    }

    @Override
    public String getTabTitle() {
        return null;
    }
}
