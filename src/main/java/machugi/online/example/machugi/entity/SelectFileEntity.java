package machugi.online.example.machugi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "select_file_table")
public class SelectFileEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalFileName;
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private SelectEntity selectEntity;


    public static SelectFileEntity toSelectFileEntity(SelectEntity selectEntity, String originalFileName, String storedFileName){
        SelectFileEntity selectfileEntity = new SelectFileEntity();
        selectfileEntity.setOriginalFileName(originalFileName);
        selectfileEntity.setStoredFileName(storedFileName);
        selectfileEntity.setSelectEntity(selectEntity);
        return selectfileEntity;
    }

}
