package com.mobtally.tallypackage;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

public final class TallyPackageParser {

    /**
     * Private constructor to prohibit instances of this static utility class.
     */
    private TallyPackageParser() {
        // Nothing to do
    }

    public static String getAsXml(TallyPackage tallyPackage) {
        if (tallyPackage == null)
            throw new IllegalArgumentException("Tallypackage must not be null");
        try {
            Marshaller marshaller = TallyPackageImpl.context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            StringWriter writer = new StringWriter();
            marshaller.marshal(tallyPackage, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new IllegalStateException(e.getLinkedException() != null ? e.getLinkedException() : e);
        }
    }

    public static String getAsJSON(TallyPackage tallyPackage) {
        if (tallyPackage == null) {
            throw new IllegalArgumentException("Tallypackage must not be null");
        }
        try {
            Marshaller marshaller = TallyPackageImpl.context.createMarshaller();

            Configuration config = new Configuration();
            config.setSupressAtAttributes(true);
            MappedNamespaceConvention con = new MappedNamespaceConvention(config);
            StringWriter writer = new StringWriter();
            XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer) {
                @Override
                public void writeStartElement(String prefix, String local, String uri) throws XMLStreamException {
                    super.writeStartElement("", local, "");
                }

                @Override
                public void writeStartElement(String uri, String local) throws XMLStreamException {
                    super.writeStartElement("", local, "");
                }

                @Override
                public void setPrefix(String pfx, String uri) throws XMLStreamException {
                }

                @Override
                public void setDefaultNamespace(String uri) throws XMLStreamException {
                }
            };

            marshaller.marshal(tallyPackage, xmlStreamWriter);
            return writer.toString();
        } catch (JAXBException e) {
            throw new IllegalStateException(e.getLinkedException() != null ? e.getLinkedException() : e);
        }
    }

    public static TallyPackage getFromXml(String xml) throws TallyPackageException {
        TallyPackageBuilder builder = TallyPackageBuilderFactory.newInstance().newTallyPackageBuilder();
        return builder.loadFromXml(xml);
    }

    public static void getAsXml(TallyPackage mediaPackage, OutputStream out, boolean format) throws TallyPackageException {
        try {
            Marshaller marshaller = TallyPackageImpl.context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
            marshaller.marshal(mediaPackage, out);
        } catch (JAXBException e) {
            throw new TallyPackageException(e.getLinkedException() != null ? e.getLinkedException() : e);
        }
    }

    public static String getArrayAsXml(List<TallyPackage> tallyPackages) throws TallyPackageException {
        try {
            StringBuilder builder = new StringBuilder();
            if (tallyPackages.isEmpty())
                return builder.toString();
            builder.append(getAsXml(tallyPackages.get(0)));
            for (int i = 1; i < tallyPackages.size(); i++) {
                builder.append("###");
                builder.append(getAsXml(tallyPackages.get(i)));
            }
            return builder.toString();
        } catch (Exception e) {
            if (e instanceof TallyPackageException) {
                throw (TallyPackageException) e;
            } else {
                throw new TallyPackageException(e);
            }
        }
    }

    public static List<TallyPackage> getArrayFromXml(String xml) throws TallyPackageException {
        try {
            List<TallyPackage> mediaPackages = new LinkedList<TallyPackage>();
            if (StringUtils.isBlank(xml))
                return mediaPackages;
            String[] xmlArray = xml.split("###");
            for (String xmlElement : xmlArray) {
                mediaPackages.add(getFromXml(xmlElement.trim()));
            }
            return mediaPackages;
        } catch (Exception e) {
            if (e instanceof TallyPackageException) {
                throw (TallyPackageException) e;
            } else {
                throw new TallyPackageException(e);
            }
        }
    }
}
