package com.github.mcruzdev.quarkus.openapi.k6.generator.deployment;

import io.quarkus.bootstrap.prebuild.CodeGenException;
import io.quarkus.deployment.CodeGenContext;
import io.quarkus.deployment.CodeGenProvider;
import org.eclipse.microprofile.config.Config;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class OpenApiK6GenProvider implements CodeGenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiK6GenProvider.class);
    private static final String K6 = "k6";

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
        Path inputDir = context.inputDir();
        Path outputDir = context.outDir();

        try (Stream<Path> pathStream = Files.walk(inputDir)) {
            pathStream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> {
                        CodegenConfigurator configurator = new CodegenConfigurator();
                        configurator
                                .setGeneratorName(K6)
                                .setInputSpec(path.toString())
                                .setOutputDir(outputDir.toString());
                        DefaultGenerator generator = new DefaultGenerator();
                        generator.opts(configurator.toClientOptInput()).generate();
                    });

        } catch (IOException e) {
            throw new CodeGenException(e);
        }

        return false;
    }



    @Override
    public boolean shouldRun(Path sourceDir, Config config) {
        return Files.isDirectory(sourceDir);
    }
}
