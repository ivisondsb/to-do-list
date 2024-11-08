package br.com.ivisondsb.to_do_list.controllers

import br.com.ivisondsb.to_do_list.dto.CreateTaskDTO
import br.com.ivisondsb.to_do_list.dto.TaskDTO
import br.com.ivisondsb.to_do_list.services.TaskService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {
    @GetMapping
    fun getAll(): ResponseEntity<List<TaskDTO>> {
        val tasks = taskService.findAll()
        val tasksDTO = tasks.map { taskService.toDTO(it) }
        return ResponseEntity.ok(tasksDTO)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@Valid @RequestBody taskDTO: CreateTaskDTO): ResponseEntity<TaskDTO> {
        val task = taskService.fromDTO(taskDTO)
        val savedTask = taskService.save(task)
        return ResponseEntity.ok(taskService.toDTO(savedTask))
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<TaskDTO> {
        val task = taskService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(taskService.toDTO(task))
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody taskDTO: CreateTaskDTO): ResponseEntity<TaskDTO> {
        val existingTask = taskService.findById(id) ?: return ResponseEntity.notFound().build()
        val updatedTask = existingTask.copy(
            title = taskDTO.title,
            description = taskDTO.description
        )
        taskService.save(updatedTask)
        return ResponseEntity.ok(taskService.toDTO(updatedTask))
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {
        return if (taskService.findById(id) != null) {
            taskService.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}