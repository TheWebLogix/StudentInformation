package com.example.studentinformation.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
@Autowired StudentRepository repo;
    public void save(Student student) {
    repo.save(student);
    }

    public List<Student> listAll() {
    return (List<Student>) repo.findAll();
    }

    public Student get(Integer id) {
        Optional<Student> result= repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        return null;
//        throw new UserNotFoundException("Could not find any users with this ID"+id);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
    public List<Student> getByKeyword(String keyword){
        return repo.findByKeyword(keyword);
    }


    public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
        return this.repo.findAll(pageable);
    }
}
