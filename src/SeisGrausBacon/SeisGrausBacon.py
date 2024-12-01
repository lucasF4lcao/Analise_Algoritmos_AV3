import pandas as pd
from collections import defaultdict, deque

dados_filmes = pd.read_csv('tmdb_5000_movies.csv')
dados_creditos = pd.read_csv('tmdb_5000_credits.csv')


def montar_grafo(creditos):
    conexoes = defaultdict(set)
    index = 0
    while index < len(creditos):
        linha = creditos.iloc[index]
        elenco = eval(linha['cast'])
        nomes = [pessoa['name'] for pessoa in elenco]

        for i in range(len(nomes)):
            for j in range(i + 1, len(nomes)):
                ator1 = nomes[i]
                ator2 = nomes[j]
                conexoes[ator1].add(ator2)
                conexoes[ator2].add(ator1)

        index += 1

    return conexoes

def buscar_caminho(conexoes, inicio, fim):
    if inicio not in conexoes or fim not in conexoes:
        return None

    visitados = set()
    fila = deque([(inicio, [inicio])])

    for _ in range(len(conexoes)):
        if not fila:
            break
        atual, caminho = fila.popleft()
        if atual == fim:
            return caminho
        visitados.add(atual)
        for vizinho in conexoes[atual]:
            if vizinho not in visitados:
                fila.append((vizinho, caminho + [vizinho]))

    return None


grafo_atores = montar_grafo(dados_creditos)

ator_inicial = "Tom Holland"
ator_destino = "Kevin Bacon"
caminho_resultado = buscar_caminho(grafo_atores, ator_inicial, ator_destino)

if caminho_resultado:
    print(f"Caminho encontrado entre {ator_inicial} e {ator_destino}:")
    print(" -> ".join(caminho_resultado))
else:
    print(f"Nenhuma conexão entre {ator_inicial} e {ator_destino}.")
