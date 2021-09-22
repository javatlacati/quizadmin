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

import lombok.Getter;
import lombok.Setter;

/**
 * @author NicolasADavid
 * @author Javatlacati
 */
@Setter
@Getter
abstract public class Question {

    private QuestionType type;

    public static final String VETTED = "vetted";
    public static final String TRIAL = "trial";

    protected String text;
    protected String answer;
    protected String explanation;
    protected String userAnswer;
    protected String vettedOrTrial;
    protected String category;
    protected Difficulty difficulty;

    /**
     * Constructs a question with empty question and answer
     *
     * @param vettedness
     */
    public Question(final String vettedness, QuestionType questionType) {
//        text = "";//only necessary for Java 5 where default value for String variable at class scope was null instead of ""
//        answer = "";
        vettedOrTrial = vettedness;
        category = "default";
        difficulty = Difficulty.NORMAL;
        type=questionType;
    }

    /**
     * Sets the question text.
     *
     * @param questionText the text of this question
     */
    public void setText(final String questionText) {
        text = questionText;
    }

    /**
     * Sets the correct answer(s)
     *
     * @param answer answer text
     */
    public abstract void setAnswer(final String answer);

    public abstract String getAnswer();

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
//    /**
//     * Checks the answer(s)
//     * @return true for correct, false otherwise
//     */
//    public abstract double checkAnswer(String answer);

    /**
     * Show question Text
     *
     * @return question text
     */
    public String display() {
        return text;
    }

    public boolean gradeQuestion() {
        return vettedOrTrial.equals(VETTED);
    }

    public abstract double checkQuestionProvidingAnswer(final String answer);

    public double checkQuestion() {
        if (userAnswer != null) {
            if (this instanceof FillBlankQuestion) {
                if (userAnswer.equalsIgnoreCase(answer)) {
                    return 1.0;
                }
//                else {
                //TODO tal vez aquí llamar a checkQuestionProvidingAnswer para llenar
//                    return 0.0;
//                }
            } else {
                if (userAnswer.equals(answer)) {
                    return 1.0;
                }
//                else {
                //TODO tal vez aquí llamar a checkQuestionProvidingAnswer para llenar
//                    return 0.0;
//                }
            }
        }
        //TODO tal vez aquí llamar a checkQuestionProvidingAnswer para llenar
        return 0.0;

    }

    /**
     * Maximum number of point that can be awarded by this question.
     */
    abstract double getMaxPoints();

    @Override
    public String toString() {
        return "Question{" + "text=" + text + ", answer=" + answer + ", explanation=" + explanation + ", userAnswer=" + userAnswer + ", vettedOrTrial=" + vettedOrTrial + ", category=" + category + ", difficulty=" + difficulty + '}';
    }


}
