package com.kaliware.kwclient.dto;

import com.kaliware.kwclient.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO implements Serializable{
  private static final long serialVersionUID = 1L;
  private Long id;
  private String name;
  private String cpf;
  private Double income;
  private Instant birthDate;
  private Integer children;

  public ClientDTO(Client entity){
    this.id = entity.getId();
    this.name = entity.getName();
    this.cpf = entity.getCpf();
    this.income = entity.getIncome();
    this.birthDate = entity.getBirthDate();
    this.children = entity.getChildren();
  }

}