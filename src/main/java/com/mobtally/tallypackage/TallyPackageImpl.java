package com.mobtally.tallypackage;

import com.mobtally.tallypackage.base.Envelop;
import com.mobtally.util.IoSupport;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@XmlRootElement(name = "ENVELOP")
@XmlAccessorType(XmlAccessType.NONE)
public class TallyPackageImpl implements TallyPackage {

    /** Context for serializing and deserializing */
    static final JAXBContext context;

    @XmlElement(name = "title")
    private String title = "Hello";

    static {
        try {
            context = JAXBContext.newInstance("com.mobtally.tallypackage", TallyPackageImpl.class.getClassLoader());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setTitle(String envelop) {
        this.title = envelop;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Reads the media package from the input stream.
     *
     * @param xml
     *          the input stream
     * @return the deserialized media package
     */
    public static TallyPackageImpl valueOf(InputStream xml) throws TallyPackageException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(new StreamSource(xml), TallyPackageImpl.class).getValue();
        } catch (JAXBException e) {
            throw new TallyPackageException(e.getLinkedException() != null ? e.getLinkedException() : e);
        } finally {
            IoSupport.closeQuietly(xml);
        }
    }

    @Override
    public Object clone() {
        try {
            String xml = TallyPackageParser.getAsXml(this);
            return TallyPackageBuilderFactory.newInstance().newTallyPackageBuilder().loadFromXml(xml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Unmarshals XML representation of a MediaPackage via JAXB.
     *
     * @param xml
     *          the serialized xml string
     * @return the deserialized media package
     * @throws TallyPackageException
     */
    public static TallyPackageImpl valueOf(String xml) throws TallyPackageException {
        try {
            return TallyPackageImpl.valueOf(IOUtils.toInputStream(xml, "UTF-8"));
        } catch (IOException e) {
            throw new TallyPackageException(e);
        }
    }

    /**
     * Reads the media package from an xml node.
     *
     * @param xml
     *          the node
     * @return the deserialized media package
     */
    public static TallyPackageImpl valueOf(Node xml) throws TallyPackageException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Serialize the media package
            DOMSource domSource = new DOMSource(xml);
            out = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(out);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(domSource, result);
            in = new ByteArrayInputStream(out.toByteArray());

            return unmarshaller.unmarshal(new StreamSource(in), TallyPackageImpl.class).getValue();
        } catch (Exception e) {
            throw new TallyPackageException("Error deserializing media package node", e);
        } finally {
            IoSupport.closeQuietly(in);
            IoSupport.closeQuietly(out);
        }
    }

    /**
     * A JAXB adapter that allows the {@link TallyPackage} interface to be un/marshalled
     */
    public static class Adapter extends XmlAdapter<TallyPackageImpl, TallyPackage> {
        @Override
        public TallyPackageImpl marshal(TallyPackage tp) throws Exception {
            return (TallyPackageImpl) tp;
        }

        @Override
        public TallyPackage unmarshal(TallyPackageImpl tp) throws Exception {
            return tp;
        }
    }
}
