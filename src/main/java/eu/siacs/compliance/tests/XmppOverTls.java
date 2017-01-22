package eu.siacs.compliance.tests;

import de.measite.minidns.hla.ResolverApi;
import de.measite.minidns.hla.ResolverResult;
import de.measite.minidns.record.SRV;
import eu.siacs.compliance.Result;
import rocks.xmpp.core.session.XmppClient;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;

public class XmppOverTls extends AbstractTest {

    public XmppOverTls(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        String domain = client.getDomain().getDomain();
        try {
            final SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            ResolverResult<SRV> results = ResolverApi.INSTANCE.resolve("_xmpps-client._tcp." + domain, SRV.class);
            for(SRV record : results.getAnswers()) {
                try {
                    SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(record.name.toString(),record.port);
                    socket.setSoTimeout(1000);
                    socket.startHandshake();
                    socket.close();
                    return Result.PASSED;
                } catch (IOException e) {
                    //ignored
                }
            }
        } catch (Exception e) {
            return Result.FAILED;
        }
        return Result.FAILED;
    }

    @Override
    public String getName() {
        return "XEP-0368: SRV records for XMPP over TLS";
    }
}
