package io.transmogrifier;

import java.util.function.Function;

/**
 *
 * @param <I>
 * @param <O>
 */
public class FilterFunction<I, O>
        implements Function<I, O>
{
    /**
     *
     */
    private Filter<I, Void, O> filter;

    /**
     *
     * @param f
     */
    public FilterFunction(final Filter<I, Void, O> f)
    {
        filter = f;
    }

    /**
     *
     * @param f
     */
    public FilterFunction(final UnaryFilter<I, O> f)
    {
        filter = f;
    }

    /**
     *
     * @param input
     * @return
     */
    @Override
    public O apply(final I input)
    {
        try
        {
            return filter.perform(input);
        }
        catch(final FilterException ex)
        {
            throw new RuntimeException(ex.getMessage(),
                                       ex);
        }
    }
}
