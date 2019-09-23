package poker;
import java.util.*;

import poker.Card.Rank;

/**
 * A poker hand is a list of cards, which can be of some "kind" (pair, straight, etc.)
 * 
 */
public class Hand implements Comparable<Hand> {

    public enum Kind {HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, 
        FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH}

    private final List<Card> cards;

    /**
     * Create a hand from a string containing all cards (e,g, "5C TD AH QS 2D")
     */
    public Hand(String c) {
    	this.cards = new ArrayList<Card>();
        String[] cards = c.split(" ");
        for(String card:cards) {
        	this.cards.add(new Card(card));
        }
    }
    
    /**
     * @returns true if the hand has n cards of the same rank
	 * e.g., "TD TC TH 7C 7D" returns True for n=2 and n=3, and False for n=1 and n=4
     */
    protected boolean hasNKind(int n) {
    	HashMap<Card.Rank, Integer> ranks = new HashMap<Card.Rank, Integer>();
    	for(Card c: cards) {
    		Card.Rank r = c.getRank();
    		if(ranks.containsKey(r)) {
    			ranks.replace(r, ranks.get(r) + 1); 	
    		} else {
    			ranks.put(r, 1);
    		}
    	}
    	for(Integer i: ranks.values()) {
    		if(i.equals((Integer)n)){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
	 * Optional: you may skip this one. If so, just make it return False
     * @returns true if the hand has two pairs
     */
    public boolean isTwoPair() {
    	return false;
    }   
    
    /**
     * @returns true if the hand is a straight 
     */
    public boolean isStraight() {
    	ArrayList<Integer> arr = new ArrayList<Integer>();
        for(Card c : cards) {
        	Card.Rank r = c.getRank();
        	if(r == Rank.ACE) { arr.add(1);}
        	else if (r == Rank.DEUCE) { arr.add(2);}
        	else if (r == Rank.THREE) { arr.add(3);}
        	else if (r == Rank.FOUR) { arr.add(4);}
        	else if (r == Rank.FIVE) { arr.add(5);}
        	else if (r == Rank.SIX) { arr.add(6);}
        	else if (r == Rank.SEVEN) { arr.add(7);}
        	else if (r == Rank.EIGHT) { arr.add(8);}
        	else if (r == Rank.NINE) { arr.add(9);}
        	else if (r == Rank.TEN) { arr.add(10);}
        	else if (r == Rank.JACK) { arr.add(11);}
        	else if (r == Rank.QUEEN) { arr.add(12);}
        	else {arr.add(13);}    	
        }
        Collections.sort(arr);
        for(int i = 0; i < arr.size() - 1; i++) {
        	if(arr.get(i) + 1 != arr.get(i+1)) {
        		return false;
        	}
        }
        return true;
        
    }
    
    /**
     * @returns true if the hand is a flush
     */
    public boolean isFlush() {
        
    }
    
    @Override
    public int compareTo(Hand h) {
        //hint: delegate!
		//and don't worry about breaking ties
    }
    
    /**
	 * This method is already implemented and could be useful! 
     * @returns the "kind" of the hand: flush, full house, etc.
     */
    public Kind kind() {
        if (isStraight() && isFlush()) return Kind.STRAIGHT_FLUSH;
        else if (hasNKind(4)) return Kind.FOUR_OF_A_KIND; 
        else if (hasNKind(3) && hasNKind(2)) return Kind.FULL_HOUSE;
        else if (isFlush()) return Kind.FLUSH;
        else if (isStraight()) return Kind.STRAIGHT;
        else if (hasNKind(3)) return Kind.THREE_OF_A_KIND;
        else if (isTwoPair()) return Kind.TWO_PAIR;
        else if (hasNKind(2)) return Kind.PAIR; 
        else return Kind.HIGH_CARD;
    }

}