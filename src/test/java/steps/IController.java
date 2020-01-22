package steps;

import steps.enumApp.CARDTYPE;
import steps.enumApp.FINTYPE;

public interface IController {

    boolean install( String pdc, String cnpj, String pgweb);

    boolean sale(int amount, FINTYPE fintype, CARDTYPE cardtype, String cardNumber, String dataVenc);

    boolean saleVoid(int amount, FINTYPE fintype, CARDTYPE cardtype);

    boolean confirmation(String option);

    String showData();
}
