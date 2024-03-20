package tukorea.library.openAPI;

import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;

public class BookAPI {
    private final String clientId = "vcRfvLVrUcqggDSgx5oy";
    private final String clientSecret = "aAFwHD0J_z";
    private String bookDetailURL = "https://openapi.naver.com/v1/search/book_adv.xml?d_isbn=";
    private WebClient webClient = WebClient.builder().defaultHeaders(httpHeaders -> {
        httpHeaders.add("X-Naver-Client-Id",clientId);
        httpHeaders.add("X-Naver-Client-Secret",clientSecret);
    }).build();
    public BookDetail bookDetail(String isbn) throws JAXBException, IOException {
        String response = webClient.get().uri(bookDetailURL+isbn).retrieve().bodyToMono(String.class).block();
        JAXBContext jaxbContext = JAXBContext.newInstance(BookDetailXML.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(response);
        BookDetailXML bookDetailXML = (BookDetailXML) unmarshaller.unmarshal(reader);

        BookDetail item = bookDetailXML.getChannel().getItem();
        return item;
    }



}
