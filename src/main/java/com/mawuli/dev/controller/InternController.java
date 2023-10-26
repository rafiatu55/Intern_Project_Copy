package com.mawuli.dev.controller;

import com.mawuli.dev.entity.Intern;
import com.mawuli.dev.input.InternInput;
import com.mawuli.dev.response.BadResponse;
import com.mawuli.dev.response.GoodResponse;
import com.mawuli.dev.response.MyResponse;
import com.mawuli.dev.service.InternService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InternController {

    private final InternService internService;

    public InternController(InternService internService) {
        this.internService = internService;
    }

    @PostMapping("/interns")
    public MyResponse store(@RequestBody InternInput internInput) throws Exception {
        try{
            Intern intern = internService.save(internInput);
            return new GoodResponse<Intern>(intern);
        } catch (Exception e){
            return new BadResponse<String>(e.getMessage());
        }
    }

    @GetMapping("/interns")
    public List<Intern> get(){
        return internService.findAll();
    }

    @PatchMapping("/interns/{intern_id}")
    public MyResponse update(@PathVariable("intern_id") Long intern_id, @RequestBody InternInput internInput){
        try{

            String response = internService.updateInternData(intern_id, internInput.generateIntern());
            return new GoodResponse<String>(response);
        } catch (Exception e){
            return new BadResponse<String>(e.getMessage());
        }
    }

    @DeleteMapping("/interns/{intern_id}")
    public MyResponse delete(@PathVariable("intern_id") Long intern_id){
        ResponseEntity<String> response = internService.delete(intern_id);
        return new GoodResponse<ResponseEntity<String>>(response);
    }

}
