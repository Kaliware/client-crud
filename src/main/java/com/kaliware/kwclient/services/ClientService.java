package com.kaliware.kwclient.services;


import com.kaliware.kwclient.dto.ClientDTO;
import com.kaliware.kwclient.entities.Client;
import com.kaliware.kwclient.repositories.ClientRepository;
import com.kaliware.kwclient.services.exceptions.DatabaseException;
import com.kaliware.kwclient.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService{

  @Autowired
  ClientRepository repository;

  @Transactional(readOnly = true)
  public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
    Page<Client> list = repository.findAll(pageRequest);
    return list.map(x -> new ClientDTO(x));
  }

  @Transactional(readOnly = true)
  public ClientDTO findById(Long id){
    Optional<Client> obj = repository.findById(id);
    Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    return new ClientDTO(entity);
  }

  @Transactional
  public ClientDTO insert(ClientDTO dto){
    Client entity = new Client();
    //entity.setName(dto.getName());
    entity = repository.save(entity);
    return new ClientDTO(entity);
  }

  @Transactional
  public ClientDTO update(Long id, ClientDTO dto){
    try{
      Client entity = repository.getReferenceById(id);
      //entity.setName(dto.getName());
      entity = repository.save(entity);
      return new ClientDTO(entity);
    }catch(javax.persistence.EntityNotFoundException e){
      throw new ResourceNotFoundException("Id not found " + id);
    }
  }

  public void delete(Long id){
    try{
      repository.deleteById(id);
    }catch(EmptyResultDataAccessException e){
      throw new ResourceNotFoundException("Id not found " + id);
    }catch(DataIntegrityViolationException e){
      throw new DatabaseException("Integrity violation");
    }
  }
}
