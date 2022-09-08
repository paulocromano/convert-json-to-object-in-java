package model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter
@ToString
public class Curso {

    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalTime horaInicio;
    private LocalDateTime dataHoraFim;
    private Set<Aluno> alunos;
}
