package io.github.tanghuibo.mockitostudy.powermock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.powermock.api.mockito.PowerMockito.*;

/**
 * MockPrivateTest
 *
 * @author tanghuibo
 * @date 2022/8/31 20:47
 */
public class MockPrivateTest {

    @Test
    @PrepareForTest(PrivateMockObj.class )
    public void test() throws Exception {
        PrivateMockObj privateMockObj = mock(PrivateMockObj.class);
        when(privateMockObj, "getV2").thenReturn(3);
        when(privateMockObj, "getV1").thenCallRealMethod();
        Assertions.assertEquals(privateMockObj.getV1(), 4);
    }

    public static class PrivateMockObj {

        public Integer getV1() {
            Integer v2 = getV2();
            return v2 + 1;
        }

        private Integer getV2() {
            return 2;
        }

    }
}
