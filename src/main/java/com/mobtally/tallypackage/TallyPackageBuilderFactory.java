package com.mobtally.tallypackage;

import com.mobtally.util.ConfigurationException;

public final class TallyPackageBuilderFactory {

    /** Class name for the default media package builder */
    private static final String BUILDER_CLASS = "com.mobtally.tallypackage.TallyPackageBuilderImpl";

    /** Name of the system property */
    public static final String PROPERTY_NAME = "com.mobtally.tallypackage.builder";

    /** The implementation class name */
    private static String builderClassName = BUILDER_CLASS;

    /** The singleton instance of this factory */
    private static final TallyPackageBuilderFactory factory = new TallyPackageBuilderFactory();

    private TallyPackageBuilderFactory() {
        String className = System.getProperty(PROPERTY_NAME);
        if (className != null) {
            builderClassName = className;
        }
    }

    public static TallyPackageBuilderFactory newInstance() {
        return factory;
    }

    public TallyPackageBuilder newTallyPackageBuilder() throws ConfigurationException {
        TallyPackageBuilder builder = null;
        try {
            Class<?> builderClass = Class.forName(builderClassName);
            builder = (TallyPackageBuilder) builderClass.newInstance();
        } catch (ClassNotFoundException e) {
            throw new ConfigurationException("Class not found while creating media package builder: " + e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new ConfigurationException("Instantiation exception while creating media package builder: "
                    + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ConfigurationException("Access exception while creating media package builder: " + e.getMessage(), e);
        }
        return builder;
    }
}
