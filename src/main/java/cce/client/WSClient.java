package cce.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

public class WSClient extends WebServiceGatewaySupport {

    public JAXBElement<CourtCaseEventResponse> queryByDocketId(JAXBElement<CourtCaseEventRequest> request){
        return (JAXBElement<CourtCaseEventResponse>) getWebServiceTemplate()
                .marshalSendAndReceive(request);

    }
}