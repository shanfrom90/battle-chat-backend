package com.battle.chat.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.battle.chat.demo.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
