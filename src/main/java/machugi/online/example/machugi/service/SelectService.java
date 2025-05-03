package machugi.online.example.machugi.service;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import machugi.online.example.machugi.dto.SelectDTO;
import machugi.online.example.machugi.entity.SelectEntity;
import machugi.online.example.machugi.entity.SelectFileEntity;
import machugi.online.example.machugi.repository.SelectFileRepository;
import machugi.online.example.machugi.repository.SelectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SelectService {
    private final SelectRepository selectRepository;
    private final SelectFileRepository selectFileRepository;


    public void save(SelectDTO selectDTO) throws IOException {
        if (selectDTO.getCover_img().isEmpty()) {
            SelectEntity selectEntity = SelectEntity.toSaveEntity(selectDTO);
            selectRepository.save(selectEntity);
        }else {
            // 첨부파일 있음
            MultipartFile selectFile = selectDTO.getCover_img();
            String originalFilename = selectFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
            String savePath = "C:/springboot_img/" + storedFileName;

            selectFile.transferTo(new File(savePath));

            SelectEntity selectEntity = SelectEntity.toSaveFileEntity(selectDTO);
            Long saveId = selectRepository.save(selectEntity).getId();
            SelectEntity select = selectRepository.findById(saveId).get();

            SelectFileEntity selectfileEntity = SelectFileEntity.toSelectFileEntity(select, originalFilename, storedFileName);
            selectFileRepository.save(selectfileEntity);


        }
    }

    @Transactional
    public List<SelectDTO> findAll(){
        List<SelectEntity> selectEntityList = selectRepository.findAll();
        System.out.println("▶ [1] 조회된 SelectEntity 개수: " + selectEntityList.size());
        List<SelectDTO> selectDTOList = new ArrayList();
        for(SelectEntity selectEntity: selectEntityList){
            selectDTOList.add(SelectDTO.toSelectDTO(selectEntity));
        }
        return selectDTOList;
    }
}
