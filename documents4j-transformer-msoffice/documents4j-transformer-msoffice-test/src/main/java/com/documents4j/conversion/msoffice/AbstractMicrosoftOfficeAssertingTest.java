package com.documents4j.conversion.msoffice;

import org.junit.AfterClass;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class AbstractMicrosoftOfficeAssertingTest {

    public static final long DEFAULT_CONVERSION_TIMEOUT = 50000L;

    private static File assertionEngineFolder;
    private static MicrosoftOfficeAssertionEngine msOfficeAssertionEngine;

    protected AbstractMicrosoftOfficeAssertingTest() {
        assertNotNull(getClass() + "was not set up properly", msOfficeAssertionEngine);
    }

    // Must be called from a @BeforeClass method in the inheriting class.
    protected static void setUp(MicrosoftOfficeScript assertionScript, MicrosoftOfficeScript shutdownScript) throws Exception {
        assertionEngineFolder = Files.createTempDirectory("tmp").toFile();
        msOfficeAssertionEngine = new MicrosoftOfficeAssertionEngine(assertionEngineFolder, assertionScript, shutdownScript);
        msOfficeAssertionEngine.assertNotRunning();
    }

    @AfterClass
    public static void tearDownAssertionEngine() throws Exception {
        if (msOfficeAssertionEngine != null) {
            try {
                msOfficeAssertionEngine.assertNotRunning();
            } catch (AssertionError e) {
                // Last attempt to kill Word in order to allow other tests to run normally.
                msOfficeAssertionEngine.kill();
                throw e;
            } finally {
                try {
                    msOfficeAssertionEngine.shutDown();
                    assertTrue(assertionEngineFolder.delete());
                } finally {
                    assertionEngineFolder = null;
                    msOfficeAssertionEngine = null;
                }
            }
        }
    }

    public static MicrosoftOfficeAssertionEngine getAssertionEngine() {
        return msOfficeAssertionEngine;
    }
}
