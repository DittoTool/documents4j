package com.documents4j.job;

import com.documents4j.api.IConverter;
import com.documents4j.conversion.IConversionManager;
import com.documents4j.conversion.IExternalConverter;
import com.documents4j.conversion.MockConversionManager;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

class LocalConverterTestDelegate implements IConverterTestDelegate {

    private final boolean operational;
    private File temporaryFolder;
    private IConverter converter;

    public LocalConverterTestDelegate(boolean operational) {
        this.operational = operational;
    }

    public void setUp() {
        try {
            temporaryFolder = Files.createTempDirectory("tmp").toFile();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        converter = new StubbedLocalConverter(temporaryFolder);
        assertEquals(operational, converter.isOperational());
    }

    public void tearDown() {
        try {
            converter.shutDown();
            assertFalse(converter.isOperational());
        } finally {
            assertTrue(temporaryFolder.delete());
        }
    }

    @Override
    public IConverter getConverter() {
        return converter;
    }

    private class StubbedLocalConverter extends LocalConverter {

        private StubbedLocalConverter(File baseFolder) {
            super(baseFolder,
                    LocalConverter.Builder.DEFAULT_CORE_POOL_SIZE,
                    LocalConverter.Builder.DEFAULT_MAXIMUM_POOL_SIZE,
                    LocalConverter.Builder.DEFAULT_KEEP_ALIVE_TIME,
                    LocalConverter.Builder.DEFAULT_PROCESS_TIME_OUT,
                    TimeUnit.MILLISECONDS,
                    Collections.<Class<? extends IExternalConverter>, Boolean>emptyMap());
        }

        @Override
        protected IConversionManager makeConversionManager(File baseFolder,
                                                           long processTimeout,
                                                           TimeUnit unit,
                                                           Map<Class<? extends IExternalConverter>, Boolean> converterConfiguration) {
            return MockConversionManager.make(baseFolder, operational);
        }
    }
}
