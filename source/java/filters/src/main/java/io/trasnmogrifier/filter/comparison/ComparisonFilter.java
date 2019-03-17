package io.trasnmogrifier.filter.comparison;

import io.transmogrifier.FilterException;
import io.transmogrifier.UnaryFilter;

import java.util.function.Predicate;

/**
 * @param <T>
 */
public interface ComparisonFilter<T>
        extends UnaryFilter<T, Boolean>,
                Predicate<T>
{
    /**
     * @param t
     * @return
     */
    default boolean test(final T t)
    {
        try
        {
            return perform(t);
        }
        catch(final FilterException ex)
        {
            throw new RuntimeException(ex.getMessage(),
                                       ex);
        }
    }
}
