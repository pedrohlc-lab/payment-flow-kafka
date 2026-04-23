package com.example.demo.controller;

import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.dto.AccountResponseDTO;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private final AccountService service;


    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO rDTO){
        AccountResponseDTO response = service.create(rDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    //READ
    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> findAll_(){
        List<AccountResponseDTO> response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> findAccount(@PathVariable long id){
        AccountResponseDTO response = service.findAccount(id);
        return  ResponseEntity.ok(response);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO>  update(Long id, @RequestBody AccountRequestDTO dto){
        AccountResponseDTO response = service.UpdateAccount(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
