package io.trasnmogrifier.filter;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;

public final class ConversionFilters
{
    private ConversionFilters()
    {
    }

    public static int toInt(final String str,
                            final int radix)
    {
        try
        {
            final int value;

            value = Integer.parseInt(str,
                                     radix);

            return value;
        }
        catch(final NumberFormatException ex)
        {
            throw new RuntimeException(ex.getMessage(),
                                       ex);
        }
    }

    public interface ConversionFilter<I, E, O>
            extends Filter<I, E, O>
    {
    }

    public static class StringToInt
            implements ConversionFilter<String, Integer, Integer>
    {
        @Override
        public Integer perform(final String input,
                               final Integer radix)
                throws
                FilterException
        {
            try
            {
                final int actulRadix;
                final int value;

                if(radix == null)
                {
                    actulRadix = 10;
                }
                else
                {
                    actulRadix = radix;
                }

                value = Integer.parseInt(input,
                                         actulRadix);

                return value;
            }
            catch(final NumberFormatException ex)
            {
                throw new FilterException(ex.getMessage(),
                                          ex);
            }
        }
    }
}
