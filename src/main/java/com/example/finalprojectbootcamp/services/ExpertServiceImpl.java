package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.repositories.ExpertRepository;

public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository ;

    public ExpertServiceImpl(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }
    @Override
    public void addANewExpert(Expert expert) {
        expertRepository.save(expert) ;
    }
}
