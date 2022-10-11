/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Service;

import com.Back_end.funcionalidades.Repository.MessageRepository;
import com.Back_end.funcionalidades.Entities.Message;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jljos
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int idMessage) {
        return messageRepository.getMessage(idMessage);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);
        } else {
            Optional<Message> j = messageRepository.getMessage(message.getIdMessage());
            if (j.isPresent()) {
                return message;
            } else {
                return messageRepository.save(message);
            }
        }
    }

    public Message update(Message k) {
        if (k.getIdMessage() != null) {
            Optional<Message> tu = messageRepository.getMessage(k.getIdMessage());
            if (tu.isPresent()) {
                if (k.getMessageText() != null) {
                    tu.get().setMessageText(k.getMessageText());
                }
                messageRepository.save(tu.get());
                return tu.get();
            }
        }
        return k;
    }

    public boolean delete(int idMessage) {
        boolean bandera = false;
        Optional<Message> t = messageRepository.getMessage(idMessage);
        if (t.isPresent()) {
            messageRepository.delete(t.get());
            bandera = true;
        }
        return bandera;
    }
}
