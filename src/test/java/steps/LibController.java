package steps;

import junit.framework.AssertionFailedError;
import org.junit.Assert;
import steps.enumApp.CARDTYPE;
import steps.enumApp.FINTYPE;
import steps.pgweblibc.Enums.*;
import steps.pgweblibc.LibIntegrada;
import steps.pgweblibc.Transacao;

import java.util.Arrays;

public class LibController implements IController {
    private String reqNum, autLocRef, autExtRef, virthMerch, authSyst;
    private String sale;
    private String confirm;
    private PWRET pwret;
    LibIntegrada lib = new LibIntegrada();
    Transacao transacao = new Transacao();

    LibController() { }

    public void init(String path){
        lib.chamarPW_iInit(path);
    }

    public boolean install( String pdc, String cnpj, String pgweb) throws IllegalAccessException {
        validarResultado(transacao.newTransac(PWOPER.INSTALL), PWRET.OK);
        Assert.assertEquals(PWRET.OK, transacao.mandatoryParams());
        validarResultado(transacao.addParam(PWINFO.AUTHTECHUSER, "123"), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.POSID, pdc), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.MERCHCNPJCPF, cnpj), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.DESTTCPIP, pgweb), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.USINGPINPAD, "0"), PWRET.OK);
        try {
            Assert.assertEquals(PWRET.MOREDATA, transacao.execTransac()); //Solicitará removeCard
            Assert.assertEquals(PWRET.OK, transacao.removeCard());
            Assert.assertEquals(PWRET.OK, transacao.ippEventLoop());
            pwret = transacao.execTransac();//necessário 2 chamadas de execTransac
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String statusDaInstalacao( String status) throws IllegalAccessException {
        validarResultado(pwret, PWRET.valueOf(status));
        Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.RESULTMSG,100));
        String resultMsg = transacao.getPszData(true).substring(1);
        return "INSTALACAO: " + resultMsg +"\n";
    }

    public boolean consultarPdC(String PdC) throws InterruptedException, IllegalAccessException {
        validarResultado(transacao.newTransac(PWOPER.SHOWPDC), PWRET.OK);
        Assert.assertEquals(PWRET.OK, transacao.mandatoryParams());

        try {
            validarResultado(transacao.execTransac(), PWRET.OK);
            Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.RESULTMSG,100));
            String resultMsg = transacao.getPszData(true);
            Assert.assertEquals(PdC, resultMsg);
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sale(int amount, FINTYPE fintype, CARDTYPE cardtype, String cardNumber, String dataVenc) throws IllegalAccessException {
        validarResultado(transacao.newTransac(PWOPER.SALE), PWRET.OK);
        Assert.assertEquals(PWRET.OK, transacao.mandatoryParams());
        validarResultado(transacao.addParam(PWINFO.CURRENCY, "986"), PWRET.OK);

        validarResultado(transacao.addParam(PWINFO.CURREXP, "2"), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.TOTAMNT, Integer.toString(amount)), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.CARDFULLPAN, cardNumber), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.CARDEXPDATE, dataVenc), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.FINTYPE, String.valueOf(fintype.getType())), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.CVV, "123"), PWRET.OK);
        // este intervalo é solicitado pela adquirente CIELO
        validarResultado(transacao.addParam(PWINFO.CARDTYPE, String.valueOf((cardtype.getType()))), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.BOARDINGTAX, "0"), PWRET.OK);
        validarResultado(transacao.addParam(PWINFO.TIPAMOUNT, "0"), PWRET.OK);
        // Fim do intervalo
        try {
            Assert.assertEquals(PWRET.MOREDATA,transacao.execTransac()); //Solicitará removeCard
            Assert.assertEquals(PWRET.OK,transacao.removeCard());
            Assert.assertEquals(PWRET.OK, transacao.ippEventLoop());
            pwret = transacao.execTransac();//necessário 2 chamadas de execTransac
            Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.RESULTMSG,100));
            String resultMsg = transacao.getPszData(true);
            sale = "VENDA A VISTA: " + resultMsg + "\n";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public String statusDaVenda( String status) throws IllegalAccessException {
        validarResultado(pwret, PWRET.valueOf(status));
        Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.RESULTMSG,100));
        String resultMsg = transacao.getPszData(true);
        Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.REQNUM,50));
        reqNum = transacao.getPszData(true);
        if (PWRET.valueOf(status) == PWRET.OK) {
            Assert.assertEquals(PWRET.OK, transacao.getResult(PWINFO.AUTLOCREF, 50));
            autLocRef = transacao.getPszData(true);
            Assert.assertEquals(PWRET.OK, transacao.getResult(PWINFO.AUTEXTREF, 50));
            autExtRef = transacao.getPszData(true);
            Assert.assertEquals(PWRET.OK, transacao.getResult(PWINFO.VIRTMERCH, 50));
            virthMerch = transacao.getPszData(true);
            Assert.assertEquals(PWRET.OK, transacao.getResult(PWINFO.AUTHSYST, 50));
            authSyst = transacao.getPszData(true);
        }
        confirm = "\nDADOS DA TRANSACAO: \nREQNUM: " + reqNum +"\nAUTLOCREF: "+ autLocRef +"\nAUTEXTREF: "+ autExtRef
                +"\nVIRTMERCH: "+ virthMerch +"\nAUTHSYST: "+ authSyst + "\n";
        return sale + confirm;
    }

    public boolean saleVoid(int amount, FINTYPE fintype, CARDTYPE cardtype) {
        return false;
    }

    public boolean confirmation(String option) throws InterruptedException {

        if (option.equalsIgnoreCase("Confirme")){
            Assert.assertEquals(PWRET.OK,transacao.ippConfirmation(PWCNF.PWCNF_CNF_AUTO,reqNum,autLocRef,autExtRef,
                    virthMerch,authSyst));
            return true;
        } else if (option.equalsIgnoreCase("Desfaca")){
            Assert.assertEquals(PWRET.OK,transacao.ippConfirmation(PWCNF.PWCNF_REV_COMM_AUT,reqNum,autLocRef,autExtRef,
                    virthMerch,authSyst));
        }
        return false;
    }

    void validarResultado(PWRET retorno, PWRET status) throws IllegalAccessException {

        transacao.getResult(PWINFO.RESULTMSG,100);
        String resultMsg = transacao.getPszData(true);
        PWDAT tipoDeDado = transacao.getBtipoDeDado(transacao.getInumParam() - 1);
        String menus = "";

        if (tipoDeDado == PWDAT.MENU) {
            menus = Arrays.toString(transacao.getMenu(transacao.getInumParam() - 1));
        }

        if (retorno != status) {
            throw new AssertionFailedError(
                    String.format("\nExpected: %s\nActual: %s  \nRESULTMSG: (%s) \nTIPO DADO: %s %s", PWRET.OK,
                            retorno, resultMsg, tipoDeDado, menus));
        }
    }
}
