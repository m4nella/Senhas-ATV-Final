package AtividadeFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleDeFila extends JFrame {
    private Fila fila;

    private JTextArea textArea;
    private JComboBox<String> filaComboBox;

    public ControleDeFila() {
        fila = new Fila();

        setTitle("Controle de Fila");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        filaComboBox = new JComboBox<>(new String[]{"Idoso", "Idoso80", "Normal", "Preferencial", "Urgente", "VIP"});
        panel.add(new JLabel("Tipo de Fila:"));
        panel.add(filaComboBox);

        JButton inserirButton = new JButton("Inserir Senha");
        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirSenha();
            }
        });
        panel.add(inserirButton);

        JButton removerButton = new JButton("Remover Senha");
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerSenha();
            }
        });
        panel.add(removerButton);

        JButton chamarButton = new JButton("Chamar Senha");
        chamarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chamarSenha();
            }
        });
        panel.add(chamarButton);

        JButton atenderButton = new JButton("Atender Senha");
        atenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atenderSenha();
            }
        });
        panel.add(atenderButton);

        add(panel, BorderLayout.SOUTH);
    }

    private void inserirSenha() {
        String tipo = (String) filaComboBox.getSelectedItem();
        TipoLista tipoLista = TipoLista.valueOf(tipo.toUpperCase());
        String resultado = fila.inserir(tipoLista);
        textArea.append(resultado + "\n");
    }

    private void removerSenha() {
        fila.remover();
        textArea.append("Senha removida.\n");
    }

    private void chamarSenha() {
        String resultado = fila.chamar();
        textArea.append(resultado + "\n");
    }

    private void atenderSenha() {
        String resultado = fila.atender();
        textArea.append(resultado + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControleDeFila().setVisible(true);
            }
        });
    }
}
