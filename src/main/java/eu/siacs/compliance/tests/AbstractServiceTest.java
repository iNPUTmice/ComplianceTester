package eu.siacs.compliance.tests;


import eu.siacs.compliance.Result;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager;
import rocks.xmpp.extensions.disco.model.items.Item;
import rocks.xmpp.util.concurrent.AsyncResult;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractServiceTest extends AbstractTest {

    public AbstractServiceTest(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        ServiceDiscoveryManager manager = client.getManager(ServiceDiscoveryManager.class);
        AsyncResult<List<Item>> result = manager.discoverServices(getNamespace());
        try {
            return result.getResult(10, TimeUnit.SECONDS).size() >= 1 ? Result.PASSED : Result.FAILED;
        } catch (Exception e) {
           return Result.FAILED;
        }
    }

    public abstract String getNamespace();
}
