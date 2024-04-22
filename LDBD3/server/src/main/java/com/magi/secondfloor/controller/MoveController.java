package com.magi.secondfloor.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.magi.secondfloor.entity.Move;
import com.magi.secondfloor.services.MoveService;

@RestController
public class MoveController {
    @Autowired
    private MoveService moveService;

    @GetMapping("/moves")
    public ResponseEntity<List<Move>> getAll() {
        try {
            List<Move> moveList = moveService.getAll();
            {
                if (moveList.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(moveList, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/moves/{id}")
    public ResponseEntity<Move> getById(@PathVariable BigInteger id) {
        try {
            Optional<Move> move = moveService.getById(id);
            if (move.isPresent()) {
                return new ResponseEntity<>(move.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/moves/search")
    public ResponseEntity<List<Move>> search(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) Integer power,
        @RequestParam(required = false) Integer accuracy,
        @RequestParam(required = false) Integer pp,
        @RequestParam(required = false) String description
    ) {
            List<Move> moveList = moveService.search(
                name,
                type,
                category,
                power,
                accuracy,
                pp,
                description
            );
            return new ResponseEntity<>(moveList, HttpStatus.OK);
            

    }

    @PostMapping("/moves/save")
    public Move save(@RequestBody Move move) {
        return moveService.save(move);
    }

    @PutMapping("/moves/update/{id}")
    public ResponseEntity<Move> update(@PathVariable BigInteger id, @RequestBody Move move) {
        try {
            Move updatedMove = moveService.update(id, move);
            if (updatedMove != null) {
                return new ResponseEntity<>(updatedMove, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/moves/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable BigInteger id) {
        try {
            moveService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
