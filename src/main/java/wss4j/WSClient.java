package wss4j;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.transform.StringResult;

import javax.xml.soap.MimeHeaders;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WSClient {

    WebServiceTemplate template = new WebServiceTemplate();
    String url;

    public void setInterceptors(ClientInterceptor[] interceptors) {
        if (interceptors != null)
            template.setInterceptors(interceptors);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String doPost(String srcXML) throws IOException {

        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
        messageFactory.setSoapVersion(SoapVersion.SOAP_12);
        messageFactory.afterPropertiesSet();

        Map<String, String> headers = Stream.of(
                new AbstractMap.SimpleEntry<>("SOAPAction", ""),
                new AbstractMap.SimpleEntry<>("X-Client", "WSS4jCLient")
        ).collect(Collectors.toMap(e -> e.getKey(), e-> e.getValue()));

        WebServiceMessageCallback callback = (WebServiceMessage message) -> {
            //((SoapMessage)message).setSoapAction("");
            SaajSoapMessage soapMessage = (SaajSoapMessage) message;
            MimeHeaders mimeHeader = soapMessage.getSaajMessage().getMimeHeaders();
            headers.entrySet().stream().forEach(e -> mimeHeader.setHeader(e.getKey(), e.getValue()));
        };

        StringBuilder sb = new StringBuilder();
        template.setFaultMessageResolver((WebServiceMessage webServiceMessage) -> {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            webServiceMessage.writeTo(stream);
            sb.append(new String(stream.toByteArray()));
        });

        String content = new String (Files.readAllBytes(Paths.get(srcXML)), Charset.forName("UTF-8"));

        // The afterPropertiesSet or @PostConstruct annotated method is called after an instance of class is created.
        template.afterPropertiesSet();
        StreamSource requestMessage = new StreamSource(new StringReader(content));
        StringResult responseResult = new StringResult();

        template.sendSourceAndReceiveToResult(url, requestMessage, callback, responseResult);
        sb.append(responseResult.toString());
        return sb.toString();

    }
}