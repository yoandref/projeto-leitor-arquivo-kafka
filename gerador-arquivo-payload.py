from datetime import datetime, timedelta
import random


def gerar_cpf_ficticio() -> str:
    """Gera um CPF fictício de 11 dígitos exatos (garante zeros à esquerda)."""
    return f"{random.randint(0, 99999999999):011d}"


def gerar_dados_cartao(qtd_digitos_total: int = 16) -> str:
    """
    Gera um cartão mascarado com BIN (6 dígitos) + Asteriscos + 4 Últimos.
    Por padrão gera 16 dígitos no total (6 + 6 + 4).
    Exemplo: 452319******1234
    """
    bin_cartao = f"{random.randint(400000, 499999):06d}"  # 6 dígitos
    ultimos_4 = f"{random.randint(0, 9999):04d}"           # 4 dígitos

    # Calcula a quantidade necessária de asteriscos para fechar a quantidade total de dígitos
    qtd_asteriscos = qtd_digitos_total - (len(bin_cartao) + len(ultimos_4))
    asteriscos = "*" * qtd_asteriscos

    dados_cartao = f"{bin_cartao}{asteriscos}{ultimos_4}"
    
    assert len(dados_cartao) == qtd_digitos_total, f"Tamanho incorreto do cartão: {len(dados_cartao)}"
    return dados_cartao


def gerar_arquivo_posicional(
    nome_arquivo: str = "transacoes_larga_escala.txt",
    quantidade_linhas: int = 10000,
):
    # Definimos 16 dígitos para o cartão -> Tamanho total da linha = 11 + 16 + 12 + 1 + 14 = 54 chars
    tamanho_linha_esperado = 54
    
    print(f"Gerando {quantidade_linhas:,} linhas com exatamente {tamanho_linha_esperado} caracteres cada...")

    # Mapeamento do tipo para o valor em centavos
    regras_valor = {
        "A": 1500,  # R$ 15,00
        "R": 4500,  # R$ 45,00
        "M": 0,     # R$ 0,00
    }

    data_base = datetime(2026, 7, 25, 8, 0, 0)

    with open(nome_arquivo, "w", encoding="utf-8") as f:
        for i in range(quantidade_linhas):
            cpf = gerar_cpf_ficticio()                      # 11 caracteres
            cartao = gerar_dados_cartao(qtd_digitos_total=16) # 16 caracteres (ex: 412345******1234)

            # Escolhe o tipo aleatoriamente
            tipo = random.choice(["A", "R", "M"])            # 1 caracter

            # Formata o valor com 12 dígitos e zeros à esquerda
            valor_centavos = regras_valor[tipo]
            valor_formatado = f"{valor_centavos:012d}"        # 12 caracteres

            # Simula avanço de tempo nas transações
            data_transacao = data_base + timedelta(seconds=i * 2)
            # Formatação para YYYYMMDDHHmmss (14 caracteres)
            data_formatada = data_transacao.strftime("%Y%m%d%H%M%S")

            # Monta a linha sem o quebra de linha para validar o tamanho
            linha_conteudo = f"{cpf}{cartao}{valor_formatado}{tipo}{data_formatada}"
            
            # Validação do tamanho da linha
            assert len(linha_conteudo) == tamanho_linha_esperado, (
                f"Erro na linha {i+1}: possui {len(linha_conteudo)} caracteres em vez de {tamanho_linha_esperado}."
            )

            f.write(f"{linha_conteudo}\n")

    print(f"Arquivo '{nome_arquivo}' gerado com sucesso!")


if __name__ == "__main__":
    gerar_arquivo_posicional("transacoes_10k.txt", quantidade_linhas=10000)
