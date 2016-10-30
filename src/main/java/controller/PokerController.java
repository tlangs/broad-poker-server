package controller;

import net.langsford.deck.Deck;
import net.langsford.deck.PlayingCard;
import net.langsford.game.Game;
import net.langsford.game.GameResult;
import net.langsford.hand.HandCombinations;
import net.langsford.hand.HandOperations;
import net.langsford.hand.HandResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trevyn on 10/30/16.
 */
@RestController
@RequestMapping("/poker")
public class PokerController {

    @RequestMapping("/")
    public String index() {
        return "Spring boot application";
    }

    @RequestMapping(value = "/drawHand", produces = "application/json")
    public List<PlayingCard> drawHand() {
        Deck deck = new Deck();
        deck.shuffleDeck();
        return deck.drawFromDeck(5);
    }

    @RequestMapping(value = "drawBigHand", produces = "application/json")
    public List<PlayingCard> drawBigHand(@RequestParam(defaultValue = "10", name = "numCards") Integer num) {
        Deck deck = new Deck();
        deck.shuffleDeck();
        return deck.drawFromDeck(num);
    }

    @RequestMapping(value = "/drawHands", produces = "application/json")
    public List<List<PlayingCard>> drawMultipleHands(@RequestParam(defaultValue = "2", name="numHands") Integer num) {
        Deck deck = new Deck();
        deck.shuffleDeck();
        List<List<PlayingCard>> hands = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            hands.add(deck.drawFromDeck(5));
        }
        return hands;
    }

    @RequestMapping(value = "/bestHand", method = RequestMethod.POST, consumes = "application/json")
    public String bestHand(@RequestBody List<PlayingCard> hand) {
        HandResult result = HandCombinations.computeHand(hand);
        return result.toString();
    }

    @RequestMapping(value = "/findWinner", method = RequestMethod.POST, consumes = "application/json")
    public Game findWinner(@RequestBody List<List<PlayingCard>> hands) {
        return new Game(hands.get(0), hands.get(1));
    }

    @RequestMapping(value = "/bestSubset", method = RequestMethod.POST, consumes = "application/json")
    public String bestSubset(@RequestBody List<PlayingCard> bigHand) {
        HandResult result = HandOperations.bestFiveCardHand(bigHand);
        return result.toString();
    }
}
