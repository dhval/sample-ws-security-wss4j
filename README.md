## Spring WS-Security with WSS4J

This is a working example of creating a SOAP service with X509 Token profile to sign the request using digital signatures.

Spring WSS supports two implementations of WS-Security:[WSS4J][wss4j] and [XWSS][xwss], using [ClientInterceptor][client-interceptor]:
- Wss4jSecurityInterceptor.
- XwsSecurityInterceptor.

To make this sample as minimalist as possible I am using [WSS4j][wss4j] which is more portable, additionally other details like trustsstore, SAML assertions, JAXB XML are omitted. Still this could serve as a complete demo.

Signature Identifier / Profile
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

### Creating keystore

This example will need a jks store which is NOT included, you will need to create it using keytool;[screenshot][screenshot-keystore].

### Running this sample
```
$ mvn clean spring-boot:run
```
[Output-Image][screenshot-run]

### License

The project has been released under the [MIT License][license]. Issues are managed at the GitHub[project issues tracker][issues]

[scm]: https://github.com/dhval/sample-ws-security-wss4j
[issues]: https://github.com/dhval/sample-ws-security-wss4j/issues
[license]: http://www.opensource.org/licenses/mit-license.php

[screenshot-keystore]:https://github.com/dhval/sample-ws-security-wss4j/blob/master/doc/screenshot_keystore.png
[screenshot-run]:https://github.com/dhval/sample-ws-security-wss4j/blob/master/doc/screenshot-run.png

[ws-security]: https://www.oasis-open.org/committees/wss/
[xwss]: https://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/1.6/tutorial/doc/XWS-SecurityIntro4.html
[wss4j]: https://ws.apache.org/wss4j/
[spring-ws]: http://projects.spring.io/spring-ws/
[client-interceptor]: http://docs.spring.io/spring-ws/sites/1.5/apidocs/org/springframework/ws/client/support/interceptor/class-use/ClientInterceptor.html
