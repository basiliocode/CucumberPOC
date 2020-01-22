package steps;

import steps.pgweblibc.Enums.PWDAT;
import steps.pgweblibc.Enums.PWINFO;
import steps.pgweblibc.Enums.PWOPER;
import steps.pgweblibc.Enums.PWRET;
import steps.pgweblibc.Transacao;
import junit.framework.AssertionFailedError;

import java.util.Arrays;

public class TesteBase {

    Transacao transacao = new Transacao();

     PWRET execTransac() throws IllegalAccessException, InterruptedException {
        //Lançando uma excessão customizada para incluir dados faltantes na messagem;

         PWRET retorno = transacao.execTransac();
         transacao.getResult(PWINFO.RESULTMSG,100);
         String resultMsg = transacao.getPszData(true);
         PWDAT tipoDeDado = transacao.getBtipoDeDado(transacao.getInumParam() - 1);
         String menus = "";

         if (tipoDeDado == PWDAT.MENU) {
             menus = Arrays.toString(transacao.getMenu(transacao.getInumParam() - 1));
         }

         if (!PWRET.OK.equals(retorno)) {
             throw new AssertionFailedError(
                     String.format("\nExpected: %s\nActual: %s  \nRESULTMSG: (%s) \nTIPO DADO: %s %s", PWRET.OK,
                             retorno, resultMsg, tipoDeDado, menus));
         }

         return retorno;
    }

    PWRET newTransac(PWOPER pwOper){

        PWRET retorno = transacao.newTransac(pwOper);
        transacao.getResult(PWINFO.RESULTMSG,100);
        String resultMsg = transacao.getPszData(true);

        if (!PWRET.OK.equals(retorno)) {
            throw new AssertionFailedError(
                    String.format("\nExpected : %s\nActual : %s  (%s)", PWRET.OK, retorno, resultMsg));
        }
        return retorno;
    }

    PWRET addParam(PWINFO pwInfo, String value){

        PWRET retorno = transacao.addParam(pwInfo,value);
        transacao.getResult(PWINFO.RESULTMSG,100);
        String resultMsg = transacao.getPszData(true);

        if (!PWRET.OK.equals(retorno)) {
            throw new AssertionFailedError(
                    String.format("\nExpected : %s\nActual : %s  (%s)", PWRET.OK, retorno, resultMsg));
        }
        return retorno;
    }
}
