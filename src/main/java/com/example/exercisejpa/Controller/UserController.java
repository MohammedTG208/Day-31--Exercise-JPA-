package com.example.exercisejpa.Controller;

import com.example.exercisejpa.Service.UserService;
import com.example.exercisejpa.model.ProductReviews;
import com.example.exercisejpa.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getall")
    public ResponseEntity getAllUsers(){
        return userService.getAllUser().isEmpty()?ResponseEntity.status(400).body("The User DB Empty"):ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            userService.addUser(user);
            return ResponseEntity.status(201).body("User added successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (userService.updateUser(user,id)){
                return ResponseEntity.status(201).body("User updated successfully");
            }
            return ResponseEntity.status(400).body("User not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(201).body("User deleted successfully");
        }
        return ResponseEntity.status(400).body("User not found");
    }

    @PostMapping("/buy/product/{userid}/{productId}/{merchantStokedID}")
    public ResponseEntity buyProduct(@PathVariable Integer userid,@PathVariable Integer productId,@PathVariable Integer merchantStokedID){
        if (userService.buyProduct(userid,productId,merchantStokedID)==1){
            return ResponseEntity.status(400).body("User not found");
        } else if (userService.buyProduct(userid,productId,merchantStokedID)==2) {
            return ResponseEntity.status(400).body("Product not found");
        } else if (userService.buyProduct(userid,productId,merchantStokedID)==3) {
            return ResponseEntity.status(400).body("Your balance can not buy the product");
        } else if (userService.buyProduct(userid,productId,merchantStokedID)==4) {
            return ResponseEntity.status(400).body("the product not in merchant stock");
        } else if (userService.buyProduct(userid,productId,merchantStokedID)==5) {
            return ResponseEntity.status(400).body("The proudest out of stock");
        }else {
            return ResponseEntity.status(200).body("You buy the Product successfully");
        }
    }

    @PostMapping("/add/review")
    public ResponseEntity addReview(@Valid @RequestBody ProductReviews reviews,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            userService.addReview(reviews);
            return ResponseEntity.status(201).body("Review added successfully");
        }
    }

    @DeleteMapping("/delete/review/{userId}/{reviewID}")
    public ResponseEntity deleteReview(@PathVariable Integer userid,@PathVariable Integer reviewID){
        if (userService.deleteReviewByAdmin(userid,reviewID)){
            return ResponseEntity.status(201).body("Review deleted successfully");
        }
        return ResponseEntity.status(400).body("Review not found");
    }

    @GetMapping("/get/review/{productId}/{userID}")
    public ResponseEntity getReviewForAdmin(@PathVariable Integer productId, @PathVariable Integer userID){
        if (userService.getSpecificProductReviewForAdmin(productId,userID)!=null){
            return ResponseEntity.status(201).body(userService.getSpecificProductReviewForAdmin(productId,userID));
        }
        return ResponseEntity.status(400).body("No review found for this Product");
    }


}
