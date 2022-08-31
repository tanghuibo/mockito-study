package io.github.tanghuibo.mockitostudy.basic;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * MockDependOnTest
 *
 * @author tanghuibo
 * @date 2022/8/31 10:56
 */
public class MockDependOnTest {
    static Logger log = LoggerFactory.getLogger(MockDependOnTest.class);

    @Mock
    private InnerObj innerObj;

    @InjectMocks
    private OuterObj outerObj;

    @Test
    public void test() {
        openMocks(this);
        when(innerObj.getData(1)).thenReturn(22);
        assertEquals(outerObj.getData(1), 22);
        assertEquals(new OuterObj().getData(1), 4);
        //innerObj.get 被调用过一次，参数为 1
        verify(innerObj, times(1)).getData(1);

    }

    public static class OuterObj {
        private InnerObj innerObj = new InnerObj();
        public Integer getData(Integer i) {
            log.info("OuterObj.getData({})", i);
            return innerObj.getData(i);
        }
    }

    public static class InnerObj {
        public Integer getData(Integer i) {
            log.info("InnerObj.getData({})", i);
            return 3 + i;
        }
    }
}
