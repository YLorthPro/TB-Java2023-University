package be.bstorm.exouniversite.bl.services;

import be.bstorm.exouniversite.dal.models.entities.SectionEntity;
import be.bstorm.exouniversite.dal.models.entities.StudentEntity;
import be.bstorm.exouniversite.pl.models.forms.SectionForm;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    List<SectionEntity> getAllSections();
    void createSection(SectionForm form);
    Optional<SectionEntity> getSectionByCode(String code);
    void updateSection(String code, SectionForm form);
    void deleteSection(String code);
    List<StudentEntity> getStudentsBySection(String id);
}
