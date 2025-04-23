package mx.uv.coatza.S22017021.copsboot.annotation;

import mx.uv.coatza.S22017021.copsboot.documentation.CopsbootControllerDocumentationTestConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@CopsbootControllerTest
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@ContextConfiguration(classes = CopsbootControllerDocumentationTestConfiguration.class)
public @interface CopsbootControllerDocumentationTest {
    @AliasFor(annotation = WebMvcTest.class, attribute = "value")
    Class<?>[] value() default {};
    @AliasFor(annotation = WebMvcTest.class, attribute = "controllers")
    Class<?>[] controllers() default {};
}
