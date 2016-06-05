package eu.siacs.compliance.extensions.csi;

import rocks.xmpp.core.stream.model.StreamFeature;

import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;

@XmlRootElement(name="csi")
public class ClientStateIndication extends StreamFeature {

    public static final String NAMESPACE = "urn:xmpp:csi:0";
}
