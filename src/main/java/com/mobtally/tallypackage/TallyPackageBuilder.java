package com.mobtally.tallypackage;

import org.w3c.dom.Node;

import java.io.InputStream;

public interface TallyPackageBuilder {

    /**
     * Creates a new media package in the temporary directory defined by the java runtime property
     * <code>java.io.tmpdir</code>.
     *
     * @return the new media package
     * @throws TallyPackageException
     *           if creation of the new media package fails
     */
    TallyPackage createNew() throws TallyPackageException;


    /**
     * Loads a media package from the manifest.
     *
     * @param is
     *          the media package manifest input stream
     * @return the media package
     * @throws TallyPackageException
     *           if loading of the media package fails
     */
    TallyPackage loadFromXml(InputStream is) throws TallyPackageException;

    /**
     * Loads a media package from the manifest.
     *
     * @param xml
     *          the media package manifest as an xml string
     * @return the media package
     * @throws TallyPackageException
     *           if loading of the media package fails
     */
    TallyPackage loadFromXml(String xml) throws TallyPackageException;

    /**
     * Loads a media package from the manifest.
     *
     * @param xml
     *          the media package manifest as an xml node
     * @return the media package
     * @throws TallyPackageException
     *           if loading of the media package fails
     */
    TallyPackage loadFromXml(Node xml) throws TallyPackageException;

    /**
     * Sets the media package serializer that is used to resolve urls and helps in serialization and deserialization of
     * media package elements.
     *
     * @param serializer
     *          the serializer
     */
    void setSerializer(TallyPackageSerializer serializer);

    /**
     * Returns the currently active serializer. The serializer is used to resolve urls and helps in serialization and
     * deserialization of media package elements.
     *
     * @return the serializer
     * @see #setSerializer(TallyPackageSerializer)
     */
    TallyPackageSerializer getSerializer();
}
