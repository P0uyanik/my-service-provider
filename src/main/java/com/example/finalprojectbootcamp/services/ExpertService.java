package com.example.finalprojectbootcamp.services;


import com.example.finalprojectbootcamp.core.entities.Expert;
import org.springframework.stereotype.Service;

@Service
public interface ExpertService {
    /*1*/ void addANewExpert (Expert expert) ;
    /*2*/ int updateExpertByPassword(long id , String password);
    /*4*/  void deleteExpert (Expert expert) ;
}
