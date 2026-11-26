
package com.studentmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.Repository.StudentRepository;
import com.studentmanagement.entity.StudentEntity;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add Student
    public StudentEntity addStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    // Get All Students
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get Student By ID
    public StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Student not found with ID: " + id));
    }

    // Update Student
    public StudentEntity updateStudent(Long id, StudentEntity student) {

        StudentEntity existingStudent = getStudentById(id);

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhone(student.getPhone());
        existingStudent.setCourse(student.getCourse());

        return studentRepository.save(existingStudent);
    }

    // Delete Student
    public void deleteStudent(Long id) {

        getStudentById(id);

        studentRepository.deleteById(id);
    }
}
