package io.trasnmogrifier.filter;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.trasnmogrifier.filter.comparison.ComparisonFilter;

import java.util.List;

/**
 * @param <I>
 * @param <E>
 * @param <O>
 */
public class RemoveIfFilter<I, E, O>
        extends ForEachFilter<I, E, O>
{
    /**
     *
     */
    private final ComparisonFilter<I> comparisonFilter;

    /**
     * @param f
     * @param cf
     */
    public RemoveIfFilter(final Filter<I, E, O> f,
                          final ComparisonFilter<I> cf)
    {
        super(f);

        comparisonFilter = cf;
    }

    /**
     * @param entry
     * @param extra
     * @param results
     * @throws FilterException
     */
    protected void perform(final I entry,
                           final E extra,
                           final List<O> results)
            throws
            FilterException
    {
        final boolean result;

        result = comparisonFilter.perform(entry);

        if(!(result))
        {
            super.perform(entry,
                          extra,
                          results);
        }
    }
}
