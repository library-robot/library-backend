package tukorea.library.controller;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tukorea.library.DTO.BookLocatedDTO;
import tukorea.library.DTO.LocatedInfo;
import tukorea.library.domain.DisplacedBook;
import tukorea.library.service.DisplacedBookService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class DisplacedBookController {
    DisplacedBookService displacedBookService;
    @Autowired
    public DisplacedBookController(DisplacedBookService displacedBookService) {
        this.displacedBookService = displacedBookService;
    }

    @GetMapping("/book/displaced-book")
    public List<DisplacedBook> displacedBookList(){
        return displacedBookService.findDisplacedBookList();
    }
    @PostMapping("/book/located-book")
    public List<LocatedInfo> isLocatedBook(@RequestBody BookLocatedDTO bookLocatedDTO) {
        System.out.println(bookLocatedDTO.toString());
        return displacedBookService.checkBookLocation(bookLocatedDTO);
//        return null;
    }
    @PostMapping(value = "/book/displaced-image" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void displacedImage(@RequestParam("file")MultipartFile image,
                               @RequestParam("id") Integer id) {
        if (image == null || image.isEmpty()) {
            // 파일이 없을 경우의 처리 로직
            System.out.println("파일 빔");
            return;
        }
        try {
            System.out.println(id);
            // 저장할 디렉토리
            String destDir = "D:/spring/library/src/main/resources/static/";
            File destFile = new File(destDir + "test"+id+".jpg");
            // 파일 저장
            image.transferTo(destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
