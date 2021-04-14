/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.utility;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class LineParser {

    public String[] analyseLine(String line) {

        char qoute = 'x';
        int STATE_STRING = 0;
        int STATE_WHITE = 1;
        int STATE_QOUTE = 2;
        int state = STATE_WHITE;

        char[] chars = line.trim().toCharArray();
        ArrayList<String> segments = new ArrayList<>();
        String current = "";

        for (char c : chars) {

            if (state == STATE_STRING) {

                if (Character.isWhitespace(c)) {
                    segments.add(current);
                    current = "";
                    state = STATE_WHITE;
                } else {
                    current += c;
                }
            } else if (state == STATE_WHITE) {
                if (!Character.isWhitespace(c)) {
                    if (c == '\"' || c == '\'') {
                        state = STATE_QOUTE;
                        qoute = c;
                    } else {
                        state = STATE_STRING;
                        current += c;
                    }
                }
            } else if (state == STATE_QOUTE) {
                if (c == qoute) {
                    segments.add(current);
                    current = "";
                } else {
                    current += c;
                }
            }
        }
        if (current.length() > 0) {
            segments.add(current);
        }
        String[] args = new String[segments.size()];

        for (int i = 0; i < segments.size(); i++) {
            args[i] = segments.get(i);
        }
        return args;
    }
}
