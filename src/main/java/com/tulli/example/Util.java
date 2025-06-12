package com.tulli.example;

import com.google.inject.Injector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Util {

    public static void guice6(Injector injector) {
        if (injector != null) {
            System.out.println("\n\n=== Guice 6 - Servlet URL Mappings ===");
            try {
                // Access the ManagedServletPipeline through reflection
                Object guiceFilter = injector.getInstance(com.google.inject.servlet.GuiceFilter.class);
                Field pipelineField = guiceFilter.getClass().getDeclaredField("pipeline");
                pipelineField.setAccessible(true);
                Object managedFilterPipeline = pipelineField.get(guiceFilter);

                // Get the servletDefinitions from the pipeline
                Field servletDefsField = managedFilterPipeline.getClass().getDeclaredField("servletPipeline");
                servletDefsField.setAccessible(true);
                Object servletDefinitions = servletDefsField.get(managedFilterPipeline);

                Field definitionsField = servletDefinitions.getClass().getDeclaredField("servletDefinitions");
                definitionsField.setAccessible(true);
                Object[] servletDefinitionsList = (Object[]) definitionsField.get(servletDefinitions);

                Arrays.stream(servletDefinitionsList).toList().forEach(f -> {
                    try {
                        Field patternMatcherField = f.getClass().getDeclaredField("patternMatcher");
                        patternMatcherField.setAccessible(true);
                        Object patternMatcher = patternMatcherField.get(f);

                        Method originalPatternMethod = patternMatcher.getClass().getDeclaredMethod("getOriginalPattern");
                        originalPatternMethod.setAccessible(true);
                        String pattern = (String) originalPatternMethod.invoke(patternMatcher);

                        Field servletKeyField = f.getClass().getDeclaredField("servletKey");
                        servletKeyField.setAccessible(true);
                        Object servletKey = servletKeyField.get(f);

                        System.out.println("URL Pattern: " + pattern + " -> " + servletKey);
                    } catch (Exception e) {
                        System.err.println("Error processing servlet definition: " + e.getMessage());
                    }
                });

            } catch (Exception e) {
                System.err.println("Failed to extract Guice servlet mappings: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("\n");
        }
    }

    public static void guice3(Injector injector) {
        if (injector != null) {
            System.out.println("\n\n=== Guice 3 - Servlet URL Mappings ===");
            try {
                // Access the ManagedServletPipeline through reflection
                Object guiceFilter = injector.getInstance(com.google.inject.servlet.GuiceFilter.class);
                Field pipelineField = guiceFilter.getClass().getDeclaredField("pipeline");
                pipelineField.setAccessible(true);
                Object managedFilterPipeline = pipelineField.get(guiceFilter);

                // Get the servletDefinitions from the pipeline
                Field servletDefsField = managedFilterPipeline.getClass().getDeclaredField("servletPipeline");
                servletDefsField.setAccessible(true);
                Object servletDefinitions = servletDefsField.get(managedFilterPipeline);

                Field definitionsField = servletDefinitions.getClass().getDeclaredField("servletDefinitions");
                definitionsField.setAccessible(true);
                Object[] servletDefinitionsList = (Object[]) definitionsField.get(servletDefinitions);

                Arrays.stream(servletDefinitionsList).toList().forEach(f -> {
                    try {
                        Field patternMatcherField = f.getClass().getDeclaredField("patternMatcher");
                        patternMatcherField.setAccessible(true);
                        Object patternMatcher = patternMatcherField.get(f);

                        Field patternField = patternMatcher.getClass().getDeclaredField("pattern");
                        patternField.setAccessible(true);
                        String pattern = (String) patternField.get(patternMatcher);

                        Field servletKeyField = f.getClass().getDeclaredField("servletKey");
                        servletKeyField.setAccessible(true);
                        Object servletKey = servletKeyField.get(f);

                        System.out.println("URL Pattern: " + pattern + " -> " + servletKey);
                    } catch (Exception e) {
                        System.err.println("Error processing servlet definition: " + e.getMessage());
                    }
                });

            } catch (Exception e) {
                System.err.println("Failed to extract Guice servlet mappings: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("\n");
        }
    }
}
