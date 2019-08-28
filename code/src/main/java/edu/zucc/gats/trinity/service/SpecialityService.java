package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.Speciality;
import edu.zucc.gats.trinity.mapper.SpecialityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {
    @Autowired
    SpecialityMapper specialityMapper;

    public int addSpeciality(Speciality speciality){
        return specialityMapper.addSpeciality(speciality);
    }

    public int updateSpeciality(Speciality speciality){
        return specialityMapper.updateSpeciality(speciality);
    }

    public int deleteSpeciality(Speciality speciality){
        return specialityMapper.deleteSpeciality(speciality);
    }

    public List<Speciality> loadSpecByStudentId(String studentId){
        return specialityMapper.loadSpecByStudentId(studentId);
    }

}
