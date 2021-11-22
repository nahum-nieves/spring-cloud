package com.yp.challenge.controller;


import com.yp.challenge.dto.TransferRequest;
import com.yp.challenge.dto.TransferResponse;
import com.yp.challenge.service.TransferService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Account Operations")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final TransferService accountService;

    @PostMapping("/api/transer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest transferRequest){
        return ResponseEntity.ok(accountService.transfer(transferRequest));
    }

}
