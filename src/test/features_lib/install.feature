#language:pt
@installTest
Funcionalidade: Testar a intalação
  Essa funcionalidade permite realizar uma instalação através da PGWebLib

  Esquema do Cenário: Instalação
  Instalação realizada através da função newTransac(Install).

    Dado que a biblioteca seja inicializada
    Quando for instalado com o <PdC> e CNPJ <cnpj> no ambiente PGWeb <ambiente>
    Entao o resultado da instalação deve ser <status_instalacao>

    Exemplos: dados para instalação
      | PdC   |       cnpj     |          ambiente          | status_instalacao |
      # Instalação com CNPJ incorreto
      | 54460 | 0547141600010x | app.tpgw.ntk.com.br:17502  |FROMHOSTUSRAUTHERR |
      # Instalação com ambiente incorreto
      | 54460 | 05471416000101 | teste.tpgw.ntk.com.br:17502|    HOSTCONNERR    |
      # Instalação com sucesso
      | 54460 | 05471416000101 | app.tpgw.ntk.com.br:17502  |        OK         |



