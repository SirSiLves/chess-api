package me.rsls.chessapi.controller;

import me.rsls.chessapi.model.ClickedField;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.validation.ValidFields;
import me.rsls.chessapi.service.BoardService;
import me.rsls.chessapi.service.GameService;
import me.rsls.chessapi.service.ValidFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/validFields")
public class ValidFieldsController {

    @Autowired
    private ValidFieldService validFieldService;

    @Autowired
    private GameService gameService;

    //TODO POST?

    @RequestMapping(value = "getFields", method = RequestMethod.POST)
    public ResponseEntity<Set<Field>> getValidFields(@RequestBody ClickedField clickedField) {

        Field sourceField = gameService.getCurrentBoard().getField(clickedField.getSourceField());
        ValidFields validFields = validFieldService.validateFields(sourceField, sourceField.getFigure());

        return new ResponseEntity<>(validFields.getFieldList().keySet(), HttpStatus.OK);
    }

}
