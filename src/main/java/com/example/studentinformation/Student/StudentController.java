package com.example.studentinformation.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
    public class StudentController {
    @Autowired
    private StudentService service;

//shows student list
    @GetMapping("/student")
    public String viewHomePage(Model model){
//        List<Student> showStudentList = service.listAll();
//        model.addAttribute("listStudent",showStudentList);
        return findPaginated(1,"firstName","asc", model);
    }
//    @GetMapping("/student")
//    public String viewHomePage(Model model) {
//        List<Student> showStudentList = service.listAll();
//        model.addAttribute("listStudent",showStudentList);
//        return findPaginated(1, model);
//    }

    //save student information
    @PostMapping("/save")
    public String savedStudent(Student student, RedirectAttributes ra){
            service.save(student);
        return "redirect:/student";
    }
    //redirect to create new form
    @GetMapping("/student/new")
    public String newForm(Model model){
    model.addAttribute("student",new Student());
        return "form";
    }
    //edit the student info
    @GetMapping("student/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
//        try {
            Student student = service.get(id);
            model.addAttribute("student",student);
            return "form";
//        }
//        catch (UserNotFoundException e) {
//            ra.addFlashAttribute("message","The details have been saved sucessfully");
//            return "redirect:/student";
//        }
    }
    //delete the student details
    @GetMapping("student/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
            service.delete(id);
        return "redirect:/student";
    }

//    @RequestMapping(path = {"/studentSearch","/search"})
    //search the firstName of students in the data
    @RequestMapping("student/search")
    public String home(Student student, Model model, String keyword) {
        if(keyword!=null) {
            List<Student> list = service.getByKeyword(keyword);
            model.addAttribute("student", list);
            if ( list.isEmpty() ){
                return "error";
            }
        }
        return "student";
    }
//    for practice
    @RequestMapping("/helloworld")
    public String hello(Model model, String text){
        model.addAttribute("HelloThere",text);
        return "helloworld";
    }
    //    for practice

    //pagination and sorting logic
    @GetMapping("student/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField")String sortField, @RequestParam("sortDir")String sortDir , Model model) {
        int pageSize = 5;

        Page < Student > page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Student > listStudents = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listStudent", listStudents);
        return "student";
    }

}
