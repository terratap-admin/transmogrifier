package io.trasnmogrifier.filter;

import io.transmogrifier.FilterException;
import io.trasnmogrifier.filter.StringFilters.UpperCaseFilter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ForEachFilterTest
{
    @Test
    public void testUpperCase()
            throws
            FilterException
    {
        final ForEachFilter<String, Locale, String> forEachFilter;
        final List<String>                          results;

        forEachFilter = new ForEachFilter<>(new UpperCaseFilter());
        results = forEachFilter.perform(Arrays.asList("Hello",
                                                      "world!").stream());

        assertThat(results,
                   is(equalTo(Arrays.asList("HELLO",
                                            "WORLD!"))));
    }

    @Test
    public void testStringToInt()
    {
        final ForEachFilter<String, Integer, Integer> forEachFilter;
        final List<Integer>                           results;

        forEachFilter = new ForEachFilter<>(ConversionFilters::toInt);
        results = forEachFilter.perform(Stream.of("123",
                                                  "4",
                                                  "56789"),
                                        16);

        assertThat(results,
                   is(equalTo(Arrays.asList(291,
                                            4,
                                            354185))));
    }
}
