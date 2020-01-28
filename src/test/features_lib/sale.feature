#language:pt
@VendaTeste
Funcionalidade: Testar a venda digitada
  Essa funcionalidade permite realizar uma venda digitada através da PGWebLib

  Contexto: Dados para instalação e venda
  | PdC   | cnpj            | cartao           | vencimento | valor | status-transcao |
  | 54460 | 054714160000101 | 4444333322221111 | 0229       | 3200  | null            |

  Cenario: Venda digitada autorizada
    Venda realizada através da função newTransac(Sale) com a venda digitada

    Dado Terminal seja instalado com o PdC 54460 e CNPJ 05471416000101 no ambiente PGWeb app.tpgw.ntk.com.br:17502
    Quando Realizar a venda digitada com o cartao 4444333322221111 e data de vencimento 0229 no valor de 1700 reais
    Entao Confirme a venda
    E Mostre o resultado
    # caso queira desfazer a transação escreva Desfaca, ou Confirme para confirmar, ou ainda se desejar que fique
      # pendente escreva qualquer coisa diferente do que foi dito
