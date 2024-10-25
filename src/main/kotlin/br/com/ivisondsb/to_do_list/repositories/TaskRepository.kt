package br.com.ivisondsb.to_do_list.repositories

import br.com.ivisondsb.to_do_list.models.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>