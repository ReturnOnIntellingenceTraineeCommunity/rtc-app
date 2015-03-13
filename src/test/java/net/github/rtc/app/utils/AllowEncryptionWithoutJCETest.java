package net.github.rtc.app.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class AllowEncryptionWithoutJCETest {

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
        allowEncryptionWithoutJCE.afterPropertiesSet();
        assertEquals(false, field.get(null));
    }
}
