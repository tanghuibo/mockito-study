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
        List<String> list = Arrays.asList("<a", "[b", "class", "/d");
        List<String> result = list.stream().filter(name -> {
            try {
                checkFieldName(name);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());
        log.info("result: {}", result);
    }

    private void checkFieldName(String fieldName) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        String value = "thbTest";
        Class clazz = new ByteBuddy()
                .subclass(Object.class)
                .name("ThbTest")
                .defineField(fieldName, String.class, Modifier.PUBLIC)
                .make()
                .load(BasicTest.class.getClassLoader()).getLoaded();
        Object o = clazz.newInstance();
        Field field = clazz.getField(fieldName);
        field.set(o, value);
        assertThat(field.get(o), equalTo(value));
        log.info("{}: getResult: {}", fieldName, field.get(o));
    }
}
