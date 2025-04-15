package com.project.Blackbelt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Blackbelt.Model.Documento;


@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
