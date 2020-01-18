package ch.agilesolutions.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ch.agilesolutions.boot.model.User;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

	@Transactional(timeout = 10)
	User findByUserId(Integer carId);
	
	@Transactional(timeout = 10)
	List<User> findAll();
	
	@Transactional
	<S extends User> S save(User car);
	
	void delete(User car);
}