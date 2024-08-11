package com.example.exercisejpa.Service;

import com.example.exercisejpa.Repository.MerchantStockRepository;
import com.example.exercisejpa.Repository.ProductRepository;
import com.example.exercisejpa.Repository.UserRepository;
import com.example.exercisejpa.model.MerchantStock;
import com.example.exercisejpa.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<MerchantStock> getMerchantStock() {
        return merchantStockRepository.findAll();
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStockRepository.save(merchantStock);
    }

    public boolean updateMerchantStock(MerchantStock merchantStock, Integer id) {
        if (merchantStockRepository.existsById(id)) {
            MerchantStock oldMerchantStock = merchantStockRepository.getById(id);
            oldMerchantStock.setStock(merchantStock.getStock());
            oldMerchantStock.setMerchantid(merchantStock.getMerchantid());
            oldMerchantStock.setProductid(merchantStock.getProductid());
            merchantStockRepository.save(oldMerchantStock);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteMerchantStock(Integer id) {
        if (merchantStockRepository.existsById(id)) {
            merchantStockRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public int updateStock(Integer merchantStokedID, int quantity) {
        if (merchantStockRepository.existsById(merchantStokedID)) {
            MerchantStock merchantStock = merchantStockRepository.getById(merchantStokedID);
            merchantStock.setStock(merchantStock.getStock() + quantity);
            merchantStockRepository.save(merchantStock);
            return 2;
        } else {
            return 1;
        }
    }


}

