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
        return "CaixeiroViajante.Cidade " + id;
    }
}

public class CaixeiroViajante {

    private static double calcularDistanciaTotal(List<Cidade> cidades) {
        double distancia = 0.0;
        for (int i = 0; i < cidades.size() - 1; i++) {
            distancia += cidades.get(i).calcularDistancia(cidades.get(i + 1));
        }
        distancia += cidades.get(cidades.size() - 1).calcularDistancia(cidades.get(0));
        return distancia;
    }

    private static void gerarPermutacoes(List<Cidade> cidades, int inicio, List<Cidade> melhorCaminho, double melhorDistancia) {
        if (inicio == cidades.size()) {
            double distanciaAtual = calcularDistanciaTotal(cidades);
            if (distanciaAtual < melhorDistancia) {
                melhorDistancia = distanciaAtual;
                melhorCaminho.clear();
                melhorCaminho.addAll(cidades);
            }
        } else {
            for (int i = inicio; i < cidades.size(); i++) {
                trocar(cidades, inicio, i);
                gerarPermutacoes(cidades, inicio + 1, melhorCaminho, melhorDistancia);
                trocar(cidades, inicio, i);
            }
        }
    }

    private static void trocar(List<Cidade> cidades, int i, int j) {
        Cidade temp = cidades.get(i);
        cidades.set(i, cidades.get(j));
        cidades.set(j, temp);
    }

    private static void exibirMelhorCaminho(List<Cidade> melhorCaminho, double menorDistancia) {
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
        cidades.add(new Cidade(1, 1, 1));
        cidades.add(new Cidade(2, 4, 1));
        cidades.add(new Cidade(3, 4, 5));
        cidades.add(new Cidade(4, 1, 5));
        cidades.add(new Cidade(5, 2, 3));
        cidades.add(new Cidade(6, 5, 3));
        cidades.add(new Cidade(7, 2, 6));
        cidades.add(new Cidade(8, 6, 2));
        cidades.add(new Cidade(9, 7, 7));
        cidades.add(new Cidade(10, 3, 4));

        List<Cidade> melhorCaminho = new ArrayList<>();
        double menorDistancia = Double.MAX_VALUE;

        gerarPermutacoes(cidades, 0, melhorCaminho, menorDistancia);

        exibirMelhorCaminho(melhorCaminho, menorDistancia);
    }
}
