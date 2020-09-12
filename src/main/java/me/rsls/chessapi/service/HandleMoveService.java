package me.rsls.chessapi.service;


import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HandleMoveService {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ValidateService validateService;

    @Autowired
    private MoveExecutorService moveExecutorService;


    public Validation handleMove(String[] sourceDesignation, String[] targetDesignation){

        Field sourceField = boardService.getField(sourceDesignation);
        Field targetField = boardService.getField(targetDesignation);

        Validation validation = validateService.validateMove(sourceField, targetField);

        //if validation = true -> execute move
        if(validation.isState()) {
            moveExecutorService.executeMove(sourceField, targetField);
        }

        return validation;
    }

}
