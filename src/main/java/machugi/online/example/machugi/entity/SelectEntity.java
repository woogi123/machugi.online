package machugi.online.example.machugi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import machugi.online.example.machugi.dto.SelectDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="file_table")
public class SelectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String topic;

    @Column
    private String builder;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @Column
    private int fileAttached; // 1 / 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="select_id")
    private SelectEntity selectEntity;

    @OneToMany(mappedBy = "selectEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<SelectEntity> selectEntityList = new ArrayList<>();

    public static SelectEntity toSaveEntity(SelectDTO selectDTO) {
        SelectEntity selectEntity = new SelectEntity();
        selectEntity.setId(selectEntity.getId());
        selectEntity.setTopic(selectEntity.getTopic());
        selectEntity.setBuilder(selectEntity.getBuilder());
        selectEntity.setFileAttached(0);
        return selectEntity;
    }

    public static SelectEntity toSaveFileEntity(SelectDTO selectDTO) {
        SelectEntity selectEntity = new SelectEntity();
        selectEntity.setId(selectEntity.getId());
        selectEntity.setTopic(selectEntity.getTopic());
        selectEntity.setBuilder(selectEntity.getBuilder());
        selectEntity.setFileAttached(1);
        return null;
    }

    public static SelectEntity toSelectFileEntity(SelectEntity selectEntity, String originalFileName, String storedFileName){
        SelectEntity selectFileName = new SelectEntity();
        selectEntity.setOriginalFileName(originalFileName);
        selectEntity.setStoredFileName(storedFileName);
        selectEntity.setSelectEntity(selectEntity);
        return selectEntity;
    }

}
