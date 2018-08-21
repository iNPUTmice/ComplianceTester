Compliance Tester for XMPP Servers
==================================

Use this tool to test your XMPP server for compatibility with various compliance suites.

You can easily mix and match various test cases to compose your own compliance suite or you can use a preexisting compliance suite.

## Why compliance?

XMPP is an extensible and living standard. Requirments shift over time and thus new extensions (called XEPs) get developed. While server implementors usually react quite fast and are able to cater to those needs it's the server operators who don't upgrade to the latest version or don't enable certain features.

Picking the right extensions to implement or enable isn't always easy. For this reason the XSF has published [XEP-0387 XMPP Compliance Suites 2018](https://xmpp.org/extensions/xep-0387.html) listing the most important extensions to date.

This tool helps you to asses if your server supports those compliance profiles. It will also test for the slightly more comprehensive *Conversations compliance suite*. [Conversations](https://conversations.im) is the technology leader in mobile XMPP instant messaging and aims to provide its user with an experience that is a par with proprietary instant messaging solutions.

## Usage

Download the compiled [ComplianceTester-0.2.3.jar](https://gultsch.de/files/ComplianceTester-0.2.3.jar) or build with ```mvn package``` (needs Java 8)

Run with ```java -jar target/ComplianceTester-0.2.3.jar username@domain password```

(For [some Java versions, the command fails with `Exception in thread "main" java.lang.NoClassDefFoundError: javax/xml/bind/JAXBException`](https://stackoverflow.com/questions/43574426/how-to-resolve-java-lang-noclassdeffounderror-javax-xml-bind-jaxbexception-in-j). Work around this by adding `--add-modules java.xml.bind` to the command line options.)

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

## Reports
Full reports can be found in the directory ```reports```. Please create a pull request if you want your server to be included.

Submitted data can be processed and [displayed in a table](https://conversations.im/compliance/).
