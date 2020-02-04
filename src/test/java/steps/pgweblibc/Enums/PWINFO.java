package steps.pgweblibc.Enums;

import java.util.HashMap;
import java.util.Map;

public enum PWINFO {

    OPERATION(2),
    PPPPWD(03),
    SENHASIM(4),
    AUTIP(5),
    USINGAUT(6),
    AUTPORT(7),
    ADDRMODE(8),
    LOCALIP(9),
    GATEWAY(10),
    SUBNETMASK(11),
    SSID(12),
    WIFITYPE(13),
    WIFIKEY(14),
    COMMTYPE (15),
    POSID(17),
    AUTNAME(21),
    AUTVER (22),
    AUTDEV(23),
    DESTTCPIP(27),
    MERCHCNPJCPF(28),
    AUTCAP(36),
    TOTAMNT(37),
    CURRENCY(38),
    CURREXP (39),
    FISCALREF (40),
    CARDTYPE (41),
    PRODUCTNAME (42),
    APAGADADOS(45), //Criado por mim
    DATETIME (49),
    REQNUM (50),
    AUTHSYST (53),
    VIRTMERCH (54),
    AUTMERCHID (56),
    PHONEFULLNO (58),
    FINTYPE (59),
    INSTALLMENTS (60),
    INSTALLMDATE (61),
    PRODUCTID (62),
    RESULTMSG (66),
    CNFREQ (67),
    AUTLOCREF (68),
    AUTEXTREF (69),
    AUTHCODE (70),
    AUTRESPCODE (71),
    DISCOUNTAMT (73),
    CASHBACKAMT (74),
    CARDNAME (75),
    ONOFF (76),
    BOARDINGTAX(77),
    TIPAMOUNT (78),
    INSTALLM1AMT (79),
    INSTALLMAMNT (80),
    RCPTFULL (82),
    RCPTMERCH (83),
    RCPTCHOLDER (84),
    RCPTCHSHORT (855),
    TRNORIGDATE (87),//87
    TRNORIGNSU (88),
    TRNORIGAMNT (96),
    LANGUAGE (108),
    TRNORIGAUTH (98),
    TRNORIGREQNUM (114),
    TRNORIGTIME (115),
    CNCDSPMSG (116),
    CNCPPMSG (117),
    OPERABORTED (118),
    MUXTAG_BEGINTIME(123),
    MUXTAG_ENDTIME(124),
    CARDENTMODE (192),
    CARDFULLPAN (193),
    CARDEXPDATE (194),
    CVV (199),
    CARDPARCPAN (200),

    //converter para int
    CHOLDVERIF ((short)0xCF),
    SMSGCHOLDER ((short)0xE2),
    SMSGMERCH ((short)0xE3),
    SMSGTOUTSEC ((short)0xE4),
    BARCODENTMODE ((short)0xE9),
    BARCODE ((short)0xEA),
    SMSGLOCAL ((short)0xEB),
    MERCHADDDATA1 ((short)0xF0),
    MERCHADDDATA2 ((short)0xF1),
    MERCHADDDATA3 ((short)0xF2),
    MERCHADDDATA4 ((short)0xF3),
    RCPTPRN ((short)0xF4),
    AUTHMNGTUSER (245),
    AUTHTECHUSER (246), //246
    PAYMNTTYPE ((short)0x1F21),
    GRAPHICRCPHEADER((short)0x1F36),
    GRAPHICRCPFOOTER((short)0x1F37),
    CHOLDERNAME((short)0x1F38),
    MERCHNAMEPDC((short)0x1F39),
    TRANSACDESCRIPT((short)0x1F40),
    ARQC((short)0x1F41),
    DEFAULTCARDPARCPAN((short)0x1F42),
    USINGPINPAD (32513),
    PPCOMMPORT ((short)0x7F02),
    IDLEPROCTIME ((short)0x7F04),
    PNDAUTHSYST ((short)0x7F05),
    PNDVIRTMERCH ((short)0x7F06),
    PNDREQNUM ((short)0x7F07),
    PNDAUTLOCREF ((short)0x7F08),
    PNDAUTEXTREF ((short)0x7F09),
    LOCALINFO1 ((short)0x7F0A),//32522
    SERVERPND ((short)0x7F0B),
    COMMODE ((short)0x7F0C),
    COMMPROT ((short)0x7F0D),
    DIALMODE ((short)0x7F0E),
    PRINUMBER ((short)0x7F0F),
    SECNUMBER ((short)0x7F10),
    DIALPREFIX ((short)0x7F11),
    DIALWAITTIME ((short)0x7F12),
    MODSPEED ((short)0x7F13),
    TPDU ((short)0x7F14),
    STATUS((short)0x6F); // criado por mim

    private int valor;

    // Reverse-lookup map for getting a PWINFO from an abbreviation
    private static final Map<Integer, PWINFO> lookup = new HashMap<Integer, PWINFO>();

    static {
        for (PWINFO pwinfo : PWINFO.values()) {
            lookup.put(pwinfo.getValor(), pwinfo);
        }
    }

    private PWINFO(int valor){ this.valor = valor; }

    public int getValor(){ return this.valor; }

    public static PWINFO get (short value) { return lookup.get(value); }

    @Override
    public String toString(){
        return "PWINFO." + super.toString();
    }

}
