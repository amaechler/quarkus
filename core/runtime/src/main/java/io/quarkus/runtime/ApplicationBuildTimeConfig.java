package io.quarkus.runtime;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

/**
 * Application
 */
@ConfigMapping(prefix = "quarkus.application")
@ConfigRoot(phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public interface ApplicationBuildTimeConfig {

    /**
     * The header to use for UI Screen (Swagger UI, GraphQL UI etc).
     */
    @WithDefault("{applicationName} (powered by Quarkus)")
    Optional<String> uiHeader();
}
