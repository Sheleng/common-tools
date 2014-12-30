package com.sheleng.java.commontools.codec.digest;

/**
 * DigestException
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class DigestException extends Exception {

    public DigestException() {
        super();
    }

    public DigestException(final String message) {
        super(message);
    }

    public DigestException(final Throwable cause) {
        super(cause);
    }

    public DigestException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
