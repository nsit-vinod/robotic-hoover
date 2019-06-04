package com.yoti.hoover.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.yoti.hoover.model.HooverInput;
/*
 * JPA Mongo repository to do CRUD operation on hoover input
 */
public interface HooverInputRepository extends MongoRepository<HooverInput, ObjectId> {

}
