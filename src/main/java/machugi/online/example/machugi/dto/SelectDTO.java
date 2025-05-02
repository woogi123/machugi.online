package machugi.online.example.machugi.dto;


import lombok.*;
import machugi.online.example.machugi.entity.SelectEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelectDTO {

    private Long id;
    private String topic;
    private String builder;

    private MultipartFile image_file;
    private String originalFileName;
    private String storedFileName;
    private int fileAttached; //( 파일 첨부 여부 (0 / 1)



    public static SelectDTO toSelectDTO(SelectEntity selectEntity){
        SelectDTO selectDTO = new SelectDTO();
        selectDTO.setId(selectEntity.getId());
        selectDTO.setBuilder(selectEntity.getBuilder());
        selectDTO.setTopic(selectEntity.getTopic());
        if (selectEntity.getFileAttached() == 0){
            selectDTO.setFileAttached(selectEntity.getFileAttached());
        } else{
            selectDTO.setFileAttached(selectEntity.getFileAttached());
            selectDTO.setOriginalFileName(selectEntity.getSelectEntityList().get(0).getOriginalFileName());
            selectDTO.setStoredFileName(selectEntity.getSelectEntityList().get(0).getStoredFileName());


        }
        return selectDTO;
    }

}
