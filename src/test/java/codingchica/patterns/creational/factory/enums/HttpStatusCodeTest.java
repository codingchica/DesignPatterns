package codingchica.patterns.creational.factory.enums;

import static codingchica.patterns.creational.factory.enums.HttpStatusCode.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the codingchica.patterns.creational.factory.enums.HttpStatusCode class.
 */
public class HttpStatusCodeTest {
    /** Tests to ensure that the enums are configured as expected. */
    @Nested
    public class ConfigurationTest {
        @Test
        void values_expectedValuesPresent() {
            // Setup
            HttpStatusCode[] httpStatusCodes = new HttpStatusCode[]{
                    // 100s
                    CONTINUE,
                    // 200s
                    OK,
                    // 300s
                    MOVED_PERMANENTLY,
                    // 400s
                    NOT_FOUND,
                    // 500s
                    INTERNAL_SERVER_ERROR};

            // Execution
            HttpStatusCode[] result = HttpStatusCode.values();

            // Validation
            assertEquals(httpStatusCodes.length, result.length);
        }

        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.EXCLUDE)
        void codes_withinAcceptableRange(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");

            // Execution
            int code = value.getCode();

            // Validation
            // These limits are important, as we are not checking for values outside these bounds.
            assertTrue(code >= 100, String.format("code (%s) >= 100", code));
            assertTrue(code < 600, String.format("code (%s) < 600", code));
        }


        @Test
        void codes_unique() {
            // Setup
            HttpStatusCode[] values = HttpStatusCode.values();

            // Execution
            Set<Object> result = Arrays.stream(values).map(HttpStatusCode::getCode).collect(Collectors.toSet());

            // Validation
            assertEquals(values.length, result.size(), "size");
        }
    }

    /** Tests to ensure that the isInformational method functions as expected */
    @Nested
    public class IsInformationalTest {

        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.INCLUDE, names = {"CONTINUE"})
        void isInformational_when100s_thenTrue(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() >= 100 && value.getCode() < 200, String.format("100 <= code (%s) < 200", value.getCode()));

            // Execution
            boolean result = value.isInformational();

            // Validation
            assertTrue(result, "result");
        }


        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"CONTINUE"})
        void isInformational_whenNot100s_thenFalse(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() < 100 || value.getCode() >= 200, String.format("code (%s) < 100 || >= 200", value.getCode()));

            // Execution
            boolean result = value.isInformational();

            // Validation
            assertFalse(result, "result");
        }
    }

    /** Tests to ensure that the isSuccessful method functions as expected */
    @Nested
    public class IsSuccessfulTest {
        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.INCLUDE, names = {"OK"})
        void isSuccessful_when200s_thenTrue(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() >= 200 && value.getCode() < 300, String.format("200 <= code (%s) < 300", value.getCode()));

            // Execution
            boolean result = value.isSuccessful();

            // Validation
            assertTrue(result, "result");
        }


        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"OK"})
        void isSuccessful_whenNot200s_thenFalse(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() < 200 || value.getCode() >= 300, String.format("code (%s) < 200 || >= 300", value.getCode()));

            // Execution
            boolean result = value.isSuccessful();

            // Validation
            assertFalse(result, "result");
        }
    }

    /** Tests to ensure that the isRedirection method functions as expected */
    @Nested
    public class IsRedirectionTest {

        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.INCLUDE, names = {"MOVED_PERMANENTLY"})
        void isRedirection_when300s_thenTrue(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() >= 300 && value.getCode() < 400, String.format("300 <= code (%s) < 400", value.getCode()));

            // Execution
            boolean result = value.isRedirection();

            // Validation
            assertTrue(result, "result");
        }


        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"MOVED_PERMANENTLY"})
        void isRedirection_whenNot300s_thenFalse(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() < 300 || value.getCode() >= 400, String.format("code (%s) < 300 || >= 400", value.getCode()));

            // Execution
            boolean result = value.isRedirection();

            // Validation
            assertFalse(result, "result");
        }
    }

    /** Tests to ensure that the isClientError method functions as expected */
    @Nested
    public class IsClientErrorTest {
        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.INCLUDE, names = {"NOT_FOUND"})
        void isClientError_when400s_thenTrue(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() >= 400 && value.getCode() < 500, String.format("400 <= code (%s) < 500", value.getCode()));

            // Execution
            boolean result = value.isClientError();

            // Validation
            assertTrue(result, "result");
        }


        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"NOT_FOUND"})
        void isClientError_whenNot400s_thenFalse(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() < 400 || value.getCode() >= 500, String.format("code (%s) < 400 || >= 500", value.getCode()));

            // Execution
            boolean result = value.isClientError();

            // Validation
            assertFalse(result, "result");
        }
    }

    /** Tests to ensure that the isServerError method functions as expected */
    @Nested
    public class IsServerErrorTest {

        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.INCLUDE, names = {"INTERNAL_SERVER_ERROR"})
        void isServerError_when500s_thenTrue(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() >= 500 && value.getCode() < 600, String.format("500 <= code (%s) < 600", value.getCode()));

            // Execution
            boolean result = value.isServerError();

            // Validation
            assertTrue(result, "result");
        }

        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"INTERNAL_SERVER_ERROR"})
        void isServerError_whenNot500s_thenFalse(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");
            assertTrue(value.getCode() < 500 || value.getCode() >= 600, String.format("code (%s) < 500 || >= 600", value.getCode()));

            // Execution
            boolean result = value.isServerError();

            // Validation
            assertFalse(result, "result");
        }
    }

    /** Tests to ensure that the fromCode method functions as expected */
    @Nested
    public class FromCodeTest {

        @ParameterizedTest
        @ValueSource(ints = {600, 0, 1, -5})
        void fromCode_whenNotFound_thenOptionalEmptyReturned(int code) {
            // Setup

            // Execution
            Optional<HttpStatusCode> result = fromCode(code);

            // Validation
            assertTrue(result.isEmpty(), "result.isEmpty()");
        }

        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.EXCLUDE)
        void fromCode_whenNotFound_thenOptionalPopulatedReturned(HttpStatusCode value) {
            // Setup
            assertNotNull(value, "value");

            // Execution
            Optional<HttpStatusCode> result = fromCode(value.getCode());

            // Validation
            assertFalse(result.isEmpty(), "result.isEmpty()");
            assertSame(value, result.get(), "result.get()");
        }
    }
}