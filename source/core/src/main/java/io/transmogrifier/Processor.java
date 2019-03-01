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

/**
 * A processor takes input and an extra for arguments, processes them, and generates an outout.
 *
 * @param <I> input
 * @param <E> extra
 * @param <O> output
 */
public interface Processor<I, E, O>
        extends BiFunction<I, E, O>
{
    /**
     * Perform the process, taking the input and extra and creating an output.
     *
     * @param input the input parameter
     * @param extra the extra paremater
     * @return the result
     * @throws ProcessorException if something goes wrong with the processing
     */
    O perform(I input,
              E extra)
            throws
            ProcessorException;

    /**
     * Perform the process, taking the input, using null for the extra, and creating an output.
     *
     * @param input the input parameter
     * @return the result
     * @throws ProcessorException if something goes wrong with the processing
     */
    default O perform(I input)
            throws
            ProcessorException
    {
        return perform(input,
                       null);
    }

    /**
     * Perform the process, taking the input and extra and creating an output.
     *
     * @param input the input parameter
     * @param extra the extra parameter
     * @return the result
     * @throws RuntimeException if something goes wrong with the processing the ProcessException is wrapped
     */
    default O apply(final I input,
                    final E extra)
    {
        try
        {
            return perform(input,
                           null);
        }
        catch(final ProcessorException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
