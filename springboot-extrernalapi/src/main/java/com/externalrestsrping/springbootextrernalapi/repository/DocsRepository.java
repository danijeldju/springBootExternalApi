package com.externalrestsrping.springbootextrernalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.externalrestsrping.springbootextrernalapi.domain.Docs;

@Repository("docsRepository")
public interface DocsRepository  extends JpaRepository<Docs, Integer>{

}
