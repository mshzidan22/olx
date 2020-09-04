package com.zidan.gov;



import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface GovRepository extends CrudRepository<Gov, Integer> {

}
