package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stream.model.StreamFeature;

/**
 * This test is looking for the c element in a servers stream features
 * see http://xmpp.org/extensions/xep-0115.html#stream
 */
public class EntityCapabilities extends AbstractStreamFeatureTest {

    public EntityCapabilities(XmppClient client) {
        super(client);
    }

    @Override
    Class<? extends StreamFeature> getStreamFeature() {
        return rocks.xmpp.extensions.caps.model.EntityCapabilities.class;
    }

    @Override
    public String getName() {
        return "XEP-0115: Entity Capabilities";
    }
}
