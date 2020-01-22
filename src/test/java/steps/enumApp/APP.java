package steps.enumApp;

public enum APP {
    LIBCW(1),
    LIBCL(2),
    CONTROLPAY(3),
    PAYGOCONV(4);

    private int app;
    APP(int app){ this.app = app; }
    public int getApp() { return this.app;}
}
