package io.github.tanghuibo.mockitostudy.aop.bytebuddy;

import net.bytebuddy.ByteBuddy;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FieldNameCheckTest {

    Logger log = LoggerFactory.getLogger(FieldNameCheckTest.class);

    @Test
    public void fieldValueTest() {
        List<String> list = Arrays.asList("!a", "[b", "class", "/d");
        List<String> result = list.stream().filter(fieldName -> {
            try {
                Object o = buildObjWithFieldName(fieldName);
                String testValue = "thbTest";
                Field field = o.getClass().getField(fieldName);
                assertThat(field.get(o), equalTo(testValue));
                log.info("{}: getResult: {}", fieldName, field.get(o));
                field.set(o, testValue);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());
        log.info("result: {}", result);
    }

    private Object buildObjWithFieldName(String fieldName) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class clazz = new ByteBuddy()
                .subclass(Object.class)
                .name("ThbTest")
                .defineField(fieldName, String.class, Modifier.PUBLIC)
                .make()
                .load(BasicTest.class.getClassLoader()).getLoaded();
        return clazz.newInstance();
    }
}
