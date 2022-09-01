package io.github.tanghuibo.mockitostudy.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class BServiceTest {

    Logger log = LoggerFactory.getLogger(BServiceTest.class);

    @Resource
    BService bService;

    @Test
    public void aDataIncrement() {
        Integer oldValue = bService.showAData();
        log.info("oldValue: {}", oldValue);
        bService.aDataIncrement();
        assertThat(bService.showAData(), equalTo(oldValue + 1));
    }
}