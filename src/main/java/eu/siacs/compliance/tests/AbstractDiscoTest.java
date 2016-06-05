package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.util.concurrent.AsyncResult;

import java.util.List;
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
        for(String namespace : getNamespaces()) {
            AsyncResult<Boolean> result = client.isSupported(namespace, target);
            try {
                Boolean hasCarbons = result.getResult(10, TimeUnit.SECONDS);
                return hasCarbons ? Result.PASSED : Result.FAILED;
            } catch (Exception e) {
                //ignore and go to next namespace
            }
        }
        return Result.FAILED;
    }
}
