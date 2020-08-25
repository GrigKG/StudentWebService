package telran.ashkelon2020.student.service;

import java.util.List;

import telran.ashkelon2020.student.dto.ScoreDTO;
import telran.ashkelon2020.student.dto.StudentDTO;
import telran.ashkelon2020.student.dto.StudentResponseDTO;
import telran.ashkelon2020.student.dto.StudentUppDTO;

public interface StudentService {
	boolean addStudent(StudentDTO studentDTO);

	StudentResponseDTO findStudent(int id);

	StudentResponseDTO deleteStudent(int id);

	StudentDTO updateStudent(int id, StudentUppDTO studentUppDTO);

	Boolean AddScoreToStudent(int id, ScoreDTO scoreDTO);

	List<StudentResponseDTO> findStudentsByName(String name);
	
	long studentQuantity(List<String> names);
	
	List<StudentResponseDTO> findStudentByExamScore(String exam, int score);
}
