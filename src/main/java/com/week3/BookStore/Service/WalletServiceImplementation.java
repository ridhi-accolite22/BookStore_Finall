package com.week3.BookStore.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.week3.BookStore.Exceptions.NoObjectFoundException;
import com.week3.BookStore.Model.User;
import com.week3.BookStore.Model.Wallet;
import com.week3.BookStore.Repository.WalletRepository;

@Service
public class WalletServiceImplementation implements WalletService{

@Autowired	
private WalletRepository walletrepo;
	
	public Wallet addAmount(Wallet wallet)
	{
		Optional<Wallet> walletObj=this.walletrepo.findById(wallet.getWalletId());
		
		if(walletObj.isPresent()&&wallet.getBalance()>0&&wallet.getBalance()%500==0)
		{
			Wallet walletUpdate=walletObj.get();
			walletUpdate.setBalance(walletUpdate.getBalance()+wallet.getBalance());
			walletrepo.save(walletUpdate);
			return walletUpdate;
		}
		else
		{
			throw new NoObjectFoundException("No valid fields");
		}
	}
	public void updateWallet(Wallet wallet)
	{
Optional<Wallet> walletObj=this.walletrepo.findById(wallet.getUserId());
		
		if(walletObj.isPresent())
		{
			Wallet walletUpdate=walletObj.get();
			//userrepo.updateUserByID(user.getUserName(),user.getUserEmail(),user.getUserPhNo(),user.getUserId());
			walletUpdate.setBalance(wallet.getBalance());;
			walletrepo.save(walletUpdate);
		}
		else
		{
			throw new NoObjectFoundException("No wallet with ID : "+wallet.getWalletId());
		}
	}

}
