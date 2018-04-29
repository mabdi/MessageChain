package burp;

import messagechain.controller.Controller;
import messagechain.model.Context;
import messagechain.view.View;

import javax.swing.*;
import java.io.PrintWriter;

public class BurpExtender implements IBurpExtender {
    private static final String EXTENSION_NAME = "Message Chain";
    private static BurpExtender instance = null;
    private static IBurpExtenderCallbacks callbacks;
    private static IExtensionHelpers helpers;
    private static View view;
    private static Controller controller;
    private static PrintWriter stdout;

    public static View getView() {
        return view;
    }

    public static Controller getController() {
        return controller;
    }

    public static Context newContext(){
        Context context = controller.newContext();
        view.addUI(context.getUI());
        return context;
    }

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        instance = this;
        this.callbacks = callbacks;
        helpers = callbacks.getHelpers();
        stdout = new PrintWriter(callbacks.getStdout(),true);
        callbacks.setExtensionName(EXTENSION_NAME);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                controller = new Controller();
                view = new View();
                newContext(); // default context
                // TODO why 2 tabs are opening? :-/
                callbacks.addSuiteTab(view);
                callbacks.registerContextMenuFactory(view);
            }
        });

        stdout.println("Plugin registerd "+EXTENSION_NAME);
    }

    public static PrintWriter getStdout() {
        return stdout;
    }

    public static IExtensionHelpers getHelpers() {
        return helpers;
    }

    public static IBurpExtenderCallbacks getCallbacks() {
        return callbacks;
    }

    public static String getExtensionName() {
        return EXTENSION_NAME;
    }

    public static BurpExtender getInstance() {
        return instance;
    }

    public static void logText(String s){
        getInstance().getStdout().println(s);
    }
}
