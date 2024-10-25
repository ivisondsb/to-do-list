package br.com.ivisondsb.to_do_list.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = 0,
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    @NotBlank
    val description: String? = null,
    @Column(name = "id_status")
    val status: Int = 1,
    @Column(name = "created_on")
    val createdOn: LocalDate = LocalDate.now(),
    @Column(name = "finished_on")
    val finishedOn: LocalDate? = null
)