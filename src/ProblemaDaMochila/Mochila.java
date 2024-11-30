package ProblemaDaMochila;

import java.util.ArrayList;
import java.util.List;

class Objeto {
    String nome;
    int peso;
    int valor;

    public Objeto(String nome, int peso, int valor) {
        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
    }
}

public class Mochila {

    public static void resolverMochila(int capacidade, List<Objeto> objetos) {
        int n = objetos.size();
        int[][] dp = new int[n + 1][capacidade + 1];

        for (int i = 1; i <= n; i++) {
            Objeto obj = objetos.get(i - 1);
            for (int w = 0; w <= capacidade; w++) {
                if (obj.peso > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - obj.peso] + obj.valor);
                }
                System.out.printf("Subproblema dp[%d][%d] = %d\n", i, w, dp[i][w]);
            }
        }

        int w = capacidade;
        List<String> itensSelecionados = new ArrayList<>();

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                Objeto obj = objetos.get(i - 1);
                itensSelecionados.add(obj.nome);
                w -= obj.peso;
            }
        }

        System.out.println("Objetos a serem colocados na mochila para maximizar o valor:");
        for (String nome : itensSelecionados) {
            System.out.println("- " + nome);
        }
        System.out.println("Valor máximo obtido: " + dp[n][capacidade]);
    }

    public static void main(String[] args) {
        List<Objeto> objetos = new ArrayList<>();
        objetos.add(new Objeto("Objeto1", 4, 20));
        objetos.add(new Objeto("Objeto2", 3, 18));
        objetos.add(new Objeto("Objeto3", 2, 14));

        int capacidade = 7;
        resolverMochila(capacidade, objetos);
    }
}
