package telran.ashkelon2020.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2020.student.dao.StudentRepositoryMongoDB;
import telran.ashkelon2020.student.dto.ScoreDTO;
import telran.ashkelon2020.student.dto.StudentDTO;
import telran.ashkelon2020.student.dto.StudentResponseDTO;
import telran.ashkelon2020.student.dto.StudentUppDTO;
import telran.ashkelon2020.student.dto.exception.StudentNotFoundException;
import telran.ashkelon2020.student.model.Student;

@Service
public class StudentServiceImp implements StudentService {
	@Autowired
	StudentRepositoryMongoDB studentRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public boolean addStudent(StudentDTO studentDTO) {
		//Student student = new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getPassword());
		Student student = modelMapper.map(studentDTO, Student.class);
		if(studentRepository.existsById(student.getId())) {
			return false;
		} else {
			studentRepository.save(student);
			return true;
		}
	}

	@Override
	public StudentResponseDTO findStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		return modelMapper.map(student, StudentResponseDTO.class);
			//return StudentResponseDTO.builder().id(id).name(student.getName()).scores(student.getScores()).build();
	}

	@Override
	public StudentResponseDTO deleteStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		studentRepository.delete(student);
		return modelMapper.map(student, StudentResponseDTO.class);
			//return StudentResponseDTO.builder().id(id).name(student.getName()).scores(student.getScores()).build();
	}
	@Override
	public StudentDTO updateStudent(int id, StudentUppDTO studentUppDTO) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		String name = studentUppDTO.getName();
		String password = studentUppDTO.getPassword();
		student.setName(name);
		student.setPassword(password);
		studentRepository.save(student);
		return modelMapper.map(student, StudentDTO.class); 
				//StudentDTO.builder().id(id).name(student.getName()).password(student.getPassword()).build();
	}
	@Override
	public Boolean AddScoreToStudent(int id, ScoreDTO scoreDTO) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		boolean res = student.addScore(scoreDTO.getExamName(), scoreDTO.getScore());
		studentRepository.save(student);
		return res;
	}

	@Override
	public List<StudentResponseDTO> findStudentsByName(String name) {
		return studentRepository.findByName(name)
				.map(s -> modelMapper.map(s, StudentResponseDTO.class))
				.collect(Collectors.toList());
		
		//return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
		//		.filter(s -> name.equalsIgnoreCase(s.getName()))
		//		.map(s -> modelMapper.map(s, StudentResponseDTO.class))
		//		.collect(Collectors.toList());
	}

	@Override
	public long studentQuantity(List<String> names) {
		return studentRepository.countByNameIn(names);
	}

	@Override
	public List<StudentResponseDTO> findStudentByExamScore(String exam, int score) {
		return studentRepository.findByExamAndScoreGreaterThanEqual(exam, score)
				.map(s -> modelMapper.map(s, StudentResponseDTO.class))
				.collect(Collectors.toList());
//		return studentRepository.findPleaseBy()
//				.filter(s -> s.getScores().containsKey(exam))
//				.filter(s -> s.getScores().get(exam) >= score)
//				.map(s -> modelMapper.map(s, StudentResponseDTO.class))
//				.collect(Collectors.toList());
	}

}
