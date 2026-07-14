package data;

public class Currency {
    private int numberCode;
    private String stringCode;
    private int count;
    private String currency;
    private double rate;

    public static class Builder {
        private int numberCode;
        private String stringCode;
        private int count;
        private String currency;
        private double rate;
        public Builder numberCode(int value) {
            this.numberCode = value;
            return this;
        }
        public Builder stringCode(String value) {
            this.stringCode = value;
            return this;
        }
        public Builder count(int value) {
            this.count = value;
            return this;
        }
        public Builder currency(String value) {
            this.currency = value;
            return this;
        }
        public Builder rate(double value) {
            this.rate = value;
            return this;
        }

        public Currency build() {
            return new Currency(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private Currency(Builder builder) {
        this.numberCode = builder().numberCode;
        this.stringCode = builder().stringCode;
        this.count = builder().count;
        this.currency = builder().currency;
        this.rate = builder().rate;

    }
}
