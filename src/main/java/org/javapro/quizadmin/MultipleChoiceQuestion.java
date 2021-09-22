/*
 * Copyright (C) 2021 Ruslan Lopez Carrro <scherzo_16 at hotmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.javapro.quizadmin;

/**
 *
 * @author Ruslan Lopez Carrro <scherzo_16 at hotmail.com>
 */
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A question with multiple choices.
 */
public class MultipleChoiceQuestion extends Question {

    private static final Pattern BLANKS = Pattern.compile("\\s+");
    private final ArrayList<String> choices;

    /**
     * Constructs a multiple choice question with no choices.
     */
    public MultipleChoiceQuestion(String vettedness) {
        super(vettedness, QuestionType.MULTIPLE_OPTION);
        choices = new ArrayList<>(2);
    }

    public MultipleChoiceQuestion(String vettedness, QuestionType type) {
        super(vettedness, type);
        choices = new ArrayList<>(2);
    }

    /**
     * Adds an answer choice to this question.
     *
     * @param choice the choice to add
     * @param correct true if this is the correct choice, false otherwise
     */
    public void setChoice(String choice, boolean correct) {
        choices.add(choice);
        if (correct) {
            // Convert choices.size() to string
            String choiceString = String.valueOf(choices.size());
            setAnswer(choiceString);
        }
    }

    /**
     * Sets the correct answer. A number in a string.
     *
     * @param answer a number in a string that corresponds to the answers place
     * in the choices arrayList
     */
    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
//        int correctAnswer = choices.indexOf(answer);
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public double checkQuestionProvidingAnswer(final String answer) {
        userAnswer = BLANKS.matcher(answer).replaceAll("");
        return checkQuestion();
    }

    /**
     * Returns a string with the question text and choices
     *
     * @return string with question text and choice
     */
    @Override
    public String display() {

        StringBuilder display = new StringBuilder(text);
        display.append("\n");

        for (int i = 0; i < choices.size(); i++) {
            int choiceNumber = i + 1;
            display.append(choiceNumber).append(": ").append(choices.get(i)).append("\n");
        }

        return display.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    double getMaxPoints() {
        return 1.0;
    }

}
