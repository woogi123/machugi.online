package machugi.online.example.machugi.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelectDTO {

    private String id;
    private String topic;
    private String builder;

    private MultipartFile image_file;
    private String originalFileName;
    private String storedFileName;
    private int fileAttached; //( 파일 첨부 여부 (0 / 1)

    public CharSequence getSelectFile() {
        return null;
    }
}
