package tukorea.library.openAPI;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rss")

public class BookDetailXML {
    @XmlElement(name = "channel")
    private Channel channel;


    @Getter
    @Setter
    @ToString
    @XmlRootElement(name = "channel")
    public static class Channel {
        private BookDetail item;


    }







}
