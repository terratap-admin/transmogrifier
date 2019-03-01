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

import java.util.function.BiFunction;
import java.util.function.Function;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TransmogrifierTest
{
    @Test
    public void testTransformProcessor()
            throws
            ProcessorException
    {
        final Transmogrifier                   transmogrifier;
        final Processor<String, Void, Integer> processor;

        transmogrifier = new Transmogrifier();
        processor = new Processor<String, Void, Integer>()
        {
            @Override
            public Integer perform(final String input,
                                   final Void extra)
                    throws
                    ProcessorException
            {
                return Integer.parseInt(input);
            }
        };

        assertThat(transmogrifier.transform("123",
                                            processor),
                   is(equalTo(123)));
        assertThat(transmogrifier.transform("45",
                                            null,
                                            processor),
                   is(equalTo(45)));
    }

    @Test
    public void testTransformFunction()
            throws
            ProcessorException
    {
        final Transmogrifier            transmogrifier;
        final Function<String, Integer> function;

        transmogrifier = new Transmogrifier();
        function = new Function<String, Integer>()
        {
            @Override
            public Integer apply(final String input)
            {
                return Integer.parseInt(input);
            }
        };

        assertThat(transmogrifier.transform("123",
                                            function),
                   is(equalTo(123)));
    }

    @Test
    public void testTransformBiFunction()
            throws
            ProcessorException
    {
        final Transmogrifier                    transmogrifier;
        final BiFunction<String, Void, Integer> function;

        transmogrifier = new Transmogrifier();
        function = new BiFunction<String, Void, Integer>()
        {
            @Override
            public Integer apply(final String input,
                                 final Void extra)
            {
                return Integer.parseInt(input);
            }
        };

        assertThat(transmogrifier.transform("123",
                                            null,
                                            function),
                   is(equalTo(123)));
    }
}
