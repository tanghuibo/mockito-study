package io.github.tanghuibo.mockitostudy.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MockStaticTest
 *
 * @author tanghuibo
 * @date 2022/8/31 10:13
 */
public class MockFinalTest {
    static Logger log = LoggerFactory.getLogger(MockFinalTest.class);

    @Test
    public void test() {
        // 支持对被 final 修饰的类进行 mock
        MockFinalObj mockFinalObj = Mockito.mock(MockFinalObj.class);
        Mockito.when(mockFinalObj.get(Mockito.anyInt())).thenReturn(22);
        MockFinalObj originMockFinalObj = new MockFinalObj();
        Assertions.assertEquals(mockFinalObj.get(12), 22);
        Assertions.assertEquals(originMockFinalObj.get(13), 10);
    }

    public static final class MockFinalObj {
        public Integer get(Integer x) {
            log.info("MockFinalObj.get({})", x);
            return x - 3;
        }
    }
}
