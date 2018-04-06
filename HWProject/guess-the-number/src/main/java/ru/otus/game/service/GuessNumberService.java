package ru.otus.game.service;

import java.util.concurrent.Future;

public interface GuessNumberService {
    int MAX_ATTEMPTS = 2;
    int BOUND = 10;

    Future<Boolean> guessTheNumber(String userName, int number);
}
