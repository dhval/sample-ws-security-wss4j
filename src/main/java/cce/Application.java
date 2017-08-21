package cce;

import cce.client.CourtCaseEventRequest;
import cce.client.CourtCaseEventResponse;
import cce.client.WSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.UUID;

@SpringBootApplication
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    public static final String NAMESPACE_URI = "http://jnet.state.pa.us/message/aopc/CCERequestReply/1";

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    @Value("${client.ws.user}")
    String authenticatedUserId;

    @Value("${client.ws.reply}")
    String replyURI;

    @Value("${client.ws.docketId}")
    String docketId;

    @Value("${client.ws.trackingId}")
    String trackingId;

    @Bean
    CommandLineRunner lookup(@Autowired WSClient wsclient) {
        return args -> {
            CourtCaseEventRequest request = new CourtCaseEventRequest();
            // Request metadata
            request.setRequestMetadata(authenticatedUserId, UUID.randomUUID().toString(), replyURI);
            // Docket Number or Charge Tracking Id
            if (!StringUtils.isEmpty(docketId))
                request.setDocketId(docketId);
            else
                request.setChargeTrackingId(trackingId);

            LOG.info("\n\n" + request + "\n");

            QName qname = new QName(NAMESPACE_URI, "RequestCourtCaseEvent");
            JAXBElement<CourtCaseEventResponse> responseJAXBElement = wsclient.queryByDocketId(new JAXBElement(qname, CourtCaseEventRequest.class, request));
            CourtCaseEventResponse response = responseJAXBElement.getValue();

            LOG.info("\n\n" + response + "\n");
        };
    }

}
