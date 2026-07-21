package io.quarkus.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusProdModeTest;

/**
 * Verifies that the application name and version captured at build time are reported at run time when no
 * override is provided.
 */
public class ApplicationNameAndVersionDefaultTest {

    @RegisterExtension
    static final QuarkusProdModeTest config = new QuarkusProdModeTest()
            .withEmptyApplication()
            .setApplicationName("default-name")
            .setApplicationVersion("1.0-BAKED")
            .setRun(true);

    @Test
    public void testBuildTimeValuesAreReported() {
        assertThat(config.getStartupConsoleOutput()).contains("default-name 1.0-BAKED");
    }
}
