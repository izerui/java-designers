package Composite;

import lombok.var;

import java.util.Arrays;
import java.util.List;

/**
 * 消息类
 */
public class Messenger {

    // 来自兽人的消息
    LetterComposite messageFromOrcs() {

        List<Word> words = Arrays.asList(
                new Word('W', 'h', 'e', 'r', 'e'),
                new Word('t', 'h', 'e', 'r', 'e'),
                new Word('i', 's'),
                new Word('a'),
                new Word('w', 'h', 'i', 'p'),
                new Word('t', 'h', 'e', 'r', 'e'),
                new Word('i', 's'),
                new Word('a'),
                new Word('w', 'a', 'y')
        );

        return new Sentence(words);

    }

    // 来自精灵的消息
    LetterComposite messageFromElves() {

        List<Word> words = Arrays.asList(
                new Word('M', 'u', 'c', 'h'),
                new Word('w', 'i', 'n', 'd'),
                new Word('p', 'o', 'u', 'r', 's'),
                new Word('f', 'r', 'o', 'm'),
                new Word('y', 'o', 'u', 'r'),
                new Word('m', 'o', 'u', 't', 'h')
        );

        return new Sentence(words);

    }

}
