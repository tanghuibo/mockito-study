package io.github.tanghuibo.mockitostudy.springmock;

import io.github.tanghuibo.mockitostudy.service.BService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class OriginalBServiceTest {

    Logger log = LoggerFactory.getLogger(OriginalBServiceTest.class);

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