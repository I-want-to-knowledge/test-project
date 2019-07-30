package com.geo.source.rong_chuang;

/**
 * 
 * @program: zjguahao
 * @description:
 * @author: Run the ant
 * @create: 2019年4月23日
 *
 */
public class GetUserAppRequest {

    private String service;

    private String method;

    private String clientUUID;

    private String version;

    private String httpCode;

    private ArgsBo args;

    private String type;

    private String token;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClientUUID() {
        return clientUUID;
    }

    public void setClientUUID(String clientUUID) {
        this.clientUUID = clientUUID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public ArgsBo getArgs() {
        return args;
    }

    public void setArgs(ArgsBo args) {
        this.args = args;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
