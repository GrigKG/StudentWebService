package telran.ashkelon2020.student.service;

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
}
