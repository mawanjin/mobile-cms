package com.joinsoft.online.signin.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * xingsen@join-cn.com
 */
@WebService(targetNamespace = "http://soap.sso.vjsp.cn/")
public interface SsoWsService {
    @WebMethod(operationName = "userExist")
    public String userExist(@WebParam(name = "data") String data);

    @WebMethod(operationName = "userRegMW")
    public String userReg(@WebParam(name = "data") String data);


    @WebMethod(operationName = "regCode")
    public String regCode(@WebParam(name = "data") String data);

    @WebMethod(operationName = "restPwd")
    public String restPwd(@WebParam(name = "data") String data);

    @WebMethod(operationName = "userLoginMW")
    public String userLoginMW(@WebParam(name = "data") String data);
}
