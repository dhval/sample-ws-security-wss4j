package cce.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CourtCaseEventResponse", namespace = "http://jnet.state.pa.us/message/aopc/CCERequestReply/1", propOrder = {
        "code",
        "description"
})
public class CourtCaseEventResponse {
    @XmlElement(name = "ResponseStatusCode")
    public String code;
    @XmlElement(name = "ResponseStatusDescriptionText")
    public String description;

}
