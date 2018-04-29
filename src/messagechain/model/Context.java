package messagechain.model;

import burp.BurpExtender;
import burp.IHttpRequestResponse;
import messagechain.view.abstracts.AbstractTab;
import messagechain.view.tabs.TabContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/26/2018.
 */
public class Context {
    private String name;
    private List<Hyper_Request> requests = new ArrayList<>();
    private List<ContextRequestChangeListener> listeners = new ArrayList<>();
    public AbstractTab getUI() {
        return new TabContext(this);
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }


    public void addRequestChangeListener(ContextRequestChangeListener listener){
        this.listeners.add(listener);
    }

    public void addMessages(IHttpRequestResponse[] selectedMessages) {
        for (IHttpRequestResponse reqres : selectedMessages) {
            byte[] request_bytes = reqres.getRequest();
            byte[] response_bytes = reqres.getResponse();
            String host = reqres.getHttpService().getHost();
            int port = reqres.getHttpService().getPort();
            String protocol = reqres.getHttpService().getProtocol();
            Hyper_Request req = new Hyper_Request(request_bytes,response_bytes,host,port,protocol);
            requests.add(req);
        }
        fireRequestsIsUpdated();
    }

    private void fireRequestsIsUpdated() {
        for (ContextRequestChangeListener listener : listeners) {
            listener.requestsIsUpdated(this, requests);
        }
    }

    public interface ContextRequestChangeListener{
        void requestsIsUpdated(Context context, List<Hyper_Request> requests);
    }
}
