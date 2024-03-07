package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.NoFoundIdException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;


@Service
public class AvatarService {
    Logger logger = LoggerFactory.getLogger(AvatarService.class);
    private AvatarRepository avatarRepository;
    private StudentService studentService;


    public AvatarService(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    public Avatar uploadAvatar(Long id, MultipartFile avatarFile) throws NoFoundIdException, IOException {
        Student student = studentService.get(id);
        Path filePath = Path.of("C:\\avatar", student.toString() + "." + getExtension(avatarFile.getOriginalFilename()));

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream inputStream = avatarFile.getInputStream();
                OutputStream outputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE_NEW);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1000);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1000);
        ) {
            bufferedInputStream.transferTo(bufferedOutputStream);
            logger.info("Загрузка аватара");
        }
        Avatar avatar = avatarRepository.findByStudentId(id);
        logger.info("Поиск аватара");
        if (avatar == null) {
            avatar = new Avatar();
        }
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        logger.info("Сохранение аватара");
        return avatarRepository.save(avatar);

    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Avatar getAvatarById(Long id) {
        logger.info("Получение аватара по id");
        return avatarRepository.findByStudentId(id);
    }

    public List<Avatar> getAllAvatars(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        logger.info("Получение всех аватаров");
        return avatarRepository.findAll(pageable).getContent();
    }
}
