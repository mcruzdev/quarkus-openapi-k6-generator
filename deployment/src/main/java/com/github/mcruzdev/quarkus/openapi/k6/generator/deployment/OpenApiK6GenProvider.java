package com.github.mcruzdev.quarkus.openapi.k6.generator.deployment;

import io.quarkus.bootstrap.prebuild.CodeGenException;
import io.quarkus.deployment.CodeGenContext;
import io.quarkus.deployment.CodeGenProvider;
import org.eclipse.microprofile.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

public class OpenApiK6GenProvider implements CodeGenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiK6GenProvider.class);

    @Override
    public String providerId() {
        return "open-api-k6";
    }

    @Override
    public String inputExtension() {
        return ".js";
    }

    @Override
    public String inputDirectory() {
        return "openapi";
    }

    @Override
    public boolean trigger(CodeGenContext context) throws CodeGenException {

    }

    @Override
    public boolean shouldRun(Path sourceDir, Config config) {
        return Files.isDirectory(sourceDir);
    }
}
