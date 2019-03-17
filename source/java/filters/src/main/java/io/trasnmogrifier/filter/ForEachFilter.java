package io.trasnmogrifier.filter;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @param <I>
 * @param <E>
 * @param <O>
 */
public class ForEachFilter<I, E, O>
        implements Filter<Stream<I>, E, List<O>>
{
    /**
     *
     */
    private final Filter<I, E, O> filter;

    /**
     * @param f
     */
    public ForEachFilter(final Filter<I, E, O> f)
    {
        filter = f;
    }

    /**
     * @param stream
     * @param extra
     * @return
     */
    @Override
    public List<O> perform(final Stream<I> stream,
                           final E extra)
    {
        final List<O> results;

        results = new ArrayList<>();

        stream.forEach((entry) ->
                       {
                           try
                           {
                               perform(entry,
                                       extra,
                                       results);
                           }
                           catch(final FilterException ex)
                           {
                               throw new RuntimeException(ex.getMessage(),
                                                          ex);
                           }
                       });

        return results;
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
        final O output;

        output = filter.perform(entry,
                                extra);
        results.add(output);
    }
}
