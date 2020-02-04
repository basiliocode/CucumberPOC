package steps.pgweblibc;

import steps.pgweblibc.Enums.PWCNF;
import steps.pgweblibc.Enums.PWINFO;
import steps.pgweblibc.Enums.PWOPER;
import steps.pgweblibc.Enums.PWRET;
import steps.pgweblibc.Estruturas.PW_GetData;
import steps.pgweblibc.Interfaces.InterfaceComPGWebLib;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.ptr.ShortByReference;

public class LibIntegrada {

    private InterfaceComPGWebLib pgWebLib;


    public LibIntegrada() {
        String myUserPath = System.getProperty("user.dir");
        this.pgWebLib = Native.loadLibrary( myUserPath + "\\" + (Platform.isLinux() ?
                        "src/test/resourses/PGWebLib/PGWebLib.so" : "src\\test\\resourses\\PGWebLib\\PGWebLib.dll"),
                InterfaceComPGWebLib.class); //lendo a lib pela Interface
    }

    public PWRET chamarPW_iInit(String caminho) { return PWRET.get(pgWebLib.PW_iInit(caminho)); }

    public PWRET chamarPW_iNewTransac(PWOPER tipoOperacao) {
        return PWRET.get(pgWebLib.PW_iNewTransac(tipoOperacao.getValor()));
    }

    public PWRET chamarPW_iAddParam(PWINFO wParam, String pszValue) {
        return PWRET.get(pgWebLib.PW_iAddParam(wParam.getValor(), pszValue));
    }

    public PWRET chamarPW_iExecTransac(PW_GetData[] vstParam, ShortByReference iNumParam) {
        return PWRET.get(pgWebLib.PW_iExecTransac(vstParam, iNumParam));
    }

    public PWRET chamarPW_iPPDisplay (String pszMsg) { return PWRET.get(pgWebLib.PW_iPPDisplay(pszMsg)); }

    public PWRET chamarPW_iPP_EventLoop(byte[] szDspMsg, int ulDisplaySize) {
        return PWRET.get(pgWebLib.PW_iPPEventLoop(szDspMsg, ulDisplaySize));
    }

    public PWRET chamarPW_iPP_RemoveCard() { return PWRET.get(pgWebLib.PW_iPPRemoveCard()); }

    public PWRET chamarPW_iPPAbort () { return PWRET.get(pgWebLib.PW_iPPAbort ()); }

    public PWRET chamarPW_iGetResult(PWINFO iInfo, byte[] pszData, int ulDataSize) {
        return PWRET.get(pgWebLib.PW_iGetResult(iInfo.getValor(), pszData, ulDataSize));
    }

    public PWRET chamarPW_iPPGetCard(short uiIndex){ return PWRET.get(pgWebLib.PW_iPPGetCard(uiIndex)); }

    public PWRET chamarPW_iPPGoOnChip(short uiIndex){ return PWRET.get(pgWebLib.PW_iPPGoOnChip(uiIndex)); }

    public PWRET chamarPW_iPPFinishChip(short uiIndex){ return PWRET.get(pgWebLib.PW_iPPFinishChip(uiIndex)); }

    public PWRET chamarPW_iConfirmation(PWCNF ulStatus, String pszReqNum, String pszLocRef,
                                        String pszExtRef, String pszVirtMerch, String pszAuthSyst){
        return PWRET.get(pgWebLib.PW_iConfirmation(ulStatus.getValor(), pszReqNum, pszLocRef, pszExtRef, pszVirtMerch,
                pszAuthSyst));
    }

    public PWRET chamarPW_iIdleProc (){return PWRET.get(pgWebLib.PW_iIdleProc()); }

    public PWRET chamarPW_iPPDataConfirmation (short uiIndex){
        return PWRET.get(pgWebLib.PW_iPPDataConfirmation(uiIndex));
    }

    public PWRET addMandatoryParams(){
        PWRET pwret = PWRET.get(pgWebLib.PW_iAddParam(PWINFO.AUTNAME.getValor(),"AUTOMATION TEST"));
        if (pwret != PWRET.OK) return pwret;
        pwret = PWRET.get(pgWebLib.PW_iAddParam(PWINFO.AUTVER.getValor(),"1.0.0"));
        if (pwret != PWRET.OK) return pwret;
        pwret = PWRET.get(pgWebLib.PW_iAddParam(PWINFO.AUTDEV.getValor(),"PayGo QA"));
        if (pwret != PWRET.OK) return pwret;

        return PWRET.get(pgWebLib.PW_iAddParam(PWINFO.AUTCAP.getValor(),"15"));
    }

}