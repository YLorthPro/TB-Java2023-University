package be.bstorm.exouniversite.bl.services;

import be.bstorm.exouniversite.dal.models.entities.SectionEntity;
import be.bstorm.exouniversite.pl.models.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    List<SectionEntity> getAllSections();
    void createSection(Section form);
    Optional<SectionEntity> getSectionByCode(String code);
    void updateSection(String code, Section form);
    void deleteSection(String code);
}
