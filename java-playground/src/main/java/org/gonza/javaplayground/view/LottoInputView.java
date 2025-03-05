package org.gonza.javaplayground.view;

import java.util.List;

public interface LottoInputView {
    String read();

    void close();

    List<String> readMultipleLines(int lineCount);
}
