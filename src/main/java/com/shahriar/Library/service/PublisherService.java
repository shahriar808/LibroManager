package com.shahriar.Library.service;

import com.shahriar.Library.entity.Publisher;
import com.shahriar.Library.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAllPublishers(){
        return publisherRepository.findAll();
    }
    public Publisher findPublisherById(Long id){
        return publisherRepository.findById(id).orElseThrow(()-> new RuntimeException("Publisher not found"));
    }
    public void savePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }
    public void deletePublisher(Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow(()-> new RuntimeException("Publisher not found"));
        publisherRepository.deleteById(publisher.getId());
    }

}
