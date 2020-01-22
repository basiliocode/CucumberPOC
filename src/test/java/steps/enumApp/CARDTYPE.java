package steps.enumApp;

public enum CARDTYPE {
    CREDIT(1),
    DEBIT(2),
    VOUCHER(3);

    private int type;
    CARDTYPE(int type) { this.type = type; }
    public int getType(){ return this.type; }
}
