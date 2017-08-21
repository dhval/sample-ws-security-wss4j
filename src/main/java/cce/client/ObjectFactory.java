package cce.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private final static QName _RequestCourtCaseEventResponse_QNAME = new QName("http://jnet.state.pa.us/message/aopc/CCERequestReply/1", "RequestCourtCaseEventResponse");
    private final static QName _RequestCourtCaseEvent_QNAME = new QName("http://jnet.state.pa.us/message/aopc/CCERequestReply/1", "RequestCourtCaseEvent");
    private final static QName _RequestMetadata_QNAME = new QName("http://www.jnet.state.pa.us/niem/jnet/metadata/1", "RequestMetadata");

    @XmlElementDecl(namespace = "http://jnet.state.pa.us/message/aopc/CCERequestReply/1", name = "RequestCourtCaseEventResponse")
    public JAXBElement<CourtCaseEventResponse> createRequestCourtCaseEventResponse(CourtCaseEventResponse value) {
        return new JAXBElement<CourtCaseEventResponse>(_RequestCourtCaseEventResponse_QNAME, CourtCaseEventResponse.class, null, value);
    }

    @XmlElementDecl(namespace = "http://jnet.state.pa.us/message/aopc/CCERequestReply/1", name = "RequestCourtCaseEvent")
    public JAXBElement<CourtCaseEventRequest> createRequestCourtCaseEvent(CourtCaseEventRequest value) {
        return new JAXBElement<CourtCaseEventRequest>(_RequestCourtCaseEvent_QNAME, CourtCaseEventRequest.class, null, value);
    }
    @XmlElementDecl(namespace = "http://www.jnet.state.pa.us/niem/jnet/metadata/1", name = "RequestMetadata")
    public JAXBElement<CourtCaseEventRequest.RequestMetadataType> createRequestMetadata(CourtCaseEventRequest.RequestMetadataType value) {
        return new JAXBElement<CourtCaseEventRequest.RequestMetadataType>(_RequestMetadata_QNAME, CourtCaseEventRequest.RequestMetadataType.class, null, value);
    }
}
