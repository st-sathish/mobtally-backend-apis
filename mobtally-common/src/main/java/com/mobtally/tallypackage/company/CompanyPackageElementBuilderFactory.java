package com.mobtally.tallypackage.company;

import com.mobtally.util.ConfigurationException;

public final class CompanyPackageElementBuilderFactory {

    /** The implementation class name */
    private static String builderClassName = "com.mobtallyt.tallypackage.company.CompanyPackageElementBuilderImpl";

    /** The singleton instance of this factory */
    private static final CompanyPackageElementBuilderFactory factory = new CompanyPackageElementBuilderFactory();

    /** The default builder implementation */
    private CompanyPackageElementBuilder builder = null;

    /**
     * Returns an instance of a MediaPackageElementBuilderFactory.
     *
     * @return the media package element builder factory
     * @throws ConfigurationException
     *           if the factory cannot be instantiated
     */
    public static CompanyPackageElementBuilderFactory newInstance() throws ConfigurationException {
        return factory;
    }

    /**
     * Factory method that returns an instance of a media package element builder.
     * <p>
     * It uses the following ordered lookup procedure to determine which implementation of the
     * {@link CompanyPackageElementBuilder} interface to use:
     * <ul>
     * <li>Platform default implementation</li>
     * </ul>
     *
     * @return the media package element builder
     * @throws ConfigurationException
     *           If the builder cannot be instantiated
     */
    public CompanyPackageElementBuilder newElementBuilder() throws ConfigurationException {
        if (builder == null) {
            try {
                Class<?> builderClass = Class.forName(builderClassName, true,
                        CompanyPackageElementBuilderFactory.class.getClassLoader());
                builder = (CompanyPackageElementBuilder) builderClass.newInstance();
            } catch (ClassNotFoundException e) {
                throw new ConfigurationException("Class not found while creating element builder: " + e.getMessage(), e);
            } catch (InstantiationException e) {
                throw new ConfigurationException("Instantiation exception while creating element builder: " + e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new ConfigurationException("Access exception while creating element builder: " + e.getMessage(), e);
            } catch (Exception e) {
                throw new ConfigurationException("Exception while creating element builder: " + e.getMessage(), e);
            }
        }
        return builder;
    }
}
