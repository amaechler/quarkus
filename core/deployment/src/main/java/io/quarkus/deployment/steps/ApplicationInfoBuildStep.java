package io.quarkus.deployment.steps;

import java.util.Optional;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.ApplicationInfoBuildItem;
import io.quarkus.deployment.builditem.RunTimeConfigurationDefaultBuildItem;

public class ApplicationInfoBuildStep {

    @BuildStep
    public ApplicationInfoBuildItem create(BuildProducer<RunTimeConfigurationDefaultBuildItem> runTimeDefaults) {
        // quarkus.application.name and quarkus.application.version are run time properties, so they cannot be
        // read through the ApplicationConfig mapping here; read the values the build system passed in directly
        Config config = ConfigProvider.getConfig();
        Optional<String> name = config.getOptionalValue("quarkus.application.name", String.class);
        Optional<String> version = config.getOptionalValue("quarkus.application.version", String.class);
        // the build system properties carrying these values are not available at run time, so record them as
        // run time defaults; they can be overridden with a system property or an environment variable
        name.ifPresent(value -> runTimeDefaults
                .produce(new RunTimeConfigurationDefaultBuildItem("quarkus.application.name", value)));
        version.ifPresent(value -> runTimeDefaults
                .produce(new RunTimeConfigurationDefaultBuildItem("quarkus.application.version", value)));
        return new ApplicationInfoBuildItem(name, version);
    }
}
