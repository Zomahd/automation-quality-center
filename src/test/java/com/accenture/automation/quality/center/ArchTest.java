package com.accenture.automation.quality.center;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.accenture.automation.quality.center");

        noClasses()
            .that()
            .resideInAnyPackage("com.accenture.automation.quality.center.service..")
            .or()
            .resideInAnyPackage("com.accenture.automation.quality.center.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.accenture.automation.quality.center.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
