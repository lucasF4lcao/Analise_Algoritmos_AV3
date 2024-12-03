package CaixeiroViajante;

import java.util.ArrayList;
import java.util.List;

class Cidade {
    int id;
    double x, y;

    Cidade(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    double calcularDistancia(Cidade outra) {
        return Math.sqrt(Math.pow(outra.x - this.x, 2) + Math.pow(outra.y - this.y, 2));
    }

    @Override
    public String toString() {
        return "Cidade " + id;
    }
}

public class CaixeiroViajante {

    private static double menorDistancia = Double.MAX_VALUE;
    private static List<Cidade> melhorCaminho = new ArrayList<>();

    private static double calcularDistanciaTotal(List<Cidade> cidades) {
        double distancia = 0.0;
        for (int i = 0; i < cidades.size() - 1; i++) {
            distancia += cidades.get(i).calcularDistancia(cidades.get(i + 1));
        }
        distancia += cidades.get(cidades.size() - 1).calcularDistancia(cidades.get(0));
        return distancia;
    }

    private static void gerarPermutacoes(List<Cidade> cidades, int inicio) {
        if (inicio == cidades.size()) {
            double distanciaAtual = calcularDistanciaTotal(cidades);
            if (distanciaAtual < menorDistancia) {
                menorDistancia = distanciaAtual;
                melhorCaminho.clear();
                melhorCaminho.addAll(cidades);
            }
        } else {
            for (int i = inicio; i < cidades.size(); i++) {
                trocar(cidades, inicio, i);
                gerarPermutacoes(cidades, inicio + 1);
                trocar(cidades, inicio, i);
            }
        }
    }

    private static void trocar(List<Cidade> cidades, int i, int j) {
        Cidade temp = cidades.get(i);
        cidades.set(i, cidades.get(j));
        cidades.set(j, temp);
    }

    private static void exibirMelhorCaminho() {
        System.out.println("Melhor caminho: ");
        for (Cidade cidade : melhorCaminho) {
            System.out.print(cidade + " -> ");
        }
        System.out.println(melhorCaminho.get(0));

        System.out.println("Distância total: " + menorDistancia);

        for (int i = 0; i < melhorCaminho.size(); i++) {
            Cidade atual = melhorCaminho.get(i);
            Cidade proxima = melhorCaminho.get((i + 1) % melhorCaminho.size());
            System.out.println("Distância de " + atual + " para " + proxima + ": " + atual.calcularDistancia(proxima));
        }
    }

    public static void main(String[] args) {
        List<Cidade> cidades = new ArrayList<>();
        cidades.add(new Cidade(1, 2, 3));
        cidades.add(new Cidade(2, 9, 9));
        cidades.add(new Cidade(3, 6, 4));
        cidades.add(new Cidade(4, 8, 9));
        cidades.add(new Cidade(5, 9, 7));


        menorDistancia = Double.MAX_VALUE;
        melhorCaminho.clear();

        gerarPermutacoes(cidades, 0);
        exibirMelhorCaminho();
    }
}
