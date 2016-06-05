package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager;
import rocks.xmpp.extensions.disco.model.info.Identity;
import rocks.xmpp.extensions.disco.model.info.InfoNode;
import rocks.xmpp.util.concurrent.AsyncResult;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PEP extends AbstractTest {

    public PEP(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        ServiceDiscoveryManager manager = client.getManager(ServiceDiscoveryManager.class);
        AsyncResult<InfoNode> result = manager.discoverInformation(client.getConnectedResource().asBareJid());
        try {
            Set<Identity> identies = result.getResult(10, TimeUnit.SECONDS).getIdentities();
            for(Identity identity : identies) {
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
        return "XEP-0163: Personal Eventing Protocol";
    }
}
