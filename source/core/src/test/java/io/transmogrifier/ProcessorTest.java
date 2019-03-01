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

package io.transmogrifier;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ProcessorTest
{
    @Test
    public void testProcess()
            throws
            ProcessorException
    {
        final Processor<String, Void, Integer> processor;

        processor = new Processor<String, Void, Integer>()
        {
            @Override
            public Integer perform(String input,
                                   Void extra)
                    throws
                    ProcessorException
            {
                return Integer.parseInt(input);
            }
        };

        assertThat(processor.perform("123"),
                   is(equalTo(123)));
        assertThat(processor.perform("45",
                                     null),
                   is(equalTo(45)));
        assertThat(processor.apply("6",
                                   null),
                   is(equalTo(6)));
    }
}
