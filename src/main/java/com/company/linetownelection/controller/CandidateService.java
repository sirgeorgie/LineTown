package com.company.linetownelection.controller;

import com.company.linetownelection.entity.Candidates;
import io.jmix.core.Id;
import io.jmix.core.UnconstrainedDataManager;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CandidateService {
    @Autowired
    private UnconstrainedDataManager dataManager;

    @GetMapping("/candidates")
    public List<Candidates> getListCandidate(){
        return dataManager.load(Candidates.class).all().list();
    }

    @GetMapping("/candidates/{id}")
    public Candidates getOneCandidate(@PathVariable Long id){
        return dataManager.load(Candidates.class).id(id).optional().orElse(null);
    }

    @PostMapping("/candidates")
    public Candidates createCandidate(@RequestBody Candidates newCandidate){
        Candidates candidates = dataManager.create(Candidates.class);
        candidates.setName(newCandidate.getName());
        candidates.setDob(newCandidate.getDob());
        candidates.setBioLink(newCandidate.getBioLink());
        candidates.setImageLink(newCandidate.getImageLink());
        candidates.setPolicy(newCandidate.getPolicy());
        return dataManager.save(candidates);
    }

    @PutMapping("/candidates/{id}")
    public Candidates updateCandidate(@RequestBody Candidates updateCandidate, @PathVariable Long id){
        Candidates candidate = dataManager.load(Candidates.class).id(id).optional().orElse(null);
        candidate.setName(updateCandidate.getName());
        candidate.setBioLink(updateCandidate.getBioLink());
        candidate.setDob(updateCandidate.getDob());
        candidate.setImageLink(updateCandidate.getImageLink());
        candidate.setPolicy(updateCandidate.getPolicy());
        return dataManager.save(candidate);
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/candidates/{id}")
    public String deleteCandidate(@PathVariable Long id){
        try{
            if(dataManager.load(Candidates.class).id(id).optional().orElse(null)!=null){
                dataManager.remove(Id.of(id.intValue(),Candidates.class));
                JSONObject json = new JSONObject();
                json.put("status","ok");
                return json.toString();
            }else{
                JSONObject json = new JSONObject();
                json.put("status","error");
                json.put("message","Candidate not found");
                return json.toString();
            }
        }catch (Exception e){
            JSONObject json = new JSONObject();
            json.put("status","error");
            json.put("message","Candidate not found");
            return json.toString();
        }
    }
}
