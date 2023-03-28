package src.model;

import java.util.List;
import java.util.Map;

public class Wallet implements Enums{
    private Map<PAYMENT_TYPE, List<String>> payments;
    private String IBAN;
    private float value;

    public boolean isValidPaymentNumber(PAYMENT_TYPE type, String number) {
        return
                this.payments.containsKey(type) &&
                this.payments.get(type).contains(number);
    }

    public float getValue() {
        return this.value;
    }

    public void increaseValue(float value) {
        this.value += value;
    }
}
