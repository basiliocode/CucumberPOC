package steps.enumApp;

public enum FINTYPE {
    IN_CASH(1),
    ADMIN_INSTALLMENT(2),
    MERCHANT_INSTALLMENT(3);

    private int type;
    FINTYPE(int type) { this.type = type; }
    public int getType(){ return this.type; }
}
