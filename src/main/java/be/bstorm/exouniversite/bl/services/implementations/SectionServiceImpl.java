package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.bl.services.SectionService;
import be.bstorm.exouniversite.dal.models.entities.SectionEntity;
import be.bstorm.exouniversite.dal.repositories.SectionRepository;
import be.bstorm.exouniversite.pl.models.Section;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<SectionEntity> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public void createSection(Section form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
    }

    @Override
    public Optional<SectionEntity> getSectionByCode(String code) {
        return sectionRepository.findById(code);
    }

    @Override
    public void updateSection(String code, Section form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
    }

    @Override
    public void deleteSection(String code) {
        sectionRepository.deleteById(code);
    }
}
