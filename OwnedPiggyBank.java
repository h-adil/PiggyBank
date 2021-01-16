package eecs2030.lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;
import java.util.List;

/**
 * A class representing a piggy bank that has an owner. 
 * A piggy bank owns a collection (or possibly collections) of coins,
 * but does not own the coins themselves. In other words,
 * the piggy bank and its collection of coins form a composition.
 * 
 * <p>
 * Only the owner of the piggy bank is able to remove coins
 * from the piggy bank. The piggy bank does own its owner.
 * In other words, the piggy bank and its owner form an
 * aggregation.
 */
public class OwnedPiggyBank {
	/*
	 * YOU NEED A FIELD HERE TO HOLD THE COINS OF THIS PIGGY BANK
	 */
	ArrayList<Coin> ownedPiggyBank = new ArrayList<Coin>();

	/**
	 * The owner of this piggy bank.
	 */
	private Owner owner;

	/**
	 * Initializes this piggy bank so that it has the specified owner
	 * and its collection of coins is empty.
	 * 
	 * @param owner
	 *            the owner of this piggy bank
	 */
	public OwnedPiggyBank(Owner owner) {

		this.owner = owner;
	}
	/**
	 * Initializes this piggy bank by copying another piggy bank. This piggy
	 * bank will have the same owner and the same number and type of coins as
	 * the other piggy bank.
	 * 
	 * @param other
	 *            the piggy bank to copy
	 */
	public OwnedPiggyBank(OwnedPiggyBank other) {

		this.ownedPiggyBank = other.ownedPiggyBank;
		this.owner = other.owner;
	}

	/**
	 * Returns the owner of this piggy bank.
	 * 
	 * <p>
	 * This method is present only for testing purposes. Returning
	 * the owner of this piggy bank allows any user to remove coins
	 * from the piggy bank (because any user can get the owner
	 * of this piggy bank)!
	 * 
	 * @return the owner of this piggy bank
	 */
	public Owner getOwner() {
		// ALREADY IMPLEMENTED; DO NOT MODIFY
		return this.owner;
	}

