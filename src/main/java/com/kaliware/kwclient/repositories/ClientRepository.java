package com.kaliware.kwclient.repositories;

import com.kaliware.kwclient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
