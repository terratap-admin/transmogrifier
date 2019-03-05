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
 * An adapter to make a BiFunction act as a Filter
 *
 * @param <I> input
 * @param <E> extra
 * @param <O> output
 * @see java.util.function.BiFunction
 */
public class BiFunctionFilter<I, E, O>
        implements Filter<I, E, O>
{
    /**
     * The function to wrap.
     */
    private final BiFunction<I, E, O> function;

    /**
     * Construct a FunctionFilter with the specified function.
     *
     * @param func the function to wrap
     */
    public BiFunctionFilter(final BiFunction<I, E, O> func)
    {
        if(func == null)
        {
            throw new RuntimeException("func cannot be null");
        }

        function = func;
    }

    /**
     * Perform the filter by calling apply on the input and extra and returning the result.
     *
     * @param input the input parameter
     * @param input the extra parameter
     * @return the result
     * @throws RuntimeException if something goes wrong with the filtering
     */
    public O perform(final I input,
                     final E extra)
            throws
            FilterException
    {
        try
        {
            return function.apply(input,
                                  extra);
        }
        catch(final RuntimeException ex)
        {
            throw new FilterException(ex.getMessage(),
                                      ex);
        }
    }
}
