package dev.rahulrajesh.PicPayData.repository;

import org.springframework.data.repository.CrudRepository;
import dev.rahulrajesh.PicPayData.model.Wallet;
public interface WalletRepository extends CrudRepository<Wallet, Integer> {
}
