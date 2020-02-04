package steps;

import steps.enumApp.CARDTYPE;
import steps.enumApp.FINTYPE;

public interface IController {

    void init(String path);

    boolean install( String pdc, String cnpj, String pgweb) throws IllegalAccessException;

    boolean consultarPdC(String PdC) throws InterruptedException, IllegalAccessException;

    boolean sale(int amount, FINTYPE fintype, CARDTYPE cardtype, String cardNumber, String dataVenc) throws IllegalAccessException;

    boolean saleVoid(int amount, FINTYPE fintype, CARDTYPE cardtype);

    boolean confirmation(String option) throws InterruptedException;

    String statusDaVenda( String status) throws IllegalAccessException;

    String statusDaInstalacao(String status) throws IllegalAccessException;
}
