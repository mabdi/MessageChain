package messagechain.model;

import burp.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/26/2018.
 */
public class Hyper_Request {
    private IRequestInfo analyzedRequest;
    private IHttpService httpService;
    private IResponseInfo analyzedResponse;
    private List<ResponseOut> outputParams;
    private byte[] request_bytes;
    private byte[] response_bytes;
    private String host;
    private int port;
    private String protocol;

    public Hyper_Request(byte[] request_bytes, byte[] response_bytes, String host, int port, String protocol) {
        this.request_bytes = request_bytes;
        this.response_bytes = response_bytes;
        this.host = host;
        this.outputParams = new ArrayList<>();
        this.port = port;
        this.protocol = protocol;
        httpService = BurpExtender.getHelpers().buildHttpService(host,port,protocol);
        analyzedRequest = BurpExtender.getHelpers().analyzeRequest(httpService,request_bytes);
        analyzedResponse = BurpExtender.getHelpers().analyzeResponse(request_bytes);
    }

    @Override
    public String toString() {
        return analyzedRequest.getMethod() + "  " + analyzedRequest.getUrl().getFile();
    }

    public List<ResponseOut> getOutputParams() {
        return outputParams;
    }

    public void addOutputParam(ResponseOut output){
        outputParams.add(output);
//        output.setFlow_request(this);
    }

    public IRequestInfo getAnalyzedRequest() {
        return analyzedRequest;
    }

    public void setAnalyzedRequest(IRequestInfo analyzedRequest) {
        this.analyzedRequest = analyzedRequest;
    }

    public IHttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(IHttpService httpService) {
        this.httpService = httpService;
    }

    public IResponseInfo getAnalyzedResponse() {
        return analyzedResponse;
    }

    public void setAnalyzedResponse(IResponseInfo analyzedResponse) {
        this.analyzedResponse = analyzedResponse;
    }

    public void setOutputParams(List<ResponseOut> outputParams) {
        this.outputParams = outputParams;
    }

    public byte[] getRequest_bytes() {
        return request_bytes;
    }

    public void setRequest_bytes(byte[] request_bytes) {
        this.request_bytes = request_bytes;
    }

    public byte[] getResponse_bytes() {
        return response_bytes;
    }

    public void setResponse_bytes(byte[] response_bytes) {
        this.response_bytes = response_bytes;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
