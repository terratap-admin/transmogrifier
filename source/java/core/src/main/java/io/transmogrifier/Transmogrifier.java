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
 * Runs a filter on an optional input and extra to produce an output.
 */
public class Transmogrifier
{
    /**
     * Perform the transformation, taking the input and extra and creating an output.
     *
     * @param input  the input parameter
     * @param extra  the extra parameter
     * @param filter the filter to use in the transformation
     * @param <I>    input
     * @param <E>    extra
     * @param <O>    output
     * @return the result of the transformation
     * @throws FilterException if something goes wrong with the filtering
     */
    public <I, E, O> O transform(final I input,
                                 final E extra,
                                 final Filter<I, E, O> filter)
            throws
            FilterException
    {
        final O output;

        if(filter == null)
        {
            throw new RuntimeException("filter cannot be null");
        }

        output = filter.perform(input,
                                extra);

        return output;
    }

    /**
     * Perform the transformation, taking the input, passing null for the extra, and creating an output.
     *
     * @param input  the input parameter
     * @param filter the filter to use in the transformation
     * @param <I>    input
     * @param <O>    output
     * @return the result of the transformation
     * @throws FilterException if something goes wrong with the filtering
     */
    public <I, O> O transform(final I input,
                              final Filter<I, Void, O> filter)
            throws
            FilterException
    {
        return transform(input,
                         null,
                         filter);
    }

    /**
     * Perform the transformation, taking the input, passing null for the extra, and creating an output.
     *
     * @param input    the input parameter
     * @param function the function to use in the transformation
     * @param <I>      input
     * @param <O>      output
     * @return the result of the transformation
     * @throws FilterException if something goes wrong with the filtering
     */
    public <I, O> O transform(final I input,
                              final Function<I, O> function)
            throws
            FilterException
    {
        return transform(input,
                         new FunctionFilter<>(function));
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
     * @throws FilterException if something goes wrong with the filtering
     */
    public <I, E, O> O transform(final I input,
                                 final E extra,
                                 final BiFunction<I, E, O> function)
            throws
            FilterException
    {
        return transform(input,
                         extra,
                         new BiFunctionFilter<>(function));
    }
}
