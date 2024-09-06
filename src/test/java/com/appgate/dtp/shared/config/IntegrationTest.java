package com.appgate.dtp.shared.config;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Represent a base for integration test. to create an integration test you should implement this interface.
 * <br>
 * <code>
 * class MyIntegrationTest implements IntegrationTest {
 * // Your tests goes here...
 * }
 * </code>
 */
@Tag(IntegrationTest.TAG)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public interface IntegrationTest {
    String TAG = "IntegrationTest";
}
