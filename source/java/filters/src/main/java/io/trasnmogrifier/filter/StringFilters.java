package io.trasnmogrifier.filter;

import io.transmogrifier.Filter;
import io.transmogrifier.UnaryFilter;

import java.util.Locale;

public final class StringFilters
{
    private StringFilters()
    {
    }

    public interface StringFilter<E>
            extends Filter<String, E, String>
    {
    }

    public interface UnaryStringFilter
            extends UnaryFilter<String, String>
    {
    }

    public static class UpperCaseFilter
            implements StringFilter<Locale>
    {
        @Override
        public String perform(final String input,
                              final Locale locale)
        {
            final String upper;

            if(locale == null)
            {
                upper = input.toUpperCase();
            }
            else
            {
                upper = input.toUpperCase(locale);
            }

            return upper;
        }
    }

    public static class UnaryUpperCaseFilter
            implements UnaryStringFilter
    {
        @Override
        public String perform(final String input)
        {
            return input.toUpperCase();
        }
    }

    public static class LowerCaseFilter
            implements StringFilter<Locale>
    {
        @Override
        public String perform(final String input,
                              final Locale locale)
        {
            final String upper;

            if(locale == null)
            {
                upper = input.toLowerCase();
            }
            else
            {
                upper = input.toLowerCase(locale);
            }

            return upper;
        }
    }

    public static class UnaryLowerCaseFilter
            implements UnaryStringFilter
    {
        @Override
        public String perform(final String input)
        {
            return input.toLowerCase();
        }
    }
}
