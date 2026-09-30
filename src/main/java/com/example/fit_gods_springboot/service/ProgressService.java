package com.example.fit_gods_springboot.service;

import com.example.fit_gods_springboot.model.Progress;
import com.example.fit_gods_springboot.repository.ProgressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public Progress save(Progress progress){
        return progressRepository.save(progress);
    }

    public List<Progress> findAll(){
        return progressRepository.findAll();
    }

    public Optional<Progress> findById(Long id){
        return progressRepository.findById(id);
    }

    public List<Progress> findByUserId(Long id){
        return progressRepository.findByUserId(id);
    }

    public Progress update(Progress progress){
        return  progressRepository.save(progress);
    }

    public void deleteById(Long id){
        progressRepository.deleteById(id);
    }
}
