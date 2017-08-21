package cce;

import cce.client.CourtCaseEventRequest;
import cce.client.CourtCaseEventResponse;
import cce.client.WSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@SpringBootApplication
public class Application {
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	public static final String NAMESPACE_URI = "http://jnet.state.pa.us/message/aopc/CCERequestReply/1";

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	CommandLineRunner lookup(@Autowired WSClient wsclient) {
		return args -> {
			if (args.length > 0) {

			}
			CourtCaseEventRequest request = new CourtCaseEventRequest();
			request.setRequestMetadata("test.user", "capa", "https://uat.captorapi.cor.state.pa.us/cce/CCEReply.svc");
			// Docket Number
			// request.setDocketId("CP-25-CR-0002884-2016");
			// Charge Tracking Id
			request.setChargeTrackingId("T4504835");
			QName qname = new QName(NAMESPACE_URI, "RequestCourtCaseEvent");
			JAXBElement<CourtCaseEventResponse> responseJAXBElement = wsclient.queryByDocketId(new JAXBElement(qname, CourtCaseEventRequest.class, request));
			CourtCaseEventResponse response = responseJAXBElement.getValue();

			LOG.info("\n\n" + response.code + "\t" + response.description + "\n");
			LOG.info("f" + args.length);
		};
	}

}
