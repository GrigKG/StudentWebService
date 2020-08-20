package telran.ashkelon2020.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2020.student.dao.StudentRepository;
import telran.ashkelon2020.student.dto.ScoreDTO;
import telran.ashkelon2020.student.dto.StudentDTO;
import telran.ashkelon2020.student.dto.StudentResponseDTO;
import telran.ashkelon2020.student.dto.StudentUppDTO;
import telran.ashkelon2020.student.dto.exception.StudentNotFoundException;
import telran.ashkelon2020.student.model.Student;

@Service
public class StudentServiceImp implements StudentService {
	@Autowired
	StudentRepository studentRepository;

	@Override
	public boolean addStudent(StudentDTO studentDTO) {
		Student student = new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getPassword());
		return studentRepository.addStudent(student);
	}

	@Override
	public StudentResponseDTO findStudent(int id) {
		Student student = studentRepository.findStudentById(id);
		if (student == null) return null;
		else return StudentResponseDTO.builder()
				.id(id)
				.name(student.getName())
				.scores(student.getScores())
				.build();
	}

	@Override
	public StudentResponseDTO deleteStudent(int id) {
		Student student = studentRepository.deleteStudent(id);
		if (student == null) return null;
		else return StudentResponseDTO.builder()
				.id(id)
				.name(student.getName())
				.scores(student.getScores())
				.build();
	}
	@Override
	public StudentDTO updateStudent(int id, StudentUppDTO studentUppDTO) {
		Student student = studentRepository.updateStudent(id, studentUppDTO.getName(), studentUppDTO.getPassword());
		return StudentDTO.builder()
				.id(id)
				.name(student.getName())
				.password(student.getPassword())
				.build();
	}
	@Override
	public Boolean AddScoreToStudent(int id, ScoreDTO scoreDTO) {
		try {
			return studentRepository.addScore(id, scoreDTO.getExamName(), scoreDTO.getScore());
		} catch (NullPointerException e) {
			throw new StudentNotFoundException();
		}
	}

}
