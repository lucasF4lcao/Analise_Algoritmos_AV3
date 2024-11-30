package CasaDePraia;

import java.util.ArrayList;

class ArquivoPDF {
    String nome;
    int paginas;
    int peso;

    public ArquivoPDF(String nome, int paginas, int peso) {
        this.nome = nome;
        this.paginas = paginas;
        this.peso = peso;
    }

    public double relacao() {
        return (double) paginas / peso;
    }
}

public class CasaDePraia {

    public static void escolherArquivos(int capacidadePendrive, ArrayList<ArquivoPDF> arquivos) {
        for (int i = 0; i < arquivos.size() - 1; i++) {
            for (int j = i + 1; j < arquivos.size(); j++) {
                if (arquivos.get(j).relacao() > arquivos.get(i).relacao()) {
                    ArquivoPDF temp = arquivos.get(i);
                    arquivos.set(i, arquivos.get(j));
                    arquivos.set(j, temp);
                }
            }
        }

        int espacoDisponivel = capacidadePendrive;
        ArrayList<String> arquivosEscolhidos = new ArrayList<>();

        for (ArquivoPDF arquivo : arquivos) {
            if (arquivo.peso <= espacoDisponivel) {
                arquivosEscolhidos.add(arquivo.nome);
                espacoDisponivel -= arquivo.peso;
                System.out.println("Escolhido: " + arquivo.nome + " | Páginas: " + arquivo.paginas + " | Peso: " + arquivo.peso + "MB");
            }
        }

        System.out.println("\nArquivos escolhidos para o pendrive:");
        for (String nome : arquivosEscolhidos) {
            System.out.println(nome);
        }
    }

    public static void main(String[] args) {
        ArrayList<ArquivoPDF> arquivos = new ArrayList<>();
        arquivos.add(new ArquivoPDF("Algoritmos 101", 120, 30));
        arquivos.add(new ArquivoPDF("Estruturas de Dados", 200, 50));
        arquivos.add(new ArquivoPDF("Análise de Algoritmos", 180, 40));
        arquivos.add(new ArquivoPDF("Inteligência Artificial", 150, 35));
        arquivos.add(new ArquivoPDF("Computação Gráfica", 100, 25));

        int capacidadePendrive = 100;

        escolherArquivos(capacidadePendrive, arquivos);
    }
}
