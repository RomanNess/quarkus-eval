package org.acme.eval;

import io.quarkus.test.junit.QuarkusTestExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import space.testflight.ConfigProperty;
import space.testflight.Flyway;
import space.testflight.FlywayExtension;

import javax.enterprise.inject.Stereotype;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Stereotype
// The order of extensions is essential
@ExtendWith({FlywayExtension.class, QuarkusTestExtension.class})
@Flyway(dockerImage = "postgres:12",
        configuration = {
                @ConfigProperty(key = "space.testflight.jdbc.url.property", value = "quarkus.datasource.jdbc.url"),
                @ConfigProperty(key = "space.testflight.jdbc.username.property", value = "quarkus.datasource.username"),
                @ConfigProperty(key = "space.testflight.jdbc.password.property", value = "quarkus.datasource.password")
        })
public @interface MyQuarkusTest {
}
