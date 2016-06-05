package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stream.StreamFeaturesManager;
import rocks.xmpp.core.stream.model.StreamFeature;

import java.util.Map;

public abstract class AbstractStreamFeatureTest extends AbstractTest {

    public AbstractStreamFeatureTest(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        Map<Class<? extends StreamFeature>, StreamFeature> features = client.getManager(StreamFeaturesManager.class).getFeatures();
        if (features.containsKey(getStreamFeature())) {
            return Result.PASSED;
        } else {
            return Result.FAILED;
        }
    }

    abstract Class<? extends StreamFeature> getStreamFeature();
}
