package io.transmogrifier;

import java.util.function.BiFunction;

/**
 *
 * @param <I>
 * @param <E>
 * @param <O>
 */
public class BiFilterFunction<I, E, O>
        implements BiFunction<I, E, O>
{
    /**
     *
     */
    private Filter<I, E, O> filter;

    /**
     *
     * @param f
     */
    public BiFilterFunction(final Filter<I, E, O> f)
    {
        filter = f;
    }

    /**
     *
     * @param input
     * @param extra
     * @return
     */
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
