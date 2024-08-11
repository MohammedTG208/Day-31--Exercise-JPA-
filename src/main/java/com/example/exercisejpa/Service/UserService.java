package com.example.exercisejpa.Service;

import com.example.exercisejpa.Repository.MerchantStockRepository;
import com.example.exercisejpa.Repository.ProductRepository;
import com.example.exercisejpa.Repository.ProductReviewsRepository;
import com.example.exercisejpa.Repository.UserRepository;
import com.example.exercisejpa.model.MerchantStock;
import com.example.exercisejpa.model.Product;
import com.example.exercisejpa.model.ProductReviews;
import com.example.exercisejpa.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MerchantStockRepository stockRepository;
    private final ProductReviewsRepository productReviewsRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(User user,Integer id) {
        if (userRepository.existsById(id)){
            User oldUser = userRepository.getById(id);
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
            oldUser.setEmail(user.getEmail());
            oldUser.setBalance(user.getBalance());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }
    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public int buyProduct(Integer userId, Integer productId,Integer merchantStokedID) {
        if (userRepository.existsById(userId)){
            User userBuyProduct = userRepository.getById(userId);
            if (productRepository.existsById(productId)){
                Product product = productRepository.getById(productId);
                if (userBuyProduct.getBalance()<product.getPrice()){
                    return 3;
                }else {
                    if (stockRepository.existsById(merchantStokedID)){
                        MerchantStock merchantStock=stockRepository.getById(merchantStokedID);
                        if (merchantStock.getStock()<1){
                            return 5;
                        }else {
                            userBuyProduct.setBalance(userBuyProduct.getBalance()-product.getPrice());
                            merchantStock.setStock(merchantStock.getStock()-1);
                            stockRepository.save(merchantStock);
                            userRepository.save(userBuyProduct);
                            return 6;
                        }
                    }else {
                        return 4;
                    }
                }
            }else {
                return 2;
            }
        }else {
            return 1;
        }
    }

    public void addReview(ProductReviews productReviews) {
       productReviewsRepository.save(productReviews);
    }

    public ArrayList<ProductReviews> getSpecificProductReviewForAdmin(Integer productId, Integer userID) {
        if (userRepository.existsById(userID)){
            for (User user : userRepository.findAll()){
                if (user.getUsername().equalsIgnoreCase("admin")&&user.getId()==userID){
                    if (productRepository.existsById(productId)){
                        ArrayList<ProductReviews> getAllProductReviews=new ArrayList<>();
                        for (int i=0;i<productReviewsRepository.findAll().size();i++){
                            if (productRepository.findAll().get(i).getId()==productId){
                                getAllProductReviews.add(productReviewsRepository.findAll().get(i));
                            }
                        }
                        return getAllProductReviews;
                    }
                }
            }
        }else {
            return null;
        }
        return null;
    }

    public boolean deleteReviewByAdmin(Integer userId,Integer reviewID){
        for (User user : userRepository.findAll()){
            if (user.getUsername().equalsIgnoreCase("admin")&&user.getId()==userId){
               if (productRepository.existsById(reviewID)){
                   productRepository.deleteById(reviewID);
                return true;
               }
            }
        }
        return false;
    }
}
