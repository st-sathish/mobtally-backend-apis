package com.mobtally.tallypackage;

public interface TallyPackageElement extends Comparable<TallyPackageElement>, Cloneable {

    /**
     * Create a deep copy of this object.
     *
     * @return The copy
     */
    Object clone();
}
