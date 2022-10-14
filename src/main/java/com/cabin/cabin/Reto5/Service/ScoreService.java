package com.cabin.cabin.Reto5.Service;

import com.cabin.cabin.Reto5.Interfaz.Score;
import com.cabin.cabin.Reto5.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll(){
        return scoreRepository.getAll();
    }
    public Optional<Score> getScore(Integer id){
        return scoreRepository.getScore(id);
    }

    public Score save(Score score){
        if(score.getIdScore()==null){
            return scoreRepository.save(score);
        }else{
            Optional<Score> e = scoreRepository.getScore(score.getIdScore());
            if(e.isPresent()){
                return score;
            }else{
                return scoreRepository.save(score);
            }
        }
    }
     public Score update(Score score){
        if(score.getIdScore()!=null){
            Optional<Score> e= scoreRepository.getScore(score.getIdScore());
            if(!e.isEmpty()){

                if(score.getScore()!=null){
                    e.get().setScore(score.getScore());
                }
                if(score.getMessageText()!=null){
                    e.get().setMessageText(score.getMessageText());
                }
                
                scoreRepository.save(e.get());
                return e.get();
            }else{
                return score;
            }
        }else{
            return score;
        }
    }

    public boolean delete(int id){
        Boolean d = getScore(id).map(score -> {
            scoreRepository.delete(score);
            return true;
        }).orElse(false);
        return d;
    }

}

