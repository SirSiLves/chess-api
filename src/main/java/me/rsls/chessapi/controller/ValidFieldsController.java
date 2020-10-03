package me.rsls.chessapi.controller;

import me.rsls.chessapi.model.ClickedField;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.service.GameService;
import me.rsls.chessapi.service.validation.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/validFields")
public class ValidFieldsController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ValidateService validateService;

    //TODO POST?

    @RequestMapping(value = "getFields", method = RequestMethod.POST)
    public ResponseEntity<List<Field>> getValidFields(@RequestBody ClickedField clickedField) {
        System.out.println("GET VALID FIELDS");


        Field sourceField = gameService.getCurrentBoard().getField(clickedField.getSourceField());
        List<Field> possibleFieldList = validateService.getAllValidFields(sourceField.getFigure(), true);

        return new ResponseEntity<>(possibleFieldList, HttpStatus.OK);
    }

}
