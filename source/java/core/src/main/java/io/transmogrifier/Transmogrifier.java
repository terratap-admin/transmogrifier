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

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Runs a processor on an optional input and extra to produce an output.
 */
public class Transmogrifier
{
    /**
     * Perform the transformation, taking the input and extra and creating an output.
     *
     * @param input     the input parameter
     * @param extra     the extra parameter
     * @param processor the processor to use in the transformation
     * @param <I>       input
     * @param <E>       extra
     * @param <O>       output
     * @return the result of the transformation
     * @throws ProcessorException if something goes wrong with the processing
     */
    public <I, E, O> O transform(final I input,
                                 final E extra,
                                 final Processor<I, E, O> processor)
            throws
            ProcessorException
    {
        final O output;

        if(processor == null)
        {
            throw new RuntimeException("processor cannot be null");
        }

        output = processor.perform(input,
                                   extra);

        return output;
    }

    /**
     * Perform the transformation, taking the input, passing null for the extra, and creating an output.
     *
     * @param input     the input parameter
     * @param processor the processor to use in the transformation
     * @param <I>       input
     * @param <O>       output
     * @return the result of the transformation
     * @throws ProcessorException if something goes wrong with the processing
     */
    public <I, O> O transform(final I input,
                              final Processor<I, Void, O> processor)
            throws
            ProcessorException
    {
        return transform(input,
                         processor);
    }

    /**
     * Perform the transformation, taking the input, passing null for the extra, and creating an output.
     *
     * @param input    the input parameter
     * @param function the function to use in the transformation
     * @param <I>      input
     * @param <O>      output
     * @return the result of the transformation
     * @throws ProcessorException if something goes wrong with the processing
     */
    public <I, O> O transform(final I input,
                              final Function<I, O> function)
            throws
            ProcessorException
    {
        return transform(input,
                         new FunctionProcessor<>(function));
    }

    /**
     * Perform the transformation, taking the input and extra and creating an output.
     *
     * @param input    the input parameter
     * @param extra    the extra parameter
     * @param function the function to use in the transformation
     * @param <I>      input
     * @param <E>      extra
     * @param <O>      output
     * @return the result of the transformation
     * @throws ProcessorException if something goes wrong with the processing
     */
    public <I, E, O> O transform(final I input,
                                 final E extra,
                                 final BiFunction<I, E, O> function)
            throws
            ProcessorException
    {
        return transform(input,
                         extra,
                         new BiFunctionProcessor<>(function));
    }
}
