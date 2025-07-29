package com.spring.school.service;

import com.spring.school.exception.UserNotFoundException;
import com.spring.school.model.PrimeClass;
import com.spring.school.model.User;
import com.spring.school.repository.ClassRepository;
import com.spring.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ClassService {
    private final ClassRepository classRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClassService(ClassRepository classRepository, UserRepository userRepository) {
        this.classRepository = classRepository;
        this.userRepository = userRepository;
    }

    public List<PrimeClass> getAllClasses() {
        return classRepository.findAll();
    }

    public PrimeClass getClassById(Long id) {
        return classRepository.findByClassId(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
    }

    @Transactional
    public void addClass(PrimeClass primeClass) {
        classRepository.save(primeClass);
    }

    @Transactional
    public void deleteClass(int classId) {
        Optional<PrimeClass> primeClass = classRepository.findByClassId((long) classId);
        if (primeClass.isPresent()) {
            List<User> students = userRepository.findByPrimeClass(primeClass.get());
            if (students.isEmpty()) {
                classRepository.delete(primeClass.get());
                return;
            }

            List<User> studentsToUpdate = new ArrayList<>(students);

            for (User student : studentsToUpdate) {
                student.setPrimeClass(null);
                if (student.getEmail() != null) {
                    student.setConfirmEmail(student.getEmail());
                }
                if (student.getPassword() != null) {
                    student.setConfirmPassword(student.getPassword());
                }
                userRepository.save(student);
            }

            classRepository.delete(primeClass.get());
        }
    }

    @Transactional
    public void addStudentToClass(String email, Long classId) {
        User student = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(
                        "Student with email " + email + " could not be found"));


        PrimeClass primeClass = classRepository.findByClassId(classId).orElseThrow(
                () -> new RuntimeException("Class not found with ID: " + classId));

        student.setPrimeClass(primeClass);

        if (student.getEmail() != null) {
            student.setConfirmEmail(student.getEmail());
        }
        if (student.getPassword() != null) {
            student.setConfirmPassword(student.getPassword());
        }

        userRepository.save(student);
    }

    @Transactional
    public void removeStudentFromCourse(Long studentId, Long classId) {
        User student = userRepository.findByUserId(studentId).orElseThrow(
                () -> new UserNotFoundException(
                        "Student with id " + studentId + " could not be found"));

        PrimeClass primeClass = classRepository.findByClassId(classId).orElseThrow(
                () -> new RuntimeException("Class not found with ID: " + classId));

        primeClass.getPersons().remove(student);

        student.setPrimeClass(null);

        if (student.getEmail() != null) {
            student.setConfirmEmail(student.getEmail());
        }
        if (student.getPassword() != null) {
            student.setConfirmPassword(student.getPassword());
        }

        classRepository.save(primeClass);

        userRepository.save(student);
    }
}
