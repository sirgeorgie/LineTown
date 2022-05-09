package com.company.linetownelection.controller;

import com.company.linetownelection.entity.Candidates;
import com.company.linetownelection.entity.ToggleElection;
import com.company.linetownelection.entity.Vote;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import io.jmix.core.UnconstrainedDataManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/election")
public class ElectionService {
    @Autowired
    private UnconstrainedDataManager dataManager;

    @PostMapping("/toggle")
    public String toggleElection(@RequestBody String input){
        JSONObject inputJson = new JSONObject(input);
        ToggleElection enable = dataManager.load(ToggleElection.class).id(1).optional().orElse(null);
        if(enable != null){
            enable.setEnable(inputJson.getBoolean("enable"));
            dataManager.save(enable);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status","ok");
            jsonObject.put("enable",enable.getEnable());
            return jsonObject.toString();
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status","error");
            jsonObject.put("message","500 Server encounter");
            return jsonObject.toString();
        }
    }

    @GetMapping("/toggle")
    public String getElectionCount(){
        List<Candidates> candidate = dataManager.load(Candidates.class).all().list();
        if(candidate != null){
            JSONArray arr = new JSONArray();
            for (Candidates c : candidate){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",c.getId());
                jsonObject.put("votedCount",c.getVotedCount());
                arr.put(jsonObject);
            }
            return arr.toString();
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status","error");
            jsonObject.put("message","Candidate not found");
            return jsonObject.toString();
        }
    }

    @GetMapping("/result")
    public List<Candidates> ListResultElection(){
        List<Candidates> allCandidate = dataManager.load(Candidates.class).all().list();
        Integer total = dataManager.loadValue(
                        "select count(o) from Vote o",
                        Integer.class
                ).one();
        for(Candidates c : allCandidate){
            c.setPercentage(String.valueOf(c.getVotedCount() * 100 / total)+"%");
        }
        return allCandidate;
    }

    @GetMapping(path = "/export",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getCSVResult(){
        List<Vote> allVote = dataManager.load(Vote.class).all().list();
//        File csvOutputFile = new File(new Date().getTime()+"-Election-Result.csv");
//        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
//            allVote.stream()
//                    .map(this::convertToCSV)
//                    .forEach(pw::println);
//
//            return IOUtils.toByteArray(targetStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        File file = new File(new Date().getTime()+"-Election-Result.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = {"CandidateId", "NationalId"};
            writer.writeNext(header);

            // add data to csv
            for(Vote v : allVote){
                String[] record = {v.getCandidateId(), v.getNationalId()};
                writer.writeNext(record);
            }

            // closing writer connection
            writer.close();
            InputStream targetStream = FileUtils.openInputStream(file);
            return IOUtils.toByteArray(targetStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String[]> transform(List<Vote> VoteList){
        List<String[]> list = new ArrayList<>();
        String[] header = {"CandidateId", "NationalId"};
        list.add(header);
        for(Vote v : VoteList){
            String[] record = {v.getCandidateId(), v.getNationalId()};
            list.add(record);
        }
        return list;
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}
