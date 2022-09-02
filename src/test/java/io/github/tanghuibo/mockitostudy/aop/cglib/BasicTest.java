package io.github.tanghuibo.mockitostudy.aop.cglib;

import io.github.tanghuibo.mockitostudy.aop.BasicBean;
import org.junit.jupiter.api.Test;
import org.objenesis.ObjenesisHelper;
import org.springframework.cglib.core.DebuggingClassWriter;
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

    @Test
    public void test() {
        // 存放动态生成的 class
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "d:\\class");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BasicBean.class);
        MethodInterceptor callBack = (o, method, objects, methodProxy) -> {
            objects[0] = "big boss " + objects[0];
            Object result = methodProxy.invokeSuper(o, objects);
            return result + "， 我是 cglib";
        };
        enhancer.setCallbackType(MethodInterceptor.class);
        Class<BasicBean> clazz = enhancer.createClass();
        BasicBean basicBean = ObjenesisHelper.newInstance(clazz);
        ((Factory) basicBean).setCallbacks(new Callback[] {callBack});
        assertThat(basicBean.sayHello("Bob"), equalTo("hello big boss Bob， 我是 cglib"));

    }
}
