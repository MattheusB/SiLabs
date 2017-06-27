package br.com.lab03.repositories;

import br.com.lab03.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	
	
}
