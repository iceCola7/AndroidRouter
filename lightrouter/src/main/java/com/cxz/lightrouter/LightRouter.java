package com.cxz.lightrouter;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 轻量级的Android路由组件
 */
public final class LightRouter {

    private Interceptor interceptor;

    public LightRouter(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    /**
     * 动态代理实现获取Service的代理类
     *
     * @param service
     * @param context
     * @param <T>
     * @return
     */
    public <T> T create(final Class<T> service, final Context context) {

        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        IntentWrapper intentWrapper = loadIntentWrapper(context, method, args);
                        Class returnType = method.getReturnType();

                        if (returnType == void.class) {
                            if (interceptor == null || !interceptor.intercept(intentWrapper)) {
                                intentWrapper.start();
                            }
                            return null;
                        } else if (returnType == IntentWrapper.class) {
                            return intentWrapper;
                        }
                        throw new RuntimeException("method return type only support 'void' or 'IntentWrapper'");
                    }
                });
    }

    public IntentWrapper loadIntentWrapper(Context context, Method method, Object... args) {
        return new IntentWrapper.Builder(context, method, args).build();
    }

    public static final class Builder {
        private Interceptor interceptor;

        public Builder interceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        public LightRouter build() {
            return new LightRouter(interceptor);
        }
    }

}
