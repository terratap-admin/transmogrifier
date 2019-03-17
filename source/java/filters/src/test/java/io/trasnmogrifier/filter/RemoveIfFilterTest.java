package io.trasnmogrifier.filter;

import io.transmogrifier.FilterException;
import io.trasnmogrifier.filter.StringFilters.UpperCaseFilter;
import io.trasnmogrifier.filter.comparison.IsNullFilter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RemoveIfFilterTest
{
    @Test
    public void test()
            throws
            FilterException
    {
        final RemoveIfFilter<String, Locale, String> removeIfFilter;
        final List<String>                           results;

        removeIfFilter = new RemoveIfFilter<>(new UpperCaseFilter(),
                                              new IsNullFilter<>());
        results = removeIfFilter.perform(Stream.of("Hello",
                                                   null,
                                                   "world!"));
        assertThat(results,
                   is(equalTo(Arrays.asList("HELLO",
                                            "WORLD!"))));
    }
}
