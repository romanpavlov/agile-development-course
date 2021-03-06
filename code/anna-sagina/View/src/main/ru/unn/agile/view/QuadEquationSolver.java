package ru.unn.agile.view;

import ru.unn.agile.QSolverViewModel.QSolverViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class QuadEquationSolver {
    private JPanel mainPanel;
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JLabel solutionLabel;
    private JButton solveButton;
    private QSolverViewModel viewModel;
    private KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            bind();
            QuadEquationSolver.this.viewModel.setCoefficientValue();
            backBind();
        }
    };

    public QuadEquationSolver(QSolverViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                QuadEquationSolver.this.viewModel.RunSolver();
                backBind();
            }
        });

        textFieldA.addKeyListener(keyAdapter);
        textFieldB.addKeyListener(keyAdapter);
        textFieldC.addKeyListener(keyAdapter);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("QuadEquationSolver");
        frame.setContentPane(new QuadEquationSolver(new QSolverViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.result = this.solutionLabel.getText();
        viewModel.a = this.textFieldA.getText();
        viewModel.b = this.textFieldB.getText();
        viewModel.c = this.textFieldC.getText();
        viewModel.isSolveButtonEnabled = this.solveButton.isEnabled();
    }

    private void backBind() {
        this.solutionLabel.setText(viewModel.result);
        this.textFieldA.setText(viewModel.a);
        this.textFieldB.setText(viewModel.b);
        this.textFieldC.setText(viewModel.c);
        this.solveButton.setEnabled(viewModel.isSolveButtonEnabled);
    }

}
