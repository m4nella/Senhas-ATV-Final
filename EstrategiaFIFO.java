package AtividadeFinal;

/*
 * Autor         Prof. Marcos
 * Data          15/03/2024
 * Disciplina    Programação Orientada a Objetos
 * Atividade     AVALIATIVA FINAL (3SI)
 */

public abstract class EstrategiaFIFO {
    //Define a classe abstrata 'EstrategiaFIFO' a partir do método FIFO

    public abstract String inserir();

    public abstract void remover();

    public abstract String chamar();

    public abstract String atender();

    public abstract String listar();
}
