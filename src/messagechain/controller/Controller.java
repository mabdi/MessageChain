package messagechain.controller;

import messagechain.model.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/26/2018.
 */
public class Controller {
    List<Context> contexts = new ArrayList<>();
    int contextNums = 1;

    public List<Context> getContexts() {
        return contexts;
    }

    public Context newContext() {
        Context c = new Context();
        contexts.add(c);
        c.setName(String.valueOf(contextNums));
        contextNums++;
        return c;
    }
}
