package io.github.tanghuibo.mockitostudy.runtime.bytebuddy;

import io.github.tanghuibo.mockitostudy.runtime.BasicBean;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.*;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import org.objenesis.ObjenesisHelper;


import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * BasicTest
 *
 * @author tanghuibo
 * @date 2022/9/2 15:34
 */
public class BasicTest {

    @Test
    public void fixedValueTest() {

        Class<? extends BasicBean> clazz = new ByteBuddy()
                .subclass(BasicBean.class)
                .method(ElementMatchers.named("sayHello"))
                .intercept(FixedValue.value("你好， 我是 bytebuddy"))
                .make()
                .load(BasicTest.class.getClassLoader()).getLoaded();

        BasicBean basicBean = ObjenesisHelper.newInstance(clazz);
        assertThat(basicBean.sayHello("Bob"), equalTo("你好， 我是 bytebuddy"));

    }

    @Test
    public void methodDelegationTest() {
        MethodDelegationTestBean bean = new MethodDelegationTestBean();
        Class<? extends BasicBean> clazz = new ByteBuddy()
                .subclass(BasicBean.class)
                .method(ElementMatchers.named("sayHello"))
                //或者静态方法的class
                .intercept(MethodDelegation.to(bean))
                .make()
                .load(BasicTest.class.getClassLoader()).getLoaded();

        BasicBean basicBean = ObjenesisHelper.newInstance(clazz);
        assertThat(basicBean.sayHello("Bob"), equalTo("你好 Bob， 我是 bytebuddy"));
    }


    @Test
    public void methodDelegationInterceptorTest() {
        Class<? extends BasicBean> clazz = new ByteBuddy()
                .subclass(BasicBean.class)
                .method(ElementMatchers.named("sayHello"))
                //或者静态方法的class
                .intercept(MethodDelegation.to(MethodInterceptor.class))
                .make()
                .load(BasicTest.class.getClassLoader()).getLoaded();

        BasicBean basicBean = ObjenesisHelper.newInstance(clazz);
        assertThat(basicBean.sayHello("Bob"), equalTo("hello big boss Bob， 我是 bytebuddy"));
    }

    public static class MethodDelegationTestBean {
        public String sayHello(String name) {
            return "你好 " + name + "， 我是 bytebuddy";
        }
    }


    public static class MethodInterceptor {

        @RuntimeType
        public static Object around(@Argument(0) String arg0, @This Object o, @SuperMethod Method method) throws Exception {
            return method.invoke(o, "big boss " + arg0) + "， 我是 bytebuddy";
        }
    }
}
