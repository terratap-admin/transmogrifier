package io.transmogrifier.processors.external;

import io.transmogrifier.Processor;
import io.transmogrifier.ProcessorException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

/**
 * Use the Scripting Engine framework to perform the processor functions.
 *
 * @param <O>
 */
public class ScriptEngineProcessor<O>
        implements Processor<List<Object>, Void, O>
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
     * The function to call in the script to perform the processing.
     */
    private final String entryPoint;

    /**
     * Construct a ScriptEngineProcessor with the specified script engine,
     * source code, and entry point.
     *
     * @param name the name of the script engine to use
     * @param code the source code to run
     * @param func the entry point into the code
     */
    public ScriptEngineProcessor(final String name,
                                 final String code,
                                 final String func)
    {
        engineName = name;
        script = code;
        entryPoint = func;
    }

    /**
     * Perform the process, taking the input and extra and creating an output.
     *
     * @param parameters the input parameters to pass to the entry point
     * @param extra      ignored
     * @return the result
     * @throws ProcessorException if something goes wrong with the processing
     */
    @Override
    public O perform(final List<Object> parameters,
                     final Void extra)
            throws
            ProcessorException
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
            throw new ProcessorException(ex.getMessage(),
                                         ex);
        }
    }
}
