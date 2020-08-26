package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.FigureType;
import org.springframework.stereotype.Service;

@Service
public class CheckService {


    public void validateCheck(Board board){

//        board.getCheck().setCheck(true);

        if(board.getFieldFromMatrix("a",5).getFigure() != null &&
                board.getFieldFromMatrix("a",5).getFigure().getFigureType().equals(FigureType.QUEEN)){
            board.getCheck().setCheck(true);
        }



    }





}
