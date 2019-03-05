package io.transmogrifier;

/**
 * A handy filter to that uses Void for the extra.
 *
 * @param <I> input
 * @param <O> output
 * @see java.util.function.Function
 */
public interface UnaryFilter<I, O>
        extends Filter<I, Void, O>
{
    /**
     * Perform the filter by calling apply on the input and returning the result.
     *
     * @param input the input parameter
     * @param input the extra parameter
     * @return the result
     * @throws RuntimeException if something goes wrong with the filtering
     */
    default O perform(final I input,
                      final Void extra)
            throws
            FilterException
    {
        return perform(input);
    }
}
