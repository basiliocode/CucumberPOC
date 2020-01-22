package steps;

import org.junit.Assert;
import steps.enumApp.CARDTYPE;
import steps.enumApp.FINTYPE;
import steps.pgweblibc.Enums.PWCNF;
import steps.pgweblibc.Enums.PWINFO;
import steps.pgweblibc.Enums.PWOPER;
import steps.pgweblibc.Enums.PWRET;

public class LibController extends TesteBase implements IController {

    private String install;
    private String sale;
    private String confirm;

    LibController() { }

    public boolean install( String pdc, String cnpj, String pgweb) {
        Assert.assertEquals(PWRET.OK, newTransac(PWOPER.INSTALL));
        Assert.assertEquals(PWRET.OK, transacao.mandatoryParams());
        Assert.assertEquals(PWRET.OK,addParam(PWINFO.AUTHTECHUSER, "123"));
        Assert.assertEquals(PWRET.OK,addParam(PWINFO.POSID, pdc));
        Assert.assertEquals(PWRET.OK,addParam(PWINFO.MERCHCNPJCPF, cnpj));
        Assert.assertEquals(PWRET.OK,addParam(PWINFO.DESTTCPIP, pgweb));
        Assert.assertEquals(PWRET.OK,addParam(PWINFO.USINGPINPAD, "0"));
        try {
            Assert.assertEquals(PWRET.MOREDATA, transacao.execTransac()); //Solicitará removeCard
            Assert.assertEquals(PWRET.OK, transacao.removeCard());
            Assert.assertEquals(PWRET.OK, transacao.ippEventLoop());
            Assert.assertEquals(PWRET.OK, execTransac());//necessário 2 chamadas de execTransac
            Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.RESULTMSG,100));
            String resultMsg = transacao.getPszData(true);
            install = "INSTALACAO: " + resultMsg +"\n";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sale(int amount, FINTYPE fintype, CARDTYPE cardtype, String cardNumber, String dataVenc) {
        Assert.assertEquals(PWRET.OK, newTransac(PWOPER.SALE));
        Assert.assertEquals(PWRET.OK, transacao.mandatoryParams());
        Assert.assertEquals(PWRET.OK, addParam(PWINFO.CURRENCY,"986"));
        Assert.assertEquals(PWRET.OK, addParam(PWINFO.CURREXP,"2"));
        Assert.assertEquals(PWRET.OK, addParam(PWINFO.TOTAMNT,Integer.toString(amount)));
        Assert.assertEquals(PWRET.OK, addParam(PWINFO.CARDFULLPAN,cardNumber));
        Assert.assertEquals(PWRET.OK, addParam(PWINFO.CARDEXPDATE, dataVenc));
        Assert.assertEquals(PWRET.OK, addParam(PWINFO.FINTYPE,String.valueOf(fintype.getType())));
        Assert.assertEquals(PWRET.OK, addParam(PWINFO.CVV,"123"));
        // este intervalo é solicitado pela adquirente CIELO
        Assert.assertEquals(PWRET.OK,addParam(PWINFO.CARDTYPE, String.valueOf((cardtype.getType()))));
        Assert.assertEquals(PWRET.OK,addParam(PWINFO.BOARDINGTAX,"0"));
        Assert.assertEquals(PWRET.OK,addParam(PWINFO.TIPAMOUNT,"0"));
        // Fim do intervalo
        try {
            Assert.assertEquals(PWRET.MOREDATA,transacao.execTransac()); //Solicitará removeCard
            Assert.assertEquals(PWRET.OK,transacao.removeCard());
            Assert.assertEquals(PWRET.OK, transacao.ippEventLoop());
            Assert.assertEquals(PWRET.OK, execTransac());//necessário 2 chamadas de execTransac
            Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.RESULTMSG,100));
            String resultMsg = transacao.getPszData(true);
            sale = "VENDA A VISTA: " + resultMsg + "\n";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean saleVoid(int amount, FINTYPE fintype, CARDTYPE cardtype) {
        return false;
    }

    public boolean confirmation(String option) {
        Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.REQNUM,50));
        String reqNum = transacao.getPszData(true);
        Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.AUTLOCREF,50));
        String autLocRef = transacao.getPszData(true);
        Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.AUTEXTREF,50));
        String autExtRef = transacao.getPszData(true);
        Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.VIRTMERCH,50));
        String virthMerch = transacao.getPszData(true);
        Assert.assertEquals(PWRET.OK,transacao.getResult(PWINFO.AUTHSYST,50));
        String authSyst = transacao.getPszData(true);
        if (option.equalsIgnoreCase("Confirme")){
            Assert.assertEquals(PWRET.OK,transacao.ippConfirmation(PWCNF.PWCNF_CNF_AUTO,reqNum,autLocRef,autExtRef,
                    virthMerch,authSyst));
        } else if (option.equalsIgnoreCase("Desfaca")){
            Assert.assertEquals(PWRET.OK,transacao.ippConfirmation(PWCNF.PWCNF_REV_COMM_AUT,reqNum,autLocRef,autExtRef,
                    virthMerch,authSyst));
        }
        confirm = "\nDADOS DA TRANSACAO: \nREQNUM: " + reqNum +"\nAUTLOCREF: "+ autLocRef +"\nAUTEXTREF: "+ autExtRef
                +"\nVIRTMERCH: "+ virthMerch +"\nAUTHSYST: "+ authSyst + "\n";
        return true;
    }


    public String showData() {
        Assert.assertNotEquals(install + sale + confirm,"");
        return  install + sale + confirm;
    }
}
