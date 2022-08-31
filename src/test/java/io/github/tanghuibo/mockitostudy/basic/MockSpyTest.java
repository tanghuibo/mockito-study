package io.github.tanghuibo.mockitostudy.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * MockSpyTest
 *
 * @author tanghuibo
 * @date 2022/8/31 20:10
 */
public class MockSpyTest {

    @Test
    public void test() {

        MockSpyObj mockSpyObj = new MockSpyObj();
        MockSpyObj spy = spy(mockSpyObj);
        // 拦截 get1 方法
        doReturn(11).when(spy).get1();
        Assertions.assertEquals(11, spy.get1());
        Assertions.assertEquals(2, spy.get2());

    }

    public static final class MockSpyObj {
        public Integer get1() {
            return 1;
        }

        public Integer get2() {
            return 2;
        }
    }
}
