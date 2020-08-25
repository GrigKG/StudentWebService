package telran.ashkelon2020.student.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import telran.ashkelon2020.student.model.Student;

public interface StudentRepositoryMongoDB extends PagingAndSortingRepository<Student, Integer> {
	Stream<Student> findByName(String name);
	
	//Stream<Student> findByNameIn(List<String> names);
	long countByNameIn(List<String> names);
	
	
	Stream<Student> findPleaseBy();
	
	@Query("{'scores.?0':{'$gte':?1}}")
	Stream<Student> findByExamAndScoreGreaterThanEqual(String exam, int score);

	
}
