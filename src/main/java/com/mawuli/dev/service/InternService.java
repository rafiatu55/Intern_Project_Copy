package com.mawuli.dev.service;

import com.mawuli.dev.entity.Intern;
import com.mawuli.dev.input.InternInput;
import com.mawuli.dev.repository.InternRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternService {
    private final InternRepository internRepository;

    public InternService(InternRepository internRepository) {
        this.internRepository = internRepository;
    }

    public List<Intern> findAll() {
        return internRepository.findAll();
    }

    public Intern save(InternInput internInput) throws Exception {
        return internRepository.save(internInput.generateIntern());
    }

    public ResponseEntity<String> findById(Long id) {
        Optional<Intern> existingInternOptional = internRepository.findById(id);

        if (existingInternOptional.isPresent()) {
            internRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Intern with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Intern not found with ID: " + id);
        }
    }

    public ResponseEntity<String> delete(Long id) {
        Optional<Intern> existingInternOptional = internRepository.findById(id);

        if (existingInternOptional.isPresent()) {
            internRepository.deleteById(id);
            return new ResponseEntity<>("Intern with ID " + id + " deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Intern not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public String updateInternData(Long id, Intern intern) {
        Optional<Intern> existingInternOptional = internRepository.findById(id);

        if (existingInternOptional.isPresent()) {
            Intern existingIntern = existingInternOptional.get();
            existingIntern.setFirstName(intern.getFirstName());
            existingIntern.setSchool(intern.getSchool());
            existingIntern.setLastName(intern.getLastName());
            existingIntern.setDateOfBirth(intern.getDateOfBirth());

            internRepository.save(existingIntern);

            return "Intern data updated successfully";
        } else {
            return "No record found for the specified ID: " + id;
        }
    }
}
