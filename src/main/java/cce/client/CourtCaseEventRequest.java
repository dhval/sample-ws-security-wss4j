package cce.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestCourtCaseEventType", namespace = "http://jnet.state.pa.us/message/aopc/CCERequestReply/1")
public class CourtCaseEventRequest {

    @XmlElement(name = "RequestMetadata", namespace = "http://www.jnet.state.pa.us/niem/jnet/metadata/1", required = true)
    public RequestMetadataType requestMetadata;

    @XmlElement(name = "DocketCriteria", namespace = "http://www.jnet.state.pa.us/niem/jnet/metadata/1")
    public DocketCriteriaType docket;

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "DocketCriteria", namespace = "http://www.jnet.state.pa.us/niem/jnet/metadata/1")
    public static class DocketCriteriaType {

        @XmlElement(name = "CaseDocketID", namespace = "http://niem.gov/niem/niem-core/2.0", required = true)
        public String docketId;

    }

    public void setDocketId(String identity) {
        docket = new DocketCriteriaType();
        docket.docketId = identity;
    }

    public void setChargeTrackingId(String identity) {
        chargeTracking = new ChargeTrackingCriteria();
        chargeTracking.trackingIdentification = new ChargeTrackingIdentification();
        chargeTracking.trackingIdentification.identity = identity;
    }

    public void setRequestMetadata(String requestAuthenticatedUserID, String userDefinedTrackingID, String replyToAddressURI) {
        requestMetadata = new RequestMetadataType();
        requestMetadata.replyToAddressURI = replyToAddressURI;
        requestMetadata.requestAuthenticatedUserID = requestAuthenticatedUserID;
        requestMetadata.userDefinedTrackingID = userDefinedTrackingID;
    }

    @XmlElement(name = "ChargeTrackingCriteria", required = false)
    public ChargeTrackingCriteria chargeTracking;

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ChargeTrackingCriteria {
        @XmlElement(name = "ChargeTrackingIdentification", namespace = "http://niem.gov/niem/domains/jxdm/4.0")
        public ChargeTrackingIdentification trackingIdentification;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ChargeTrackingIdentification {
        @XmlElement(name = "IdentificationID", namespace = "http://niem.gov/niem/niem-core/2.0")
        public String identity;

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "RequestMetadataType", namespace = "http://www.jnet.state.pa.us/niem/jnet/metadata/1", propOrder = {
            "userDefinedTrackingID",
            "replyToAddressURI",
            "requestActionText",
            "requestAuthenticatedUserID"
    })
    public static class RequestMetadataType {
        @XmlElement(name = "UserDefinedTrackingID", required = true)
        public String userDefinedTrackingID;
        @XmlElement(name = "ReplyToAddressURI", required = true)
        public String replyToAddressURI;
        @XmlElement(name = "RequestActionText")
        public String requestActionText;
        @XmlElement(name = "RequestAuthenticatedUserID")
        public String requestAuthenticatedUserID;

    }

}
