package com.externalrestsrping.springbootextrernalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.externalrestsrping.springbootextrernalapi.domain.Docs;
import com.externalrestsrping.springbootextrernalapi.repository.DocsRepository;

@Service("docsService")
public class DocsService {

	
	@Autowired 
	private DocsRepository docsRepository;

	public DocsService(DocsRepository docsRepository) {
		this.docsRepository = docsRepository;
	}

	public List<Docs> findList() {
		return docsRepository.findAll();
	}
	
	public List<Docs> getAll(){
		return docsRepository.findAll();
	}
	public Iterable<Docs> list() {
		return docsRepository.findAll();
	}
}
