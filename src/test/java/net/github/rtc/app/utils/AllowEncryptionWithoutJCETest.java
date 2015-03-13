package net.github.rtc.app.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class AllowEncryptionWithoutJCETest {

    private static Logger log = LoggerFactory.getLogger(AllowEncryptionWithoutJCETest.class.getName());

    @InjectMocks
    private AllowEncryptionWithoutJCE allowEncryptionWithoutJCE;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAfterPropertiesSet() throws Exception {
        final Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
        field.setAccessible(true);
        log.debug("BEFORE:" + field.toString());
        System.out.println("BEFORE:" + field.toString());
        log.debug("BEFORE:" + field.get(null).toString());
        System.out.println("BEFORE:" + field.get(null).toString());
        allowEncryptionWithoutJCE.afterPropertiesSet();
        log.debug("AFTER:" + field.get(null).toString());
        System.out.println("AFTER:" + field.get(null).toString());
        assertEquals(false, field.get(null));
    }
}
