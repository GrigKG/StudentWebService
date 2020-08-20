package telran.ashkelon2020.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.student.dto.ScoreDTO;
import telran.ashkelon2020.student.dto.StudentDTO;
import telran.ashkelon2020.student.dto.StudentResponseDTO;
import telran.ashkelon2020.student.dto.StudentUppDTO;
import telran.ashkelon2020.student.service.StudentService;


@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("/student")
	public boolean addStudent(@RequestBody StudentDTO studentDTO) {
		return studentService.addStudent(studentDTO);
	}
	@PostMapping("/student/{id}")
	public StudentResponseDTO FindStudentById(@PathVariable Integer id) {
		return studentService.findStudent(id);
	}
	@DeleteMapping("/student/{id}")
	public StudentResponseDTO DeleteStudent(@PathVariable Integer id) {
		return studentService.deleteStudent(id);
	}
	@PutMapping("/student/{id}")
	public StudentDTO UpdateStudent(@PathVariable Integer id, @RequestBody StudentUppDTO studentUppDTO ) {
		return studentService.updateStudent(id, studentUppDTO);
	}
	@PutMapping("/score/student/{id}")
	public Boolean AddScoreToStudent(@PathVariable Integer id, @RequestBody ScoreDTO scoreDTO) {
	return studentService.AddScoreToStudent(id, scoreDTO);
	}
}
