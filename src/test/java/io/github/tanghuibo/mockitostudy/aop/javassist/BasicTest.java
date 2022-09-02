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
        CtClass cc = pool.get("io.github.tanghuibo.mockitostudy.aop.BasicBean");
        CtMethod sayHello = cc.getMethod("sayHello", "(Ljava/lang/String;)Ljava/lang/String;");
        sayHello.insertBefore("$1 = \"big boss \" + $1;");
        sayHello.insertAfter("$_ = $_ + \"， 我是 javassist\";");
        cc.toClass();
        BasicBean basicBean = new BasicBean();
        assertThat(basicBean.sayHello("Bob"), equalTo("hello big boss Bob， 我是 javassist"));
    }


    @Test
    public void newProxyClass() throws CannotCompileException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("io.github.tanghuibo.mockitostudy.aop.BasicBean$proxy");
        cc.setSuperclass(pool.getCtClass("io.github.tanghuibo.mockitostudy.aop.BasicBean"));
        CtMethod sayHello =  CtMethod.make("public String sayHello(String name) {\n" +
            "    return super.sayHello(\"big boss \" + name) + \"， 我是 javassist\";\n" +
            "}", cc);
        cc.addMethod(sayHello);
        Class<?> aClass = cc.toClass();
        BasicBean basicBean = (BasicBean)ObjenesisHelper.newInstance(aClass);
        assertThat(basicBean.sayHello("Bob"), equalTo("hello big boss Bob， 我是 javassist"));
    }
}
