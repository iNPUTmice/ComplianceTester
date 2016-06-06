Compliance Tester for XMPP Servers
==================================

Use this tool to test your XMPP server for compatibility with various compliance suits.

You can easily mix and match various test cases to compose your own compliance suite or you can use a preexisting compliance suite.

Build with ```mvn package``` (needs Java 8)

Run with ```java -jar target/ComplianceTester-0.1.jar username@domain password```

Example output:
```
Use compliance suite 'Conversations Compliance Suite' to test conversations.im

running XEP-0115: Entity Capabilities…		PASSED
running XEP-0163: Personal Eventing Protocol…		PASSED
running Roster Versioning…		PASSED
running XEP-0280: Message Carbons…		PASSED
running XEP-0191: Blocking Command…		PASSED
running XEP-0045: Multi-User Chat…		PASSED
running XEP-0198: Stream Management…		PASSED
running XEP-0313: Message Archive Management…		PASSED
running XEP-0352: Client State Indication…		PASSED
running XEP-0363: HTTP File Upload…		PASSED
running XEP-0065: SOCKS5 Bytestreams (Proxy)…		PASSED
running XEP-0357: Push Notifications…		FAILED
passed 11/12

Conversations Compliance Suite: FAILED
```

This tool will save your accounts and passwords into a file called ```accounts.xml```. When you invoke it a second time you can omit the password.

There is also a very tiny wrapper script called ```runall.sh``` invoking that will run the compliance tester with all known accounts and save the output to ```reports/$servername```.
