package io.github.tanghuibo.mockitostudy.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * BasicTest
 *
 * @author tanghuibo
 * @date 2022/8/31 21:12
 */
public class BasicTest {

    @Test
    public void test() throws InstantiationException, IllegalAccessException {

        Class<? extends ByteBuddyTestBean> clazz = new ByteBuddy()
                .subclass(ByteBuddyTestBean.class)
                .method(ElementMatchers.named("get1"))
                .intercept(FixedValue.value(3))
                .make()
                .load(BasicTest.class.getClassLoader()).getLoaded();

        ByteBuddyTestBean byteBuddyTestBean = clazz.newInstance();
        assertThat(byteBuddyTestBean.get1(3), equalTo(3));

    }

    public static class ByteBuddyTestBean {
        public Integer get1(Integer a) {
            return a + 1;
        }
    }
}
