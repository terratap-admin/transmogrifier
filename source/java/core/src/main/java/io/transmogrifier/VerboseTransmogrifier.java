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

/**
 * Runs a filter on an optional input and extra to produce an output.
 */
public class VerboseTransmogrifier
        extends Transmogrifier
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
    @Override
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

        System.out.println(filter.getClass().getName());
        System.out.println("- Input: " + input);
        System.out.println("- Extra: " + extra);
        output = filter.perform(input,
                                extra);
        System.out.println("- Output: " + output);

        return output;
    }
}
