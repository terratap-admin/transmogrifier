package io.trasnmogrifier.filter;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.UnaryFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 *
 */
public final class FileFilters
{
    /**
     *
     */
    private FileFilters()
    {
    }

    /**
     * @param <E>
     * @param <O>
     */
    public static abstract class FileFilter<E, O>
            implements Filter<File, E, O>
    {
        @Override
        public O perform(final File file,
                         final E extra)
                throws
                FilterException
        {
            final Path path;

            path = file.toPath();

            try
            {
                final byte[] bytes;
                final O      contents;

                bytes = Files.readAllBytes(path);
                contents = getContent(bytes,
                                      extra);

                return contents;
            }
            catch(final IOException ex)
            {
                throw new FilterException(ex.getMessage(),
                                          ex);
            }
        }

        protected abstract O getContent(byte[] bytes,
                                        E extra);
    }

    /**
     * @param <E>
     * @param <O>
     */
    public static abstract class UnaryFileFilter<O>
            implements UnaryFilter<File, O>
    {
        @Override
        public O perform(final File file)
                throws
                FilterException
        {
            final Path path;

            path = file.toPath();

            try
            {
                final byte[] bytes;
                final O      contents;

                bytes = Files.readAllBytes(path);
                contents = getContent(bytes);

                return contents;
            }
            catch(final IOException ex)
            {
                throw new FilterException(ex.getMessage(),
                                          ex);
            }
        }

        protected abstract O getContent(byte[] bytes);
    }

    /**
     *
     */
    public static class FileToBinaryFilter
            extends FileFilter<Void, byte[]>
    {
        @Override
        protected byte[] getContent(final byte[] bytes,
                                    final Void ignore)
        {
            return bytes;
        }
    }

    /**
     *
     */
    public static class UnaryFileToBinaryFilter
            extends UnaryFileFilter<byte[]>
    {
        @Override
        protected byte[] getContent(final byte[] bytes)
        {
            return bytes;
        }
    }

    /**
     *
     */
    public static class FileToStringFilter
            extends FileFilter<Charset, String>
    {
        @Override
        protected String getContent(final byte[] bytes,
                                    final Charset charset)
        {
            final Charset actualCharset;
            final String  contents;

            if(charset == null)
            {
                actualCharset = StandardCharsets.UTF_8;
            }
            else
            {
                actualCharset = charset;
            }

            contents = new String(bytes,
                                  actualCharset);

            return contents;
        }
    }


    /**
     *
     */
    public static class UnaryFileToStringFilter
            extends UnaryFileFilter<String>
    {
        @Override
        protected String getContent(final byte[] bytes)
        {
            final Charset actualCharset;
            final String  contents;

            actualCharset = StandardCharsets.UTF_8;

            contents = new String(bytes,
                                  actualCharset);

            return contents;
        }
    }

    /**
     *
     */
    public static class StringToFileFilter
            implements Filter<String, File, Void>
    {
        @Override
        public Void perform(final String contents,
                            final File file)
                throws
                FilterException
        {
            try
            {
                final Path path;

                path = file.toPath();
                Files.write(path,
                            contents.getBytes(),
                            StandardOpenOption.CREATE,
                            StandardOpenOption.TRUNCATE_EXISTING);

                return null;
            }
            catch(final IOException ex)
            {
                throw new FilterException(ex.getMessage(),
                                          ex);
            }
        }
    }
}
