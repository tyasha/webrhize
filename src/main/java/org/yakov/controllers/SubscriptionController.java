package org.yakov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yakov.dto.ResponseResult;
import org.yakov.model.Subscription;
import org.yakov.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping(path = "/{idUser}")
    public ResponseEntity<ResponseResult<Subscription>> add(@PathVariable long idUser, @RequestBody Subscription subscription) {
        try {
            this.subscriptionService.add(idUser, subscription);
            return new ResponseEntity<>(new ResponseResult<>(null, subscription), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Subscription>>> getBestSubscriptions() {
        List<Subscription> list = this.subscriptionService.getBestSubscriptions();
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
    }

    @GetMapping(path = "/{idUser}")
    public ResponseEntity<ResponseResult<List<Subscription>>> getSubscriptionsByUserId(@PathVariable long idUser) {
        List<Subscription> list = this.subscriptionService.getSubscriptionsByUserId(idUser);
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Subscription>> delete(@PathVariable long id) {
        try {
            Subscription subscription = this.subscriptionService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, subscription), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
