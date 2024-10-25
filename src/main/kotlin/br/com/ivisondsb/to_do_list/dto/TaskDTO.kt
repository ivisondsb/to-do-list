package br.com.ivisondsb.to_do_list.dto

data class TaskDTO(
    val id: Long?,
    val title: String,
    val description: String?,
    val status: Int,
    val createdOn: String,
    val finishedOn: String?
)