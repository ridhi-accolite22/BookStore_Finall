package com.week3.BookStore.Service;

import com.week3.BookStore.Model.User;
import com.week3.BookStore.Model.Wallet;

public interface WalletService {

	Wallet addAmount(Wallet wallet);
	void updateWallet(Wallet wallet);
}