	/**
	 * Allows the current owner of this piggy bank to give this
	 * piggy bank to a new owner.
	 * 
	 * @param currentOwner the current owner of this piggy bank
	 * @param newOwner the new owner of this piggy bank
	 * @throws IllegalArgumentException if currentOwner is not the
	 * current owner of this piggy bank
	 */
	public void changeOwner(Owner currentOwner, Owner newOwner) {

		if(this.owner.getID() == currentOwner.getID()) {
			this.owner = newOwner;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Adds the specified coins to this piggy bank.
	 * 
	 * @param coins
	 *            a list of coins to add to this piggy bank
	 */
	public void add(List<Coin> coins) {

		this.ownedPiggyBank.addAll(coins);
	}

	/**
	 * Returns true if this piggy bank contains the specified coin, and false
	 * otherwise.
	 * 
	 * @param coin
	 *            a coin
	 * @return true if this piggy bank contains the specified coin, and false
	 *         otherwise
	 */
	public boolean contains(Coin coin) {

		return this.ownedPiggyBank.contains(coin);
	}

	/**
	 * Allows the owner of this piggy bank to remove a coin equal to the value
	 * of the specified coin from this piggy bank.
	 * 
	 * <p>
	 * If the specified user is not equal to the owner of this piggy bank,
	 * then the coin is not removed from this piggy bank, and null is returned.
	 * 
	 * @param user
	 *            the person trying to remove the coin
	 * @param coin
	 *            a coin
	 * @return a coin equal to the value of the specified coin from this piggy
	 *         bank, or null if user is not the owner of this piggy bank
	 * @pre. the piggy bank contains a coin equal to the specified coin
	 */
	public Coin remove(Owner user, Coin coin) {

		if(this.owner.getID() == user.getID()) {
			this.ownedPiggyBank.remove(coin);
		}
		else {
			return null;
		}
		return coin;
	}

	/**
	 * Allows the owner of this piggy bank to remove
	 * the smallest number of coins whose total value in cents is equal
	 * to the specified value in cents from this piggy bank.
	 * 
	 * <p>
	 * Returns the empty list if the specified user is not equal to
	 * the owner of this piggy bank.
	 * 
	 * @param user
	 *            the person trying to remove coins from this piggy bank
	 * @param value
	 *            a value in cents
	 * @return the smallest number of coins whose total value in cents is equal
	 *         to the specified value in cents from this piggy bank 
	 * @pre. the piggy bank contains a group of coins whose total value is equal
	 *         to specified value
	 */
	public List<Coin> removeCoins(Owner user, int value) {

		List<Coin> coins = Arrays.asList();
		if(this.owner.getID() == user.getID()) { 
			this.ownedPiggyBank.clear();
				if(value >= 1 && value < 5) {
					for (int x = 0; x < value; x++) {
						coins = Arrays.asList(Coin.PENNY);
						this.ownedPiggyBank.addAll(coins);
					}
			}
			else if((value >= 5 && value < 10)) {
				int q = value / 5;
				int r = value % 5;
				coins = removeCoins(user, r);
				if (q != 0 && r == 0) {
					for (int x = 0; x < q; x++) {
						coins = Arrays.asList(Coin.NICKEL);
						this.ownedPiggyBank.addAll(coins);
					}
				}
				else {
					coins = Arrays.asList(Coin.NICKEL);
					this.ownedPiggyBank.addAll(coins);
				}
			}
			else if((value >= 10 && value < 25)) {
				int q = value / 10;
				int r = value % 10;
				coins = removeCoins(user, r);
				if (q != 0 && r == 0) {
					for (int x = 0; x < q; x++) {
						coins = Arrays.asList(Coin.DIME);
						this.ownedPiggyBank.addAll(coins);
					}
				}
				else {
					coins = Arrays.asList(Coin.DIME);
					this.ownedPiggyBank.addAll(coins);
				}
			}
			else if((value >= 25 && value < 100)) {
				int q = value / 25;
				int r = value % 25;
				coins = removeCoins(user, r);
				if (q != 0 && r == 0) {
					for (int x = 0; x < q; x++) {
						coins = Arrays.asList(Coin.QUARTER);
						this.ownedPiggyBank.addAll(coins);
					}
				}
				else {
					coins = Arrays.asList(Coin.QUARTER);
					this.ownedPiggyBank.addAll(coins);
				}
			}
			else if((value >= 100 && value < 200)) {
				int q = (int) (Math.ceil(value)) / 100;
				int r = value % 100;
				coins = removeCoins(user, r);
				if (q != 0 && r == 0) {
					for (int x = 0; x < q; x++) {
						coins = Arrays.asList(Coin.LOONIE);
						this.ownedPiggyBank.addAll(coins);
					}
				}
				else {
					coins = Arrays.asList(Coin.LOONIE);
					this.ownedPiggyBank.addAll(coins);
				}
			}
			else if(value >= 200) {
				int q = value / 200;
				int r = value % 200;
				coins = removeCoins(user, r);
				if (q != 0 && r == 0) {
					for (int x = 0; x < q; x++) {
						coins = Arrays.asList(Coin.TOONIE);
						this.ownedPiggyBank.addAll(coins);
					}
				}
				else {
					coins = Arrays.asList(Coin.TOONIE);
					this.ownedPiggyBank.addAll(coins);
				}
			}
		} 
		else {
			this.ownedPiggyBank.clear();
		}
		return this.ownedPiggyBank;
	}
//		List<Coin> coins = Arrays.asList();
//		if(this.owner.getID() == user.getID()) {
//			for(int x = 0; x <= this.ownedPiggyBank.size() - 1; x++) {
//				if(this.ownedPiggyBank.get(0).getValue() == value) {
//					break;
//				}
//			}
//			this.ownedPiggyBank.clear();
//			if(value ==1) {
//				coins = Arrays.asList(Coin.PENNY);
//				this.ownedPiggyBank.addAll(coins);
//			}
//			else if((value > 1 && value < 5) ) {
//				for(int i=1; i <= value; i++) {
//					coins = Arrays.asList(Coin.PENNY);
//					this.ownedPiggyBank.addAll(coins);
//				}
//			}
//			else if(value ==5) {
//				coins = Arrays.asList(Coin.NICKEL);
//				this.ownedPiggyBank.addAll(coins);
//			}
//			else if(value > 5 && value < 10) {
//				for(int i=5; i < value; i++) {
//					if(value >= i+1) {
//						coins = Arrays.asList(Coin.PENNY);
//						this.ownedPiggyBank.addAll(coins);
//					if(value >= i+2) {
//						coins = Arrays.asList(Coin.NICKEL);
//						this.ownedPiggyBank.addAll(coins);
//					}
//					}	
//				}
//			}
//			else if(value ==10) {
//				coins = Arrays.asList(Coin.DIME);
//				this.ownedPiggyBank.addAll(coins);
//			}
//			else if(value > 10 && value < 25) {
//				
//			}
//			else if(value ==25) {
//				coins = Arrays.asList(Coin.QUARTER);
//				this.ownedPiggyBank.addAll(coins);
//			}
//			else if(value > 25 && value < 100) {
//				
//			}
//			else if(value ==100) {
//				coins = Arrays.asList(Coin.LOONIE);
//				this.ownedPiggyBank.addAll(coins);
//			}
//			else if(value ==200) {
//				coins = Arrays.asList(Coin.TOONIE);
//				this.ownedPiggyBank.addAll(coins);
//			}
//		}
//		else {
//			this.ownedPiggyBank.clear();
//			return this.ownedPiggyBank;
//		}
//
//		return this.ownedPiggyBank;
//	}


	/**
	 * Returns a deep copy of the coins in this piggy bank. The returned list
	 * has its coins in sorted order (from smallest value to largest value;
	 * i.e., pennies first, followed by nickels, dimes, quarters, loonies, and
	 * toonies).
	 * 
	 * @return a deep copy of the coins in this piggy bank
	 */
	public List<Coin> deepCopy() {

		return this.ownedPiggyBank;
	}
}	

