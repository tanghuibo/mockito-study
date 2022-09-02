package io.github.tanghuibo.mockitostudy.mockito;

import org.junit.jupiter.api.Test;

import org.mockito.Answers;
import org.mockito.MockedStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.mockStatic;

/**
 * MockStaticTest
 *
 * @author tanghuibo
 * @date 2022/8/31 10:13
 */
public class MockStaticTest {

    static Logger log = LoggerFactory.getLogger(MockStaticTest.class);

    @Test
    public void test() {
        // mock 静态方法
        MockedStatic<MockStaticObj> mockXMockedStatic = mockStatic(MockStaticObj.class);
        // 拦截 x > 5 的场景
        mockXMockedStatic.when(() -> MockStaticObj.get(intThat(a -> a > 5))).thenAnswer(invocationOnMock -> ((Integer) invocationOnMock.getArgument(0)) + 3);
        // 其他场景走默认规则
        mockXMockedStatic.when(() -> MockStaticObj.get(intThat(a -> a <= 5))).thenAnswer(Answers.CALLS_REAL_METHODS);
        assertEquals(MockStaticObj.get(4), 1);
        assertEquals(MockStaticObj.get(11), 14);
    }

    public static class MockStaticObj {
        public static Integer get(Integer x) {
            log.info("MockStaticObj.get({})", x);
            return x - 3;
        }
    }
}
