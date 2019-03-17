package io.trasnmogrifier.filter.comparison;

public class IsNullFilter<I extends Object>
        implements ComparisonFilter<I>
{
    @Override
    public Boolean perform(final I input)
    {
        return input == null;
    }
}
