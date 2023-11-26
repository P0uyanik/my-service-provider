package com.example.finalprojectbootcamp.services;

import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.repositories.ExpertRepository;
import com.example.finalprojectbootcamp.util.myExceptions.MyExceptions;

public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository ;

    public ExpertServiceImpl(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }
    @Override
    public void addANewExpert(Expert expert) {
        expertRepository.save(expert) ;
    }

    @Override
    public int updateExpertByPassword(long id ,String password) {
        return expertRepository.updateExpertByPassword(id , password) ;
    }

    @Override
    public void deleteExpert(Expert expert) {
        Expert expertByIdy= expertRepository.findExpertById(expert.getId());
        MyExceptions.isExpertExists(expertByIdy) ;
        expertRepository.delete(expertByIdy);
    }
}
