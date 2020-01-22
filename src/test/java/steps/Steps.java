package steps;

import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import cucumber.api.java.it.E;
import cucumber.api.java.it.Quando;
import cucumber.api.java.pt.Entao;
import steps.enumApp.APP;
import steps.enumApp.CARDTYPE;
import steps.enumApp.FINTYPE;
import steps.pgweblibc.ChamarFuncoes;


import static steps.enumApp.APP.*;

public class Steps {
    private IController controller;
    private APP app;

    @Before
    public void initializer(){
        app = LIBCW; //colocar como parametro de um arquivo
        switch (app){
            case LIBCW:
                ChamarFuncoes.chamarPW_iInit(".");
                controller = new LibController();
                break;
            case LIBCL: System.out.println(" Linux ");
                break;
            case CONTROLPAY: System.out.println(" ControlPay ");
                break;
            case PAYGOCONV:
                controller = new PayGoController();
                break;
        }

    }

    @Dado("^Terminal seja instalado com o PdC (.*) e CNPJ (.*) no ambiente PGWeb (.*)$")
    public void terminal_instalado(String pdc, String cnpj, String pgweb)  throws Throwable{
        controller.install(pdc,cnpj,pgweb);
    }

    @Quando("^(?:Fazer|Realizar) a venda digitada com o cartao (.*) e data de vencimento (.*) no valor de (.*00) reais$")
    public void fazer_a_venda( String cardNumber, String dataVenc, String amount) throws Throwable {
        controller.sale(Integer.parseInt(amount), FINTYPE.IN_CASH, CARDTYPE.CREDIT,cardNumber,dataVenc);
    }

    @Entao("^(Confirme|confirme|Desfaca|desfaca) a venda$")
    public void confirmar_venda( String option) throws Throwable {
        controller.confirmation(option);
    }

    @E("^(?:Mostre|Exiba) o resultado$")
    public void mostre_venda_autorizada() throws Throwable {
        System.out.println(controller.showData());
    }

}
