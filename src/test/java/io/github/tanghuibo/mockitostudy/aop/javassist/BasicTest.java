package io.github.tanghuibo.mockitostudy.aop.javassist;

import io.github.tanghuibo.mockitostudy.aop.BasicBean;
import javassist.*;
import org.junit.jupiter.api.Test;
import org.objenesis.ObjenesisHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * BasicTest
 *
 * @author tanghuibo
 * @date 2022/9/2 17:56
 */
public class BasicTest {

    @Test
    public void changeProto() throws CannotCompileException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        // 设置类名
        CtClass cc = pool.makeClass("io.github.tanghuibo.mockitostudy.aop.BasicBean$proxy");
        // 设置父类
        cc.setSuperclass(pool.getCtClass("io.github.tanghuibo.mockitostudy.aop.BasicBean"));
        // 通过 getMethod 实际拿到的是父类的 CtMethod
        CtMethod sayHello = cc.getMethod("sayHello", "(Ljava/lang/String;)Ljava/lang/String;");
        // 对父类的 CtMethod 修改会影响到父类
        sayHello.insertBefore("$1 = $1.toUpperCase();");
        sayHello.insertAfter("$_ = $_ + \"; 欢迎使用 javassist\";");
        pool.getCtClass("io.github.tanghuibo.mockitostudy.aop.BasicBean").toClass();
        BasicBean realBean = new BasicBean();
        assertThat(realBean.sayHello("thb"), equalTo("hello THB; 欢迎使用 javassist"));
    }


    @Test
    public void newProxyClass() throws Exception {
        BasicBean proxyBean = aop(new BasicBean());
        assertThat(proxyBean.sayHello("thb"), equalTo("hello THB; 欢迎使用 javassist"));
    }

    private static BasicBean aop(BasicBean realBean) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        // 设置类名
        CtClass cc = pool.makeClass("io.github.tanghuibo.mockitostudy.aop.BasicBean$proxy");
        // 设置父类
        cc.setSuperclass(pool.getCtClass("io.github.tanghuibo.mockitostudy.aop.BasicBean"));
        // 设置代理字段
        cc.addField(CtField.make("public io.github.tanghuibo.mockitostudy.aop.BasicBean targetBean$1;", cc));
        // 设置方法
        CtMethod sayHello =  CtMethod.make("public String sayHello(String name) {\n" +
            "    return targetBean$1.sayHello(name.toUpperCase()) + \"; 欢迎使用 javassist\";\n" +
            "}", cc);
        cc.addMethod(sayHello);
        BasicBean proxyBean = (BasicBean)ObjenesisHelper.newInstance(cc.toClass());
        // 注入代理类
        proxyBean.getClass().getField("targetBean$1").set(proxyBean, realBean);
        return proxyBean;
    }
}
