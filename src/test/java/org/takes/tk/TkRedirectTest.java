/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.tk;

import com.google.common.base.Joiner;
import java.io.IOException;
import java.net.HttpURLConnection;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.takes.rs.RsPrint;

/**
 * Test case for {@link TkRedirect}.
 * @author Dmitry Molotchko (dima.molotchko@gmail.com)
 * @version $Id$
 * @since 0.9.1
 */
public final class TkRedirectTest {
    /**
     * Constant variable for url testing.
     */
    private static final String URL = "/about";
    /**
     * Constant variable for HTTP header testing.
     */
    private static final String LOCATION = "Location: ";
    /**
     * New line constant.
     */
    private static final String NEWLINE = "\r\n";

    /**
     * TkRedirect can create a response with url string.
     * @throws IOException If some problem inside
     */
    @Test
    public void createRedirectResponseWithURL() throws IOException {
        MatcherAssert.assertThat(
            new RsPrint(
                new TkRedirect(URL).act()
            ).print(),
            Matchers.equalTo(
                Joiner.on(NEWLINE).join(
                    "HTTP/1.1 303 See Other",
                    LOCATION + URL,
                    "",
                    ""
                )
            )
        );
    }

    /**
     * TkRedirect can create a response with HTTP status code and url string.
     * @throws IOException If some problem inside
     */
    @Test
    public void createRedirectResponseWithURLAndStatus() throws IOException {
        final int status = HttpURLConnection.HTTP_MOVED_TEMP;
        MatcherAssert.assertThat(
            new RsPrint(
                new TkRedirect(URL, status).act()
            ).print(),
            Matchers.equalTo(
                Joiner.on(NEWLINE).join(
                    "HTTP/1.1 302 Moved Temporarily",
                    LOCATION + URL,
                    "",
                    ""
                )
            )
        );
    }

}
