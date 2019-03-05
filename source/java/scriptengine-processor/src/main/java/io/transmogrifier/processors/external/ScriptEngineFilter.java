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

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

/**
 * Use the Scripting Engine framework to perform the filter functions.
 *
 * @param <O>
 */
public class ScriptEngineFilter<O>
        implements Filter<List<Object>, Void, O>
{
    /**
     * The name of the script engine to use.
     */
    private final String engineName;

    /**
     * The code for the script.
     */
    private final String script;

    /**
     * The function to call in the script to perform the filtering.
     */
    private final String entryPoint;

    /**
     * Construct a ScriptEngineFilter with the specified script engine,
     * source code, and entry point.
     *
     * @param name the name of the script engine to use
     * @param code the source code to run
     * @param func the entry point into the code
     */
    public ScriptEngineFilter(final String name,
                              final String code,
                              final String func)
    {
        engineName = name;
        script = code;
        entryPoint = func;
    }

    /**
     * Perform the filter, taking the input and extra and creating an output.
     *
     * @param parameters the input parameters to pass to the entry point
     * @param extra      ignored
     * @return the result
     * @throws FilterException if something goes wrong with the filtering
     */
    @Override
    public O perform(final List<Object> parameters,
                     final Void extra)
            throws
            FilterException
    {
        final ScriptEngineManager manager;
        final ScriptEngine        engine;

        manager = new ScriptEngineManager();
        engine = manager.getEngineByName(engineName);

        try
        {
            final Invocable invocable;
            final Object[]  args;
            final Object    result;

            invocable = (Invocable)engine;
            //            engine.eval("var module = {}");
            engine.eval(script);
            args = parameters.toArray();
            result = invocable.invokeFunction(entryPoint,
                                              args);

            return (O)result;
        }
        catch(final ScriptException | NoSuchMethodException ex)
        {
            throw new FilterException(ex.getMessage(),
                                      ex);
        }
    }
}
