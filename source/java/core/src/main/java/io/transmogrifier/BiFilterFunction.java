package io.transmogrifier;

import java.util.function.BiFunction;

public class BiFilterFunction<I, E, O>
        implements BiFunction<I, E, O>
{
    private Filter<I, E, O> filter;

    public BiFilterFunction(final Filter<I, E, O> f)
    {
        filter = f;
    }

    @Override
    public O apply(final I input, final E extra)
    {
        try
        {
            return filter.perform(input, extra);
        }
        catch(final FilterException ex)
        {
            throw new RuntimeException(ex.getMessage(),
                                       ex);
        }
    }
}
