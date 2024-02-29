package com.battle.chat.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Data
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="text_message")
    private String textMessage;

    @Column(name="sender_name")
    private String senderName;

    @Column(name="target_name")
    private String targetName;
}
