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
import java.util.List;

public class FillBlankQuestion extends Question {

    private final List<String> correctAnswers;

    public FillBlankQuestion(final String vettedness) {
        super(vettedness);
        correctAnswers = new ArrayList<>(1);
    }

    @Override
    public void setAnswer(final String answer) {
        correctAnswers.add(answer);
    }

    @Override
    public String getAnswer() {
        return Arrays.toString(correctAnswers.toArray());
    }

    @Override
    public double checkQuestionProvidingAnswer(final String daAnswer) {
        userAnswer = daAnswer;
        return checkQuestion();
    }
    private static final String delims = "[ ]+";

    @Override
    public double checkQuestion() {
        final String[] tokens = this.userAnswer.split(delims);

        final ArrayList<String> answers = new ArrayList<>(tokens.length);
        answers.addAll(Arrays.asList(tokens));

        final double totRightAnswers = (double) correctAnswers.size();
        //final double totAnswers = tokens.length;
        double grade = 0.0;

        for (int i = 0; i < answers.size(); i++) {
            if (i < correctAnswers.size() && answers.get(i).equals(correctAnswers.get(i))) {
                grade += 1.0 / totRightAnswers;
            }
        }

        return grade;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected double getMaxPoints() {
        return 1.0;
    }
}
