package com.mobtally.tallypackage;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class TallyPackageBuilderImpl implements TallyPackageBuilder {

    private static final Logger logger = LoggerFactory.getLogger(TallyPackageBuilderImpl.class);

    /** The tally package serializer */
    protected TallyPackageSerializer serializer = null;

    /**
     * Creates a new media package builder.
     *
     * @throws IllegalStateException
     *           if the temporary directory cannot be created or is not accessible
     */
    public TallyPackageBuilderImpl() {
    }

    /**
     * Creates a new tally package builder that uses the given serializer to resolve urls while reading manifests and
     * adding new elements.
     *
     * @param serializer
     *          the media package serializer
     * @throws IllegalStateException
     *           if the temporary directory cannot be created or is not accessible
     */
    public TallyPackageBuilderImpl(TallyPackageSerializer serializer) {
        if (serializer == null)
            throw new IllegalArgumentException("Serializer may not be null");
        this.serializer = serializer;
    }

    @Override
    public TallyPackage createNew() throws TallyPackageException {
        return new TallyPackageImpl();
    }

    @Override
    public TallyPackage loadFromXml(String xml) throws TallyPackageException {
        InputStream in = null;
        try {
            in = IOUtils.toInputStream(xml, "UTF-8");
            return loadFromXml(in);
        } catch (IOException e) {
            throw new TallyPackageException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    @Override
    public TallyPackage loadFromXml(Node xml) throws TallyPackageException {
        if (serializer != null) {
            // FIXME This code runs if *any* serializer is present, regardless of the serializer implementation
            try {
                rewriteUrls(xml, serializer);
                return TallyPackageImpl.valueOf(xml);
            } catch (Exception e) {
                throw new TallyPackageException("Error deserializing paths in media package", e);
            }
        } else {
            return TallyPackageImpl.valueOf(xml);
        }
    }

    @Override
    public void setSerializer(TallyPackageSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public TallyPackageSerializer getSerializer() {
        return this.serializer;
    }

    public TallyPackage loadFromXml(InputStream is) throws TallyPackageException {
        if (serializer != null) {
            // FIXME This code runs if *any* serializer is present, regardless of the serializer implementation
            try {
                Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
                rewriteUrls(xml, serializer);
                return TallyPackageImpl.valueOf(xml);
            } catch (Exception e) {
                throw new TallyPackageException("Error deserializing paths in media package", e);
            }
        } else {
            return TallyPackageImpl.valueOf(is);
        }
    }

    /**
     * Rewrite the url elements using the serializer. Attention: This method modifies the given DOM!
     */
    private static void rewriteUrls(Node xml, TallyPackageSerializer serializer) throws XPathExpressionException,
            URISyntaxException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList) xPath.evaluate("//*[local-name() = 'url']", xml, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node uri = nodes.item(i).getFirstChild();
            if (uri != null) {
                String uriStr = uri.getNodeValue();
                String trimmedUriStr = uriStr.trim();
                /*
                 * Warn the user if trimming is necessary as this means that the URI was technically invalid.
                 */
                if (!trimmedUriStr.equals(uriStr)) {
                    logger.warn("Detected invalid URI. Trying to fix it by " + "removing spaces from beginning/end.");
                }
                uri.setNodeValue(serializer.decodeURI(new URI(trimmedUriStr)).toString());
            }
        }
    }
}
