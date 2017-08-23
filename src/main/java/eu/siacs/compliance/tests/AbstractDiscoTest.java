package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import eu.siacs.utils.TestUtils;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager;
import rocks.xmpp.util.concurrent.AsyncResult;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class AbstractDiscoTest extends AbstractTest {

    public AbstractDiscoTest(XmppClient client) {
        super(client);
    }

    //test will succeed if any namespace matches
    abstract List<String> getNamespaces();

    abstract boolean checkOnServer();

    @Override
    public Result run() {
        Jid target = checkOnServer() ? Jid.of(client.getConnectedResource().getDomain()) : client.getConnectedResource().asBareJid();
        final ServiceDiscoveryManager serviceDiscoveryManager = client.getManager(ServiceDiscoveryManager.class);
        try {
            Set<String> features = serviceDiscoveryManager.discoverInformation(target).getResult().getFeatures();
            return TestUtils.hasAnyone(getNamespaces(),features) ? Result.PASSED : Result.FAILED;
        } catch (XmppException e) {
            return Result.FAILED;
        }
    }
}
