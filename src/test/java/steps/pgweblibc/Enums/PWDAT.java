package steps.pgweblibc.Enums;

import java.util.HashMap;
import java.util.Map;

public enum PWDAT {

    MENU((byte)1),
    TYPED((byte)2),
    CARDINF((byte)3),
    PPENTRY((byte)5),
    PPENCPIN((byte)6),
    CARDOFF((byte)9),
    CARDONL((byte)10),
    PPCONF((byte)11),
    BARCODE((byte)12),
    PPREMCRD((byte)13),
    PPGENCMD(),
    PPDATAPOSCNF((byte)16),
    USERAUTH((byte)17);

    private byte valor;

    // Reverse-lookup map for getting a PWDAT from an abbreviation
    private static final Map<Byte, PWDAT> lookup = new HashMap<Byte, PWDAT>();

    static {
        for (PWDAT pwdat : PWDAT.values()) {
            lookup.put(pwdat.getValor(), pwdat);
        }
    }

    private PWDAT(byte valor){
        this.valor = valor;
    }
    private PWDAT(){ valor = -1; }

    public byte getValor(){
        return this.valor;
    }

    public static PWDAT get (byte value) { return lookup.get(value); }

    @Override
    public String toString(){
        return "PWDAT." + super.toString();
    }
}
