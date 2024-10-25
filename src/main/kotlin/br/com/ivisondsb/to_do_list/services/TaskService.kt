package br.com.ivisondsb.to_do_list.services

import br.com.ivisondsb.to_do_list.dto.CreateTaskDTO
import br.com.ivisondsb.to_do_list.dto.TaskDTO
import br.com.ivisondsb.to_do_list.models.Task
import br.com.ivisondsb.to_do_list.repositories.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {

    fun toDTO(task: Task): TaskDTO {
        return TaskDTO(
            id = task.id,
            title = task.title,
            description = task.description,
            status = task.status,
            createdOn = task.createdOn.toString(),
            finishedOn = task.finishedOn?.toString()
        )
    }

    fun fromDTO(taskDto: CreateTaskDTO): Task {
        return Task(
            title = taskDto.title,
            description = taskDto.description
        )
    }

    fun save(task: Task): Task = taskRepository.save(task)
    fun findAll(): List<Task> = taskRepository.findAll()
    fun findById(id: Long): Task? = taskRepository.findById(id).orElse(null)
    fun deleteById(id: Long) = taskRepository.deleteById(id)
}