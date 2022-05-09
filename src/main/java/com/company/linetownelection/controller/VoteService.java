package com.company.linetownelection.controller;

import com.company.linetownelection.entity.Candidates;
import com.company.linetownelection.entity.ToggleElection;
import com.company.linetownelection.entity.Vote;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.querycondition.PropertyCondition;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class VoteService {
    @Autowired
    private UnconstrainedDataManager dataManager;

    @PostMapping("/status")
    public String checkVoteStatus(@RequestBody String nationalId){
        JSONObject inputJson = new JSONObject(nationalId);
        Vote status = dataManager.load(Vote.class).condition(PropertyCondition.contains("nationalId", inputJson.getString("nationalId"))).optional().orElse(null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status == null);
        return jsonObject.toString();
    }

    @PostMapping()
    public String vote(@RequestBody String voteInput){
        JSONObject inputJson = new JSONObject(voteInput);
        //find first
        Vote checkVote = dataManager.load(Vote.class).condition(PropertyCondition.equal("nationalId", inputJson.getString("nationalId"))).optional().orElse(null);
        if(checkVote != null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status","error");
            jsonObject.put("message","Already voted");
            return jsonObject.toString();
        }
        if(dataManager.load(ToggleElection.class).id(1).one().getEnable().equals(true)){
            Vote curVote = dataManager.create(Vote.class);
            curVote.setNationalId(inputJson.getString("nationalId"));
            curVote.setCandidateId(String.valueOf(inputJson.getInt("candidateId")));
            Candidates candidate = dataManager.load(Candidates.class).id(inputJson.getInt("candidateId")).optional().orElse(null);
            if(candidate != null){
                candidate.setVotedCount(candidate.getVotedCount()+1);
                dataManager.save(curVote);
                dataManager.save(candidate);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status",true);
                return jsonObject.toString();
            }else{
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status","error");
                jsonObject.put("message","Candidate not found");
                return jsonObject.toString();
            }
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status","error");
            jsonObject.put("message","Election is closed");
            return jsonObject.toString();
        }
    }


}
