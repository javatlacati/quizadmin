/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javapro.quizadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import static org.javapro.quizadmin.QuestionParser.parseQuestion;

/**
 *
 * @author RuslanLopez
 */
public class QuizAdmin extends JFrame {

    private List<Question> questions = new ArrayList<>();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    JButton agregarOpcion;
    JButton btnAgregarPregunta;
    JButton btnAnterior;
    JComboBox<String> btnIrAUnaEspecífica;
    JButton btnQuitarPregunta;
    JButton btnSiguente;
    JComboBox<String> cmbTipoPregunta;
    JLabel lblEnunciado;
    JLabel lblOpciones;
    JLabel lblRespuesta;
    JLabel lblTipoPregunta;
    JPanel opcionespnl;
    ButtonGroup optionButtonsGroup;
    JPanel pnlControles;
    JPanel pnlDatosPregunta;
    JPanel pnlOpciones;
    JScrollPane pnlOpcionesRespuesta;
    JPanel pnlQuestionCreationDeletion;
    JPanel pnlQuestionNavigation;
    JPanel pnlRespuesta;
    JPanel pnleditarOpciones;
    JPanel pnlenunciadoPregunta;
    JButton quitarOpcion;
    JPanel respuestapnl;
    JScrollPane scrollBlancos;
    JTextField txtEnunciado;
    JTextArea txtRespuesta;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form QuizAdmin
     */
    public QuizAdmin() {
        initComponents();
        // TODO add jfilechooser to open and edit a file
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        optionButtonsGroup = new ButtonGroup();
        pnlDatosPregunta = new JPanel();
        pnlenunciadoPregunta = new JPanel();
        lblEnunciado = new JLabel();
        txtEnunciado = new JTextField();
        lblTipoPregunta = new JLabel();
        cmbTipoPregunta = new JComboBox<>();
        opcionespnl = new JPanel();
        lblOpciones = new JLabel();
        //lblOpciones.setVisible(false);
        //lblOpciones.setBounds(0, 0, 0, 0);
        pnlOpciones = new JPanel();
        //pnlOpciones.setVisible(false);
        //lblOpciones.setBounds(0, 0, 0, 0);
        pnlOpcionesRespuesta = new JScrollPane();
        pnleditarOpciones = new JPanel();
        agregarOpcion = new JButton();
        quitarOpcion = new JButton();
        respuestapnl = new JPanel();
        lblRespuesta = new JLabel();
        pnlRespuesta = new JPanel();
        scrollBlancos = new JScrollPane();
        txtRespuesta = new JTextArea();
        pnlControles = new JPanel();
        pnlQuestionCreationDeletion = new JPanel();
        btnQuitarPregunta = new JButton();
        btnAgregarPregunta = new JButton();
        pnlQuestionNavigation = new JPanel();
        btnAnterior = new JButton();
        btnSiguente = new JButton();
        btnIrAUnaEspecífica = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ResourceBundle bundle = ResourceBundle.getBundle("org/javapro/quizadmin/Bundle"); // NOI18N
        pnlDatosPregunta.setBorder(BorderFactory.createTitledBorder(bundle.getString("QuizAdmin.pnlDatosPregunta.border.title"))); // NOI18N
        pnlDatosPregunta.setLayout(new BoxLayout(pnlDatosPregunta, BoxLayout.Y_AXIS));

        pnlenunciadoPregunta.setLayout(new GridLayout(0, 2));

        lblEnunciado.setText(bundle.getString("QuizAdmin.lblEnunciado.text")); // NOI18N
        pnlenunciadoPregunta.add(lblEnunciado);
        pnlenunciadoPregunta.add(txtEnunciado);

        lblTipoPregunta.setText(bundle.getString("QuizAdmin.lblTipoPregunta.text")); // NOI18N
        pnlenunciadoPregunta.add(lblTipoPregunta);

        cmbTipoPregunta.setModel(new DefaultComboBoxModel<>(new String[] { "Llenar el espacio en blanco", "Opción Múltiple", "Respuesta Múltiple" }));
        pnlenunciadoPregunta.add(cmbTipoPregunta);

        pnlDatosPregunta.add(pnlenunciadoPregunta);

        opcionespnl.setLayout(new BoxLayout(opcionespnl, BoxLayout.LINE_AXIS));

        lblOpciones.setText(bundle.getString("QuizAdmin.lblOpciones.text")); // NOI18N
        opcionespnl.add(lblOpciones);

        pnlOpciones.setLayout(new GridBagLayout());

        pnlOpcionesRespuesta.setMinimumSize(new Dimension(125, 125));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlOpciones.add(pnlOpcionesRespuesta, gridBagConstraints);

        pnleditarOpciones.setLayout(new BoxLayout(pnleditarOpciones, BoxLayout.Y_AXIS));

        agregarOpcion.setText(bundle.getString("QuizAdmin.agregarOpcion.text")); // NOI18N
        agregarOpcion.setEnabled(false);
        pnleditarOpciones.add(agregarOpcion);

        quitarOpcion.setText(bundle.getString("QuizAdmin.quitarOpcion.text")); // NOI18N
        quitarOpcion.setEnabled(false);
        pnleditarOpciones.add(quitarOpcion);

        pnlOpciones.add(pnleditarOpciones, new GridBagConstraints());

        opcionespnl.add(pnlOpciones);

        pnlDatosPregunta.add(opcionespnl);

        respuestapnl.setLayout(new FlowLayout(FlowLayout.LEFT));

        lblRespuesta.setText(bundle.getString("QuizAdmin.lblRespuesta.text")); // NOI18N
        respuestapnl.add(lblRespuesta);

        pnlRespuesta.setMinimumSize(new Dimension(100, 90));
        pnlRespuesta.setPreferredSize(new Dimension(200, 90));
        pnlRespuesta.setLayout(new BorderLayout());

        txtRespuesta.setEditable(false);
        txtRespuesta.setColumns(20);
        txtRespuesta.setRows(5);
        scrollBlancos.setViewportView(txtRespuesta);

        pnlRespuesta.add(scrollBlancos, BorderLayout.CENTER);

        respuestapnl.add(pnlRespuesta);

        pnlDatosPregunta.add(respuestapnl);

        getContentPane().add(pnlDatosPregunta, BorderLayout.CENTER);

        pnlControles.setBorder(BorderFactory.createTitledBorder(bundle.getString("QuizAdmin.pnlControles.border.title"))); // NOI18N
        pnlControles.setLayout(new BorderLayout());

        pnlQuestionCreationDeletion.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        pnlQuestionCreationDeletion.setLayout(new BorderLayout());

        btnQuitarPregunta.setText(bundle.getString("QuizAdmin.btnQuitarPregunta.text")); // NOI18N
        btnQuitarPregunta.setEnabled(false);
        pnlQuestionCreationDeletion.add(btnQuitarPregunta, BorderLayout.EAST);

        btnAgregarPregunta.setText(bundle.getString("QuizAdmin.btnAgregarPregunta.text")); // NOI18N
        btnAgregarPregunta.setEnabled(false);
        pnlQuestionCreationDeletion.add(btnAgregarPregunta, BorderLayout.WEST);

        pnlControles.add(pnlQuestionCreationDeletion, BorderLayout.NORTH);

        pnlQuestionNavigation.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        pnlQuestionNavigation.setLayout(new BorderLayout());

        btnAnterior.setText(bundle.getString("QuizAdmin.btnAnterior.text")); // NOI18N
        btnAnterior.setEnabled(false);
        pnlQuestionNavigation.add(btnAnterior, BorderLayout.WEST);

        btnSiguente.setText(bundle.getString("QuizAdmin.btnSiguente.text")); // NOI18N
        btnSiguente.setEnabled(false);
        pnlQuestionNavigation.add(btnSiguente, BorderLayout.EAST);

        btnIrAUnaEspecífica.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        btnIrAUnaEspecífica.setEnabled(false);
        pnlQuestionNavigation.add(btnIrAUnaEspecífica, BorderLayout.CENTER);

        pnlControles.add(pnlQuestionNavigation, BorderLayout.CENTER);

        getContentPane().add(pnlControles, BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void loadQuestionsFromFile(String aFile) throws FileNotFoundException, IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(aFile)))){
            parseQuestions(reader);
        }
    }

    public void parseQuestions(BufferedReader reader) throws IOException {
        String thisLine;
        while ((thisLine = reader.readLine()) != null) {
            questions.add(parseQuestion(thisLine));
        }
        Question primeraPregunta = questions.get(0);
        System.out.println("Pintando primera pregunta:"+primeraPregunta);
        txtEnunciado.setText(primeraPregunta.getText());
        QuestionType preguntaType = primeraPregunta.getType();
        cmbTipoPregunta.setSelectedIndex(preguntaType.ordinal());
        switch (preguntaType){
            case FILL_IN_THE_BLANKS:
                FillBlankQuestion fb = (FillBlankQuestion) primeraPregunta;
                txtRespuesta.setText(fb.getAnswer());
                //TODO ocultar panel de opción
                break;
            case MULTIPLE_OPTION:
                MultipleChoiceQuestion mc = (MultipleChoiceQuestion) primeraPregunta;
                //TODO ocultar panel de respuesta
                break;
            case MULTIPLE_ANSWER:
                MultipleAnswerQuestion ma = (MultipleAnswerQuestion) primeraPregunta;
                //TODO ocultar panel de respuesta
                break;
            default:
                System.err.println("unexpected question type");
        }
    }


}
