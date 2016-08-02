package com.iquestgroup.webApp.dispatcher;

import com.iquestgroup.webApp.controllers.AbstractController;

import java.lang.reflect.Method;

/**
 * Class which encapsulates necessary objects to cache a {@link org.springframework.http.HttpRequest}.
 *
 * @author Stefan Pamparau
 */
public class CachedRequest {

    private AbstractController controllerInstance;
    private Method method;

    public CachedRequest(AbstractController controllerInstance, Method method) {
        this.controllerInstance = controllerInstance;
        this.method = method;
    }

    public AbstractController getControllerInstance() {
        return controllerInstance;
    }

    public void setControllerInstance(AbstractController controllerInstance) {
        this.controllerInstance = controllerInstance;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "CachedRequest{" +
                "controllerInstance=" + controllerInstance +
                ", method=" + method +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CachedRequest that = (CachedRequest) o;

        if (controllerInstance != null ? !controllerInstance.equals(that.controllerInstance) : that.controllerInstance != null)
            return false;
        return method != null ? method.equals(that.method) : that.method == null;

    }

    @Override
    public int hashCode() {
        int result = controllerInstance != null ? controllerInstance.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }
}
