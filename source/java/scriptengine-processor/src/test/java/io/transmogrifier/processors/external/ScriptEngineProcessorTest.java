package io.transmogrifier.processors.external;

import io.transmogrifier.ProcessorException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ScriptEngineProcessorTest
{
    @Test
    public void testScript()
            throws
            ProcessorException
    {
        final ScriptEngineProcessor<String> processor;
        final String                        output;

        processor = new ScriptEngineProcessor<>("graal.js",
                                                "function convert(data) { var json = data; if(typeof(data) === 'string' || data instanceof String) { json = JSON.parse(data); } return JSON.stringify(json, null, 4); }",
                                                "convert");

        output = processor.perform(Arrays.asList("{\"x\" : 1}"));
        assertThat(output,
                   is(equalTo("{\n    \"x\": 1\n}")));
    }
}
