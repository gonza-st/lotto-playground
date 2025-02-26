package org.gonza.javaplayground;

import org.gonza.javaplayground.controller.LottoGameController;
import org.gonza.javaplayground.core.LottoMachine;
import org.gonza.javaplayground.core.LottoNumberGenerator;
import org.gonza.javaplayground.util.InputValidator;
import org.gonza.javaplayground.view.ConsoleInputView;
import org.gonza.javaplayground.view.ConsoleOutputView;
import org.gonza.javaplayground.view.LottoInputView;
import org.gonza.javaplayground.view.LottoOutputView;

public class JavaPlaygroundApplication {

    public static void main(String[] args) {
        InputValidator validator = new InputValidator();

        LottoInputView inputView = new ConsoleInputView();

        LottoOutputView outputView = new ConsoleOutputView();
        LottoMachine lottoMachine = new LottoMachine(new LottoNumberGenerator());

        LottoGameController gameController = new LottoGameController(
                inputView,
                outputView,
                lottoMachine,
                validator
        );

        try {
            gameController.play();
        } finally {
            inputView.close();
        }
    }
}
