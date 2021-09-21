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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.ResourceBundle.getBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Ruslan Lopez Carrro <scherzo_16 at hotmail.com>
 */
public class QuestionParser {

    public static final String FIELD_DIVIDER = "@@";
    private static final Pattern SEPARATE_BY_DIVIDER_PATTERN = Pattern.compile(FIELD_DIVIDER);
    private static final Pattern LINE_BREAK = Pattern.compile("\\\\n");

    /**
     * @param questionString the question fields split by @@
     */
    public static List<Question> parseQuestion(final String questionString) {
        List<Question> questions = new ArrayList<>();
        final String[] questarray = SEPARATE_BY_DIVIDER_PATTERN.split(questionString);
        String tipoPregunta = questarray[0];
        String vettedness = "v".equals(questarray[1]) ? Question.VETTED : Question.TRIAL;
        String explanation = questarray[2];
        String category = questarray[3];
        String difficulty = questarray[4];
        String questionText = formateaPregunta(questarray[5]);
        switch (tipoPregunta) {
            case "MC": {
                //multiple choice
                int correctAnswerIdx = Integer.parseInt(questarray[6]);
                String[] answersTexts = Arrays.copyOfRange(questarray, 7, questarray.length);
                addMultipleChoiceQuestion(questions, vettedness, explanation,
                        questionText, correctAnswerIdx, category, difficulty, answersTexts);
                break;
            }
            case "FB": {
                //fill in the blanks
                String[] blanks = Arrays.copyOfRange(questarray, 6, questarray.length);
                addFillBlankQuestion(questions, vettedness, explanation, questionText, category, difficulty, blanks);
                break;
            }
            case "MA": {
                Map<String, Boolean> choices = parseChoicesMap(Arrays.copyOfRange(questarray, 6, questarray.length));
                addMultipleAnswerQuestion(questions, vettedness, explanation, questionText,
                        category, difficulty, choices);
                break;
            }
            default:
                System.err.println(MessageFormat.format(getBundle("quiz/resources/quiz").getString("QUESTION_TYPE_ERR"), new Object[]{}));
                break;
        }
        return questions;
    }

    /**
     * Adds a new multiple choice question to the specified question list.
     */
    private static void addMultipleChoiceQuestion(final List<Question> questions,
            final String vettedness, final String explanation, final String questionText, final int correctAnswerIdx,
            final String category,
            final String difficulty,
            final String... answersTexts) {
        final MultipleChoiceQuestion choiceQuestion = new MultipleChoiceQuestion(vettedness);
        choiceQuestion.setExplanation(explanation);
        choiceQuestion.setText(questionText);
        choiceQuestion.setCategory(category);
        choiceQuestion.setDifficulty(str2difficulty(difficulty));
        for (int i = 0; i < answersTexts.length; i++) {
            final String answerText = answersTexts[i];
            choiceQuestion.setChoice(answerText, i == correctAnswerIdx);
        }
        questions.add(choiceQuestion);
    }

    /**
     *
     */
    private static void addMultipleAnswerQuestion(final List<Question> questions,
            final String vettedness, final String explanation, final String questionText,
            final String category, final String difficulty,
            final Map<String, Boolean> answerChoicesMap) {
        final MultipleAnswerQuestion choiceQuestion = new MultipleAnswerQuestion(vettedness);
        choiceQuestion.setExplanation(explanation);
        choiceQuestion.setText(questionText);
        choiceQuestion.setCategory(category);
        choiceQuestion.setDifficulty(str2difficulty(difficulty));
        answerChoicesMap.forEach(choiceQuestion::setChoice);
        questions.add(choiceQuestion);
    }

    /**
     *
     */
    private static void addFillBlankQuestion(final List<Question> questions,
            final String vettedness, final String explanation, final String questionText,
            final String category, final String difficulty,
            final String... blanks) {
        final FillBlankQuestion fillBlankQuestion = new FillBlankQuestion(vettedness);
        fillBlankQuestion.setExplanation(explanation);
        fillBlankQuestion.setText(questionText);
        fillBlankQuestion.setCategory(category);
        fillBlankQuestion.setDifficulty(str2difficulty(difficulty));
        for (final String blank : blanks) {
            fillBlankQuestion.setAnswer(blank);
        }
        questions.add(fillBlankQuestion);
    }

    /**
     *
     */
    private static Map<String, Boolean> createAnswerChoicesMap(final String[] answerTexts, final Boolean... answerValidities) {
        final int numsOfAnswers = answerTexts.length;
        if (numsOfAnswers != answerValidities.length) {
            throw new RuntimeException(MessageFormat.format(getBundle("quiz/resources/quiz").getString("ANSWER_TEXTS_ERR"), new Object[]{}));
        }
        final HashMap<String, Boolean> hashMap = new HashMap<>(2);
        for (int answIdx = 0; answIdx < answerTexts.length; answIdx++) {
            hashMap.put(answerTexts[answIdx], answerValidities[answIdx]);
        }
        return hashMap;
    }

    private static Difficulty str2difficulty(String idxStr) {
        switch (idxStr) {
            case "2":
                return Difficulty.EASY;
            case "1":
                return Difficulty.HARD;
            case "0":
            default:
                return Difficulty.NORMAL;
        }
    }

    /**
     * Parses the choices and relates them with their values.
     *
     * @param questionMapArr list with options and then values of each question
     * @return options maped with their values
     */
    public static Map<String, Boolean> parseChoicesMap(final String... questionMapArr) {
        final String[] questarray = Arrays.copyOfRange(questionMapArr, 0, questionMapArr.length / 2);
        final String[] strAnswerValidities = Arrays.copyOfRange(questionMapArr, questionMapArr.length / 2, questionMapArr.length);
        final Boolean[] answerValidities = new Boolean[strAnswerValidities.length];
        Arrays.stream(strAnswerValidities).map((booleanAnswer) -> "true".equalsIgnoreCase(booleanAnswer) ? Boolean.TRUE : Boolean.FALSE).collect(Collectors.toList()).toArray(answerValidities);
        return createAnswerChoicesMap(questarray, answerValidities);
    }

    private static String formateaPregunta(final String strPregunta) {
        return LINE_BREAK.matcher(strPregunta).replaceAll("\n");
    }

}
