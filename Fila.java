package AtividadeFinal;

import java.util.LinkedList;
import java.util.Queue;

public class Fila extends EstrategiaFIFO {
    private Queue<Senha> filaNormal;
    private Queue<Senha> filaIdoso;
    private Queue<Senha> filaIdoso80;
    private Queue<Senha> filaPreferencial;
    private Queue<Senha> filaUrgente;
    private Queue<Senha> filaVip;
    private Senha ultimaSenhaAtendida;

    public Fila() {
        this.filaNormal = new LinkedList<>();
        this.filaIdoso = new LinkedList<>();
        this.filaIdoso80 = new LinkedList<>();
        this.filaPreferencial = new LinkedList<>();
        this.filaUrgente = new LinkedList<>();
        this.filaVip = new LinkedList<>();
    }

    @Override
    public String inserir() {
        // tamo inserindo a senha normalzona (esqueci de deixar do meu jeito e nao do pessoal de lá)
        return inserir(TipoLista.NORMAL);
    }

    public String inserir(TipoLista tipo) {
        Senha senha = new Senha();
        senha.gerarSenha();

        switch (tipo) {
            case IDOSO:
                filaIdoso.add(senha);
                break;
            case IDOSO80:
                filaIdoso80.add(senha);
                break;
            case NORMAL:
                filaNormal.add(senha);
                break;
            case PREFERENCIAL:
                filaPreferencial.add(senha);
                break;
            case URGENTE:
                filaUrgente.add(senha);
                break;
            case VIP:
                filaVip.add(senha);
                break;
            default:
                throw new IllegalArgumentException("Tipo de fila inválido: " + tipo);
        }

        return "Senha " + senha.retornarSenha() + " inserida na fila " + tipo.tipo + ".";
    }

    @Override
    public void remover() {
        if (ultimaSenhaAtendida != null) {
            TipoLista tipo = obterTipoSenha(ultimaSenhaAtendida);
            if (tipo != null) {
                switch (tipo) {
                    case IDOSO:
                        filaIdoso.remove(ultimaSenhaAtendida);
                        break;
                    case IDOSO80:
                        filaIdoso80.remove(ultimaSenhaAtendida);
                        break;
                    case NORMAL:
                        filaNormal.remove(ultimaSenhaAtendida);
                        break;
                    case PREFERENCIAL:
                        filaPreferencial.remove(ultimaSenhaAtendida);
                        break;
                    case URGENTE:
                        filaUrgente.remove(ultimaSenhaAtendida);
                        break;
                    case VIP:
                        filaVip.remove(ultimaSenhaAtendida);
                        break;
                }
            }
        }
    }

    @Override
    public String chamar() {
        Senha senha = null;
        if (!filaUrgente.isEmpty()) {
            senha = filaUrgente.peek();
        } else if (!filaPreferencial.isEmpty()) {
            senha = filaPreferencial.peek();
        } else if (!filaVip.isEmpty()) {
            senha = filaVip.peek();
        } else if (!filaIdoso80.isEmpty()) {
            senha = filaIdoso80.peek();
        } else if (!filaIdoso.isEmpty()) {
            senha = filaIdoso.peek();
        } else if (!filaNormal.isEmpty()) {
            senha = filaNormal.peek();
        }

        if (senha != null) {
            senha.setChamado();
            return "Senha " + senha.retornarSenha() + " chamada.";
        }

        return "Todas as filas estão vazias.";
    }
    @Override
    public String atender() {
        Senha senha = null;
        if (!filaUrgente.isEmpty()) {
            senha = filaUrgente.poll();
        } else if (!filaPreferencial.isEmpty()) {
            senha = filaPreferencial.poll();
        } else if (!filaVip.isEmpty()) {
            senha = filaVip.poll();
        } else if (!filaIdoso80.isEmpty()) {
            senha = filaIdoso80.poll();
        } else if (!filaIdoso.isEmpty()) {
            senha = filaIdoso.poll();
        } else if (!filaNormal.isEmpty()) {
            senha = filaNormal.poll();
        }

        if (senha != null) {
            ultimaSenhaAtendida = senha; // Armazena a última senha atendida
            return "Senha " + senha.retornarSenha() + " atendida.";
        }

        return "Todas as filas estão vazias.";
    }

    @Override
    public String listar() {
        StringBuilder sb = new StringBuilder();
        sb.append("Urgente:\n");
        for (Senha senha : filaUrgente) {
            sb.append(senha.retornarSenha()).append("\n");
        }
        sb.append("Preferencial:\n");
        for (Senha senha : filaPreferencial) {
            sb.append(senha.retornarSenha()).append("\n");
        }
        sb.append("VIP:\n");
        for (Senha senha : filaVip) {
            sb.append(senha.retornarSenha()).append("\n");
        }
        sb.append("Idoso80:\n");
        for (Senha senha : filaIdoso80) {
            sb.append(senha.retornarSenha()).append("\n");
        }
        sb.append("Idoso:\n");
        for (Senha senha : filaIdoso) {
            sb.append(senha.retornarSenha()).append("\n");
        }
        sb.append("Normal:\n");
        for (Senha senha : filaNormal) {
            sb.append(senha.retornarSenha()).append("\n");
        }
        return sb.toString();
    }
}
