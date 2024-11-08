package br.com.ivisondsb.to_do_list.dto

import jakarta.validation.constraints.NotBlank

data class CreateTaskDTO(
    @field:NotBlank(message = "Task title is required")
    val title: String,
    val description: String?
)