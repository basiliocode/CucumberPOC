#language:pt
@saleTest
Funcionalidade: Testar a venda digitada
  Essa funcionalidade permite realizar uma venda digitada através da PGWebLib

  Esquema do Cenário: Venda digitada autorizada
    Venda realizada através da função newTransac(Sale) com a venda digitada

    Dado que a biblioteca seja inicializada
    E terminal esteja instalado com o PdC 54460
    Quando realizar a venda digitada com o cartao <cartao> e data de vencimento <vencimento> no valor de <valor> reais
    Entao venda deve ter o status <status_transacao>
    E Confirme a venda
    # caso queira desfazer a transação escreva Desfaca, ou Confirme para confirmar, ou ainda se desejar que fique
      # pendente escreva qualquer coisa diferente do que foi dito

    Exemplos: dados para a venda
      | cartao           | vencimento | valor |status_transacao|
      # Venda com status APROVADA
      | 4444333322221111 |    0229    | 1700  |    OK    |
      # Venda com Time out (TENTE DE NOVO-TO)
      | 4444333322221111 |    0229    |1000099| FROMHOST |
      # Venda com mensagem invalida (TENTE DE NOVO-MI)
      | 4444333322221111 |    0229    |1000500| FROMHOST |
      # Venda com status NEGADA
      | 4444333322221111 |    0229    |1005900| FROMHOST |

      | 4444333322221111 |    0219    |1000| FROMHOST |




