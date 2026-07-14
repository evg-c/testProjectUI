package data;

public class UserTable {
    private String name;
    private String email;
    private String role;

    public static class Builder {
        private String name = "default";
        private String email = "default@test.com";
        private String role = "user";

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder email(String value) {
            this.email = value;
            return this;
        }

        public Builder role(String value) {
            this.role = value;
            return this;
        }

        public UserTable build() {
            return new UserTable(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private UserTable(Builder builder) {
        this.name = builder().name;
        this.email = builder().email;
        this.role = builder().role;
    }



}
