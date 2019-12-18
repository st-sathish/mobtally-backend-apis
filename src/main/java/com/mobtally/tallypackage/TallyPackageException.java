package com.mobtally.tallypackage;

public class TallyPackageException extends Exception {

    /** Serial version uid */
    private static final long serialVersionUID = -1645569283274593366L;

    /**
     * Creates a new tally package exception with the specified message.
     *
     * @param msg
     *          the error message
     */
    public TallyPackageException(String msg) {
        super(msg);
    }

    /**
     * Creates a new media package exception caused by Throwable <code>t</code>.
     *
     * @param t
     *          the original exception
     */
    public TallyPackageException(Throwable t) {
        super(t.getMessage(), t);
    }

    /**
     * Creates a new media package exception caused by Throwable <code>t</code>.
     *
     * @param msg
     *          individual error message
     * @param t
     *          the original exception
     */
    public TallyPackageException(String msg, Throwable t) {
        super(msg, t);
    }
}
