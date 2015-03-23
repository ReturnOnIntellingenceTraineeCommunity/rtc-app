package net.github.rtc.app.utils;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class AllowEncryptionWithoutJCETest {


    AllowEncryptionWithoutJCE allowEncryptionWithoutJCE = new AllowEncryptionWithoutJCE();

    @Test
    public void testAfterPropertiesSet() throws Exception {
        final Field field = Class.forName("javax.crypto.JceSecurity").
                getDeclaredField("isRestricted");
        field.setAccessible(true);

        assertEquals(field.get(null), true);

        allowEncryptionWithoutJCE.afterPropertiesSet();
        assertEquals(field.get(null), false);
    }
}