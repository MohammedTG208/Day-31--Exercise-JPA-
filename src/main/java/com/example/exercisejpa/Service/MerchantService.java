package com.example.exercisejpa.Service;


import com.example.exercisejpa.Repository.MerchantRepository;
import com.example.exercisejpa.model.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private MerchantRepository merchantRepository;

    public List<Merchant> getMerchants() {
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public boolean removeMerchant(Integer id) {
        if (merchantRepository.existsById(id)) {
            merchantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateMerchant(Merchant merchant,Integer id) {
        if (merchantRepository.existsById(id)) {
            Merchant oldMerchant = merchantRepository.getById(id);
            oldMerchant.setName(merchant.getName());
            merchantRepository.save(oldMerchant);
            return true;
        }
        return false;
    }
}
