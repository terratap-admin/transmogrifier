package io.trasnmogrifier.filter;

import io.transmogrifier.FilterFunction;
import io.trasnmogrifier.filter.StringFilters.UnaryUpperCaseFilter;
import io.trasnmogrifier.filter.comparison.IsNotNullFilter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class StreamTest
{
    @Test
    public void testFilter()
    {
        final List<String> list;
        final List<String> notNull;

        list = Arrays.asList("abc",
                             null,
                             "def");
        notNull = list.stream().filter(new IsNotNullFilter<>()).collect(Collectors.toList());
        assertThat(notNull,
                   contains("abc",
                            "def"));
    }

    @Test
    public void testMap()
    {
        final List<String> list;
        final List<String> upper;

        list = Arrays.asList("ghi",
                             "jkl");
        upper = list.stream().map(new FilterFunction<>(new UnaryUpperCaseFilter())).collect(Collectors.toList());
        assertThat(upper,
                   contains("GHI",
                            "JKL"));
    }

    @Test
    public void testFilterMap()
    {
        final List<String> list;
        final List<String> upper;

        list = Arrays.asList("ghi",
                             null,
                             "jkl");
        upper = list.stream().filter(new IsNotNullFilter<>()).map(new FilterFunction<>(new UnaryUpperCaseFilter())).collect(Collectors.toList());
        assertThat(upper,
                   contains("GHI",
                            "JKL"));
    }
}
