package com.sda.spring.core.spring;

import com.sda.spring.core.autowired.ImageConverter;
import com.sda.spring.core.autowired.SpellChecker;
import com.sda.spring.core.autowired.TextEditor;
import com.sda.spring.core.autowired.TextFormatter;

public class DemoSpring {

    public static void main(String[] args) {
        SpellChecker spellChecker = new SpellChecker();
        ImageConverter imageConverter = new ImageConverter();
        TextFormatter textFormatter = new TextFormatter();
        TextEditor textEditor = new TextEditor(spellChecker);
        textEditor.setImageConverter(imageConverter);

        textEditor.format();
    }
}
