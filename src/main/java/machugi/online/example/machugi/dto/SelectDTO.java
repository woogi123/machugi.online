package machugi.online.example.machugi.dto;

import lombok.*;
import machugi.online.example.machugi.entity.SelectEntity;
import machugi.online.example.machugi.entity.SelectFileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelectDTO {

    private Long id;
    private String topic;
    private String builder;

    private MultipartFile cover_img;  // 입력 시 사용
    private List<MultipartFile> ingame_img;

    private String originalFileName;
    private String storedFileName;
    private int fileAttached;

    public SelectDTO(Long id, String topic, String builder){
        this.id = id;
        this.topic = topic;
        this.builder = builder;
    }

    // SelectEntity → SelectDTO 변환
    public static SelectDTO toSelectDTO(SelectEntity selectEntity) {
        SelectDTO selectDTO = new SelectDTO();
        selectDTO.setId(selectEntity.getId());
        selectDTO.setTopic(selectEntity.getTopic());
        selectDTO.setBuilder(selectEntity.getBuilder());
        selectDTO.setFileAttached(selectEntity.getFileAttached());

        if(selectEntity.getFileAttached()==0){
            selectDTO.setFileAttached(selectEntity.getFileAttached());
        }else{
            selectDTO.setFileAttached(selectEntity.getFileAttached());
            selectDTO.setOriginalFileName(selectEntity.getSelectFileEntityList().get(0).getOriginalFileName());
            selectDTO.setStoredFileName(selectEntity.getSelectFileEntityList().get(0).getStoredFileName());
        }
        return selectDTO;
    }
}
