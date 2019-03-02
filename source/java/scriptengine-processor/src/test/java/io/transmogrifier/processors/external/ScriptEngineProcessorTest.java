/*
 * Copyright (C) 2019 Terratap Technology Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
