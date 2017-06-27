package br.com.lab03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lab03.models.SubTask;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Integer>{

}
