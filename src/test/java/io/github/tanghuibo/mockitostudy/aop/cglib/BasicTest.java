package io.github.tanghuibo.mockitostudy.aop.cglib;

import io.github.tanghuibo.mockitostudy.aop.BasicBean;
import org.junit.jupiter.api.Test;
import org.objenesis.ObjenesisHelper;
import org.springframework.cglib.proxy.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * BasicTest
 *
 * @author tanghuibo
 * @date 2022/9/2 17:30
 */
public class BasicTest {

    BasicBean aop(BasicBean realBean) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BasicBean.class);
        MethodInterceptor callback = (proxyObj, method, objects, methodProxy) -> {
            // 修改入参
            objects[0] = objects[0].toString().toUpperCase();
            // 真实调用，此处使用 fastClass 技术，不走反射
            Object result = methodProxy.invoke(realBean, objects);
            // 修改返回值
            return result + "; 欢迎使用 cgLib";
        };
        enhancer.setCallbackTypes(new Class[] { MethodInterceptor.class });
        // callbackTypes 为多个时使用，设置对象的某个 method 由第几个 callbackType 拦截
        // enhancer.setCallbackFilter(method -> {
        //      // method 为生成字节码时解析产生，运行过程中不使用反射
        //      return 0;
        // });
        // 生成 class，也可通过 create() 直接生成对象
        Class<BasicBean> clazz = enhancer.createClass();
        // 类生成对象
        BasicBean proxyBean = ObjenesisHelper.newInstance(clazz);
        // 生成代理对象后可动态设置 callback
        ((Factory) proxyBean).setCallbacks(new Callback[] { callback });
        return proxyBean;
    }

    @Test
    public void aopTest() {
        BasicBean realBean = new BasicBean();
        BasicBean proxyBean = aop(realBean);
        assertThat(proxyBean.sayHello("thb"), equalTo("hello THB; 欢迎使用 cgLib"));
    }
}
