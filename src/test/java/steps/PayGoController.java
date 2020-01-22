package steps;

import steps.enumApp.CARDTYPE;
import steps.enumApp.FINTYPE;

public class PayGoController implements IController  {

    public boolean install(String pdc, String cnpj, String pgweb) {
        System.out.println("LUCAS IMPLEMENTA A INSTALACAO DO PG CONVENCIONAL COM OS PARAMETROS ("+ pdc +" "+ " "
                + cnpj +" "+ pgweb +")");
        return false;
    }

    public boolean sale(int amount, FINTYPE fintype, CARDTYPE cardtype, String cardNumber, String dataVenc) {
        System.out.println("LUCAS IMPLEMENTA A VENDA DO PG CONVENCIONAL COM OS PARAMETROS ("+ amount +" "+ " "
                + fintype +" "+ cardtype +" "+ cardNumber +" "+ dataVenc +")");
        return false;
    }

    public boolean saleVoid(int amount, FINTYPE fintype, CARDTYPE cardtype) {
        System.out.println("LUCAS IMPLEMENTA O CANCELAMENTO DO PG CONVENCIONAL COM OS PARAMETROS ("+ amount +" "+ " "
                + fintype +" "+ cardtype +")");
        return false;
    }

    public boolean confirmation(String option) {
        System.out.println("LUCAS IMPLEMENTA A CONFIRMACAO DO PG CONVENCIONAL COM O PARAMETRO ("+ option+")");
        return false;
    }

    public String showData() {
        return "LUCAS IMPLEMENTA A EXIBICAO DE DADOS DO PG CONVENCIONAL";
    }
}
