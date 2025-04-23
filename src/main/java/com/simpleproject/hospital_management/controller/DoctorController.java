package com.simpleproject.hospital_management.controller;


import com.simpleproject.hospital_management.model.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/doctor/apis")
public class DoctorController {

    //spring boot application - takes input in the form of JSON(javascript object notation) - it is like key value pair

    // here we are storing data inside hashmap which is in memory
    Map<Integer, Doctor> doctorMapDb = new HashMap<>();
    // 101 -> {101,ajay,ajay123@gmail.com,cardio,35}
    // 102 -> {102,vijay,ajay123@gmail.com,cardio,35}
    // 103 -> {103,sanjay,ajay123@gmail.com,cardio,35}

    // @RequestBody -it takes the input from the postman or ui and assigns it to the doctor object(used for complete class objects)
    @PostMapping("/save")
    public String saveDoctor(@RequestBody Doctor doctor){
        doctorMapDb.put(doctor.getId(), doctor);
        System.out.println("doctor saved is : "+doctorMapDb);
        return "Doctor saved successfully";
    }

    @PostMapping("/saveList")
    public String saveDoctorList(@RequestBody List<Doctor> doctorList){
        for(Doctor d1:doctorList){
            doctorMapDb.put(d1.getId(), d1);
        }
        System.out.println("Doctors saved is : "+doctorMapDb);
        return "DoctorList is saved";
    }

    @GetMapping("/findAll")
    public Map<Integer, Doctor> getAllDoctors(){
        return doctorMapDb;
    }

    // @PathVariable - takes the input in API url path or endpoint
    @GetMapping("/findById/{id}")
    public Doctor getDoctorById(@PathVariable int id){
        Doctor doctor = doctorMapDb.get(id);
        return doctor;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDoctorById(@PathVariable int id){
        doctorMapDb.remove(id);
        return "Doctor deleted successfully with id : "+id;
    }
 }
