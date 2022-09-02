package io.github.tanghuibo.mockitostudy.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * CaptorUseTest
 *
 * @author tanghuibo
 * @date 2022/9/2 14:10
 */
public class CaptorUseTest {

    @Captor
    ArgumentCaptor<Integer> argumentCaptor;

    static Logger log = LoggerFactory.getLogger(CaptorUseTest.class);

    @Test
    public void test() {
        openMocks(this);
        CaptorTestBean captorTestBean = new CaptorTestBean();
        CaptorTestBean spy = spy(captorTestBean);
        spy.test(11);
        //innerObj.get 被调用过一次，参数为 1
        verify(spy, times(1)).test(argumentCaptor.capture());
        log.info("arg is {}", argumentCaptor.getValue());


    }

    public static class CaptorTestBean {

        public void test(Integer item) {
            log.info("item: {}", item);
        }
    }
}
