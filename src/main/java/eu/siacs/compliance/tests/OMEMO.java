package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager;
import rocks.xmpp.extensions.disco.model.info.Identity;
import rocks.xmpp.extensions.disco.model.info.InfoNode;
import rocks.xmpp.util.concurrent.AsyncResult;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * This test checks for the availability of publish-options on the account’s PEP service.
 * publish-options allows a client to efficiently change the access model of the OMEMO key material
 * such that everyone can access it. Without publish-options OMEMO is only available to contacts with
 * mutual presence subscription.
 */
public class OMEMO extends AbstractTest {

    private static final String PUBLISH_OPTIONS = "http://jabber.org/protocol/pubsub#publish-options";
    private static final String WHITELISTED = "eu.siacs.conversations.axolotl.whitelisted";

    public OMEMO(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        ServiceDiscoveryManager manager = client.getManager(ServiceDiscoveryManager.class);
        AsyncResult<InfoNode> result = manager.discoverInformation(client.getConnectedResource().asBareJid());
        try {
            final InfoNode infoNode = result.get(10, TimeUnit.SECONDS);
            final Set<String> features = infoNode.getFeatures();
            if (!features.contains(PUBLISH_OPTIONS) && !features.contains(WHITELISTED)) {
                return Result.FAILED;
            }
            for (Identity identity : infoNode.getIdentities()) {
                if ("pep".equalsIgnoreCase(identity.getType()) && "pubsub".equalsIgnoreCase(identity.getCategory())) {
                    return Result.PASSED;
                }
            }
            return Result.FAILED;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.FAILED;
        }
    }

    @Override
    public String getName() {
        return "XEP-0384: OMEMO Encryption";
    }
}
