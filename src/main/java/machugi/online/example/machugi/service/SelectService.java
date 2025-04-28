package machugi.online.example.machugi.service;

import lombok.NoArgsConstructor;
import machugi.online.example.machugi.dto.SelectDTO;
import machugi.online.example.machugi.entity.SelectEntity;
import machugi.online.example.machugi.repository.SelectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@NoArgsConstructor(force = true)
public class SelectService {
    private final SelectRepository selectRepository;

    public void save(SelectDTO selectDTO) throws IOException {
        if (selectDTO.getSelectFile().isEmpty()) {
            SelectEntity selectEntity = SelectEntity.toSaveEntity(selectDTO);
            selectRepository.save(selectEntity);
        }else{
            // 첨부파일 있음
            MultipartFile selectFile = (MultipartFile) selectDTO.getSelectFile();
            String originalFilename = selectFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
            String savePath = "C/springboot_img/" + storedFileName;
            selectFile.transferTo(new File(savePath));

            SelectEntity selectEntity = SelectEntity.toSaveFileEntity(selectDTO);
            Long savedId = selectRepository.save(selectEntity).getId();
            SelectEntity select = selectRepository.findById(savedId).get();

            SelectEntity selectFileEntity = SelectEntity.toSelectFileEntity(select, originalFilename, storedFileName);
            selectRepository.save(selectEntity);
        }

    }
}
