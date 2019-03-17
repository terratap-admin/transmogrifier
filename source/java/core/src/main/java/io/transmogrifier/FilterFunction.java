package io.transmogrifier;

import java.util.function.Function;

public class FilterFunction<I, O>
        implements Function<I, O>
{
    private Filter<I, Void, O> filter;

    public FilterFunction(final Filter<I, Void, O> f)
    {
        filter = f;
    }

    public FilterFunction(final UnaryFilter<I, O> f)
    {
        filter = f;
    }

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
