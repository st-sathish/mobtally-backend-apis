package com.mobtally.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class IoSupport {

    private IoSupport() {

    }

    /**
     * Closes a <code>Closable</code> quietly so that no exceptions are thrown.
     *
     * @param s
     *          maybe null
     */
    public static boolean closeQuietly(final Closeable s) {
        if (s == null) {
            return false;
        }
        try {
            s.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Closes the processes input, output and error streams.
     *
     * @param process
     *          the process
     * @return <code>true</code> if the streams were closed
     */
    public static boolean closeQuietly(final Process process) {
        if (process != null) {
            closeQuietly(process.getInputStream());
            closeQuietly(process.getErrorStream());
            closeQuietly(process.getOutputStream());
            return true;
        }
        return false;
    }

    /**
     * Extracts the content from the given input stream. This method is intended to faciliate handling of processes that
     * have error, input and output streams.
     *
     * @param is
     *          the input stream
     * @return the stream content
     */
    public static String getOutput(InputStream is) {
        InputStreamReader bis = new InputStreamReader(is);
        StringBuffer outputMsg = new StringBuffer();
        char[] chars = new char[1024];
        try {
            int len = 0;
            try {
                while ((len = bis.read(chars)) > 0) {
                    outputMsg.append(chars, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                }
        }
        return outputMsg.toString();
    }
}
