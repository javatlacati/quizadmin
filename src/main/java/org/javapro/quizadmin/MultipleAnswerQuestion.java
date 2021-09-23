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
import java.util.Arrays;
import java.util.Collections;
import lombok.Getter;

@Getter
public class MultipleAnswerQuestion extends MultipleChoiceQuestion {

    private final ArrayList<String> choices;
    private final ArrayList<String> correctAnswers;

    /**
     * Constructs a choice question with no choices.
     */
    public MultipleAnswerQuestion(String vettedness) {
        super(vettedness, QuestionType.MULTIPLE_ANSWER);
        choices = new ArrayList<>(2);
        correctAnswers = new ArrayList<>(1);
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

    public void setAnswer(String answer) {
        correctAnswers.add(answer);
    }

    @Override
    public String getAnswer() {
        return Arrays.toString(correctAnswers.toArray());
    }

    public double checkQuestionProvidingAnswer(final String answer) {
        userAnswer = answer;
        return checkQuestion();
    }

    /**
     *
     * @return
     */
    @Override
    public double checkQuestion() {

        String delims = "[ ]+";
        String[] tokens = userAnswer.split(delims);

        ArrayList<String> answers = new ArrayList<>(tokens.length);
        Collections.addAll(answers, tokens);

        double totRightAnswers = (double) correctAnswers.size();
        double totAnswers = (double) tokens.length;
        double wrongAnswers = (double) tokens.length;
        double grade = 0.0;

        for (String answer : answers) {
            for (String correctAnswer : correctAnswers) {
                if (answer.equals(correctAnswer)) {
                    grade += 1.0 / totRightAnswers;
                    wrongAnswers -= 1.0;
                }
            }
        }

        //Deducts points for answers given that were not correct or in excess.
        grade -= wrongAnswers * (1.0 / totRightAnswers);

        /*
         * If more answers were wrong/in excess than were right, causing a
         * negative grade, it is brought back up to 0.
         */
        if (grade < 0.0) {
            grade = 0.0;
        }

        return grade;
    }

    /**
     * @return
     */
    @Override
    public String display() {

        StringBuilder display = new StringBuilder(text);
        display.append("\n");

        for (int i = 0; i < choices.size(); i++) {
            final int choiceNumber = i + 1;
            display.append(choiceNumber).append(": ").append(choices.get(i)).append("\n");
        }

        return display.toString();

    }

}
