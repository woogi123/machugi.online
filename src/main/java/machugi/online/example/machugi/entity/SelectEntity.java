package machugi.online.example.machugi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import machugi.online.example.machugi.dto.SelectDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name="select_table")
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


    @OneToMany(mappedBy = "selectEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<SelectFileEntity> selectFileEntityList = new ArrayList<>();

    public static SelectEntity toSaveEntity(SelectDTO selectDTO) {
        SelectEntity selectEntity = new SelectEntity();
        selectEntity.setTopic(selectDTO.getTopic());
        selectEntity.setBuilder(selectDTO.getBuilder());
        selectEntity.setFileAttached(0);
        return selectEntity;
    }

    public static SelectEntity toSaveFileEntity(SelectDTO selectDTO) {
        SelectEntity selectEntity = new SelectEntity();
        selectEntity.setTopic(selectDTO.getTopic());
        selectEntity.setBuilder(selectDTO.getBuilder());
        selectEntity.setFileAttached(1);
        return selectEntity;
    }


}
