## Spring WS-Security with WSS4J

This is a working example of creating a SOAP service with X509 Token profile to sign the request using digital signatures (digSig).

Spring WSS supports two implementations of WS-Security:[WSS4J][wss4j] and [XWSS][xwss], using [ClientInterceptor][client-interceptor] class.
- Wss4jSecurityInterceptor.
- XwsSecurityInterceptor.

To make this sample working yet minimalist, I am using [WSS4j][wss4j] which is more portable, additionally other details like
 trustsstore, Custom [SAML assertions][signed-custom-saml-assertion], encryption, JAXB/XJC configurations are omitted.

For customizing see; [wss4j-config][wss4j-config]. Below details are implemented in [ClientConfig.java][client-config].

Signature Identifier [Profiles][signature-identifiers]
```
// X509KeyIdentifier, DirectReference
securityInterceptor.setSecurementSignatureKeyIdentifier("DirectReference");
```

Security Actions
```
// Timestamp Signature SAMLTokenSigned SAMLTokenUnsigned
securityInterceptor.setSecurementActions("Timestamp Signature");
```

Signature Parts
```
securityInterceptor.setSecurementSignatureParts(
    "{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;" +
        "{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body"
```

[Sample WSS Outgoing Header][wss-header-sample]

### Creating keystore

This example will need a java key store (jks) file [like][screenshot-keystore] which is NOT included, you will need to create it
 using [keytool][create-keystore].

### Running this sample
```
$ mvn clean spring-boot:run
```
[Output-Image][screenshot-run]

### License

The project has been released under the [MIT License][license]. Issues and suggestions for this sample are welcome, [Tracker][issues]

[scm]: https://github.com/dhval/sample-ws-security-wss4j
[issues]: https://github.com/dhval/sample-ws-security-wss4j/issues
[license]: http://www.opensource.org/licenses/mit-license.php

[screenshot-keystore]:https://github.com/dhval/sample-ws-security-wss4j/blob/master/doc/screenshot_keystore.png
[screenshot-run]:https://github.com/dhval/sample-ws-security-wss4j/blob/master/doc/screenshot-run.png
[wss-header-sample]: https://github.com/dhval/sample-ws-security-wss4j/blob/master/doc/wss-header-sample.xml
[client-config]: https://github.com/dhval/sample-ws-security-wss4j/blob/master/src/main/java/cce/client/ClientConfig.java

[ws-security]: https://www.oasis-open.org/committees/wss/
[xwss]: https://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/1.6/tutorial/doc/XWS-SecurityIntro4.html
[wss4j]: https://ws.apache.org/wss4j/
[wss4j-config]: https://ws.apache.org/wss4j/config.html
[spring-ws]: http://projects.spring.io/spring-ws/
[client-interceptor]: https://github.com/spring-projects/spring-ws/blob/master/spring-ws-security/src/main/java/org/springframework/ws/soap/security/wss4j/Wss4jSecurityInterceptor.java
[signature-identifiers]: http://coheigea.blogspot.com/2013/03/signature-and-encryption-key.html
[create-keystore]: http://memorynotfound.com/create-public-private-keystore-client-server/
[signed-custom-saml-assertion]: http://jaminhitchcock.blogspot.com/2014/05/creating-and-validating-saml-assertions.html
