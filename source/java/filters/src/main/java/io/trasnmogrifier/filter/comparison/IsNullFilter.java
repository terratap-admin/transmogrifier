package io.trasnmogrifier.filter.comparison;

/**
 * @param <I>
 */
public class IsNullFilter<I extends Object>
        implements ComparisonFilter<I>
{
    /**
     * @param input
     * @return
     */
    @Override
    public Boolean perform(final I input)
    {
        return input == null;
    }
}
