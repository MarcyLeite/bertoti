package com.magi.secondfloor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magi.secondfloor.entity.Move;
import com.magi.secondfloor.repository.MoveInterface;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class MoveService {
    
    @Autowired
    private MoveInterface moveInterface;

    public List<Move> getAll() {
        return moveInterface.findAll();
    }

    public Optional<Move> getById(BigInteger id) {
        return moveInterface.findById(id);
    }

    public List<Move> search(
        String name,
        String type,
        String category,
        Integer power,
        Integer accuracy,
        Integer pp,
        String description
    ) {
        return moveInterface.findBySearch(
            name,
            type,
            category,
            power,
            accuracy,
            pp,
            description
        );
    }

    public Move save(Move move) {
        return moveInterface.save(move);
    }

    public Move update(BigInteger id, Move newMove) {
        Optional<Move> optionalMove = moveInterface.findById(id);
        if (!optionalMove.isPresent()) { return null; }
        
        Move move = optionalMove.get();

        String newName = newMove.getName();
        String newType = newMove.getType();
        String newCategory = newMove.getCategory();
        Integer newPower = newMove.getPower();
        Integer newAccuracy = newMove.getAccuracy();
        Integer newPp = newMove.getPp();
        String newDescription = newMove.getDescription();

        if(newName != null) {
            move.setName(newName);
        }
        if(newType != null) {
            move.setType(newType);
        }
        if(newCategory != null) {
            move.setCategory(newCategory);
        }
        if(newPower != null) {
            move.setPower(newPower);
        }
        if(newAccuracy != null) {
            move.setAccuracy(newAccuracy);
        }
        if(newPp != null) {
            move.setPp(newPp);
        }
        if(newDescription != null) {
            move.setDescription(newDescription);
        }

        return moveInterface.save(move);
    }

    public void delete(BigInteger id) {
        moveInterface.deleteById(id);
    }
}
