package io.github.tanghuibo.mockitostudy.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BasicBean
 *
 * @author tanghuibo
 * @date 2022/9/2 15:31
 */
public class BasicBean {

    static Logger log = LoggerFactory.getLogger(BasicBean.class);

    public String sayHello(String name) {
        log.info("sayHello({})", name);
        return "hello " + name;
    }
}
