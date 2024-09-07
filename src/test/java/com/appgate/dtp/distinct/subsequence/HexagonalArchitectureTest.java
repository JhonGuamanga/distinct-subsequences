package com.appgate.dtp.distinct.subsequence;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * Tests scenarios related to the defined architecture (hexagonal architecture).
 * Within these scenarios some architecture rules are going be tested, for instance,
 * here the dependency inversion should be tested in order that the core layers like
 * domain or application doesn't depend on infrastructure layers like adapters
 */
@DisplayName("Hexagonal Architecture")
class HexagonalArchitectureTest {

    static final String DOMAIN = "domain";
    static final String APPLICATION = "application";
    static final String INPUT_ADAPTERS = "input adapters";
    static final JavaClasses classes = new ClassFileImporter().importPackages("com.appgate.dtp.distinct.subsequence");

    @Nested
    @DisplayName("Application Layer")
    class ApplicationLayerTest {

        @Test
        @DisplayName("should only depends on domain layer")
        void shouldCheckApplicationLayerDependencies() {

            getLayeredArchitecture()
                .whereLayer(APPLICATION).mayOnlyAccessLayers(DOMAIN)
                .check(classes);
        }

        @Test
        @DisplayName("should be only accessed by adapters")
        void shouldCheckAccessToApplicationLayer() {

            getLayeredArchitecture()
                .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(
                INPUT_ADAPTERS)
                .check(classes);
        }
    }

    @Nested
    @DisplayName("Domain Layer")
    class DomainLayerTest {

        @Test
        @DisplayName("should not depends on any other layer")
        void shouldCheckDomainLayerDependencies() {

            getLayeredArchitecture()
                .whereLayer(DOMAIN).mayNotAccessAnyLayer()
                .check(classes);
        }
    }

    static Architectures.LayeredArchitecture getLayeredArchitecture() {

        return layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer(DOMAIN).definedBy("com.appgate.dtp.distinct.subsequence.domain..*")
            .layer(APPLICATION).definedBy("com.appgate.dtp.distinct.subsequence.application..*")
            .layer(INPUT_ADAPTERS).definedBy("com.appgate.dtp.distinct.subsequence.adapters.in..*");
    }
}
