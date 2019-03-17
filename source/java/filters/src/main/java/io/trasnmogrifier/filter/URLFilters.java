package io.trasnmogrifier.filter;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public final class URLFilters
{
    private URLFilters()
    {
    }

    public static abstract class URLFilter<E, O>
            implements Filter<URL, E, O>
    {
        @Override
        public O perform(final URL url,
                         final E extra)
                throws
                FilterException
        {
            final CompletableFuture<HttpResponse> downloader;

            downloader = CompletableFuture.supplyAsync(new Downloader(url));

            try
            {
                final HttpResponse response;
                final byte[]       bytes;
                final Charset      suppliedCharset;
                final O            content;

                response = downloader.get();

                if(response.getStatusCode() >= 300)
                {
                    throw new RuntimeException();
                }

                bytes = getContent(response);
                suppliedCharset = response.getContentCharset();
                content = convertBytes(bytes,
                                       suppliedCharset,
                                       extra);

                return content;
            }
            catch(final ExecutionException | InterruptedException | IOException ex)
            {
                throw new FilterException(ex.getMessage(),
                                          ex);
            }
        }

        protected byte[] getContent(final HttpResponse response)
                throws
                IOException
        {
            final InputStream stream;
            final byte[] bytes;
            final ByteArrayOutputStream buffer;
            int nRead;

            stream = response.getContent();
            buffer = new ByteArrayOutputStream();
            bytes = new byte[16384];

            while((nRead = stream.read(bytes, 0, bytes.length)) != -1)
            {
                buffer.write(bytes, 0, nRead);
            }

            return buffer.toByteArray();
        }

        protected abstract O convertBytes(final byte[] bytes,
                                          final Charset suppliedCharset,
                                          final E extra)
                throws
                IOException;

        protected class Downloader
                implements Supplier<HttpResponse>
        {
            private final URL url;

            protected Downloader(final URL url)
            {
                this.url = url;
            }

            @Override
            public HttpResponse get()
            {
                final HttpTransport      transport;
                final HttpRequestFactory factory;
                final GenericUrl         requestURL;
                final HttpRequest        request;
                final HttpResponse       response;

                transport = new NetHttpTransport();
                factory = transport.createRequestFactory();
                requestURL = new GenericUrl(url);

                try
                {
                    final HttpHeaders headers;
                    final String      etag;

                    // TODO: get the etag from the meta file
                    etag = null;
                    request = factory.buildGetRequest(requestURL);
                    headers = request.getHeaders();

                    // have to remove gzip to get the ETag
                    headers.clear();
                    headers.setAccept("*/*");

                    if(etag == null)
                    {
                        final String modified;

                        // TODO: get the date from the meta file
                        modified = null;
                        headers.setIfModifiedSince(modified);
                    }
                    else
                    {
                        headers.setIfNoneMatch(etag);
                    }

                    request.setHeaders(headers);
                    //request.setThrowExceptionOnExecuteError(false);
                    response = request.execute();

                    // TODO: get the header and grab the etag & save it to the meta file
                    // TODO: if no etag use the date

                    return response;
                }
                catch(final IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public static class URLToBinaryFilter
            extends URLFilter<Void, byte[]>
    {
        @Override
        protected byte[] convertBytes(final byte[] bytes,
                                      final Charset suppliedCharset,
                                      final Void extra)
        {
            return bytes;
        }
    }

    public static class URLToStringFilter
            extends URLFilter<Charset, String>
    {
        @Override
        protected String convertBytes(final byte[] bytes,
                                      final Charset suppliedCharset,
                                      final Charset charset)
                throws
                IOException
        {
            final String  str;
            final Charset actualCharset;

            if(charset == null)
            {
                if(suppliedCharset == null)
                {
                    actualCharset = StandardCharsets.UTF_8;
                }
                else
                {
                    actualCharset = suppliedCharset;
                }
            }
            else
            {
                actualCharset = charset;
            }

            str = new String(bytes,
                             actualCharset);

            return str;
        }
    }
}
