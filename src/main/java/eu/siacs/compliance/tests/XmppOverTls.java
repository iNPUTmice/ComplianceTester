package eu.siacs.compliance.tests;

import de.measite.minidns.hla.ResolverApi;
import de.measite.minidns.hla.ResolverResult;
import de.measite.minidns.record.SRV;
import eu.siacs.compliance.Result;
import eu.siacs.utils.XmppDomainVerifier;
import rocks.xmpp.core.session.XmppClient;

import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.util.Arrays;

public class XmppOverTls extends AbstractTest {

    public XmppOverTls(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        final String domain = client.getDomain().getDomain();
        final SSLParameters parameters = new SSLParameters();
        parameters.setServerNames(Arrays.asList(new SNIHostName(domain)));
        try {
            final SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            ResolverResult<SRV> results = ResolverApi.INSTANCE.resolve("_xmpps-client._tcp." + domain, SRV.class);
            for(SRV record : results.getAnswers()) {
                try {
                    SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(record.name.toString(),record.port);
                    socket.setSSLParameters(parameters);
                    socket.setSoTimeout(1000);
                    socket.startHandshake();
                    final Result result;
                    if (XmppDomainVerifier.getInstance().verify(domain,socket.getSession())) {
                        result = Result.PASSED;
                    } else {
                        result = Result.FAILED;
                    }
                    socket.close();
                    return result;
                } catch (IOException e) {
                    //ignored
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.FAILED;
        }
        return Result.FAILED;
    }

    @Override
    public String getName() {
        return "XEP-0368: SRV records for XMPP over TLS";
    }
}
