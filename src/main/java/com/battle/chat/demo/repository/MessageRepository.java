package com.battle.chat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battle.chat.demo.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
