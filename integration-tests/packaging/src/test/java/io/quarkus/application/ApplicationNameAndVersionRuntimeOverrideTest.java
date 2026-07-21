package io.quarkus.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusProdModeTest;

/**
 * Verifies that quarkus.application.name and quarkus.application.version can be overridden at run time,
 * with the values captured at build time acting only as defaults.
 */
public class ApplicationNameAndVersionRuntimeOverrideTest {

    @RegisterExtension
    static final QuarkusProdModeTest config = new QuarkusProdModeTest()
            .withEmptyApplication()
            .setApplicationName("build-name")
            .setApplicationVersion("1.0-BAKED")
            .setRuntimeProperties(Map.of(
                    "quarkus.application.name", "runtime-name",
                    "quarkus.application.version", "9.9.9"))
            .setRun(true);

    @Test
    public void testRuntimeOverrideIsReported() {
        assertThat(config.getStartupConsoleOutput()).contains("runtime-name 9.9.9");
        assertThat(config.getStartupConsoleOutput()).doesNotContain("1.0-BAKED");
    }
}
