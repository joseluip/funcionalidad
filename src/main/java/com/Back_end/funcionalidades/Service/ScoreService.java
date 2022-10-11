/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Service;

import com.Back_end.funcionalidades.Repository.ScoreRepository;
import com.Back_end.funcionalidades.Entities.Score;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jljos
 */
@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int idScore) {
        return scoreRepository.getScore(idScore);
    }

    public Score save(Score score) {
        if (score.getIdScore() == null) {
            return scoreRepository.save(score);
        } else {
            Optional<Score> j = scoreRepository.getScore(score.getIdScore());
            if (j.isPresent()) {
                return score;
            } else {
                return scoreRepository.save(score);
            }
        }
    }

    public Score update(Score k) {
        if (k.getIdScore() != null) {
            Optional<Score> tu = scoreRepository.getScore(k.getIdScore());
            if (tu.isPresent()) {
                if (k.getStars()!= null && k.getStars()<= 5) {
                    tu.get().setStars(k.getStars());
                }
                if(k.getMessageText()!=null){
                   tu.get().setMessageText(k.getMessageText());
               }
                scoreRepository.save(tu.get());
                return tu.get();
            } else {
                return k;
            }
        } else {
            return k;
        }
    }

    public boolean delete(int idScore) {
        boolean bandera = false;
        Optional<Score> t = scoreRepository.getScore(idScore);
        if (t.isPresent()) {
            scoreRepository.delete(t.get());
            bandera = true;
        }
        return bandera;
    }
}
