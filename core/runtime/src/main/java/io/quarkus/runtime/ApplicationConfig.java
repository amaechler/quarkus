package io.quarkus.runtime;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;

/**
 * Application
 */
@ConfigMapping(prefix = "quarkus.application")
@ConfigRoot(phase = ConfigPhase.RUN_TIME)
public interface ApplicationConfig {

    /**
     * The name of the application.
     * If not set, defaults to the name of the project (except for tests where it is not set at all).
     */
    Optional<String> name();

    /**
     * The version of the application.
     * If not set, defaults to the version of the project (except for tests where it is not set at all).
     */
    Optional<String> version();
}
