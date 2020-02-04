package steps;

import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import cucumber.api.java.it.E;
import cucumber.api.java.it.Quando;
import cucumber.api.java.pt.Entao;
import steps.enumApp.APP;
import steps.enumApp.CARDTYPE;
import steps.enumApp.FINTYPE;

import static steps.enumApp.APP.*;

public class Steps {
    private IController controller;
    private APP app;

    @Before
    public void initializer(){
        app = PAYGOCONV; //colocar como parametro de um arquivo
        switch (app){
            case LIBCW:
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

    @Dado("^que a biblioteca seja inicializada$")
    public void inicializar_lib(){
        controller.init(".");
    }

    @Dado("^terminal esteja instalado com o PdC (.*)$")
    public void terminal_instalado(String PdC) throws Throwable{
        controller.consultarPdC(PdC);
    }

    @Dado("^for instalado com o (.*) e CNPJ (.*) no ambiente PGWeb (.*)$")
    public void instalar_terminal(String pdc, String cnpj, String pgweb) throws Throwable{
        controller.install(pdc,cnpj,pgweb);
    }

    @Quando("^(?:fazer|realizar) a venda digitada com o cartao (.*) e data de vencimento (.*) no valor de (.*) reais$")
    public void fazer_a_venda( String cardNumber, String dataVenc, String amount) throws Throwable {
        controller.sale(Integer.parseInt(amount), FINTYPE.IN_CASH, CARDTYPE.CREDIT,cardNumber,dataVenc);
    }

    @E("^(Confirme|confirme|Desfaca|desfaca) a venda$")
    public void confirmar_venda( String option) throws Throwable {
        controller.confirmation(option);
    }

    @Entao("^o resultado da instalação deve ser (.*)$")
    public void resultado_instalacao(String status) throws IllegalAccessException {
        System.out.println(controller.statusDaInstalacao(status));
    }

    @Entao("^venda deve ter o status (.*)$")
    public void status_da_venda( String status) throws Throwable {
        System.out.println(controller.statusDaVenda(status));
    }

}
