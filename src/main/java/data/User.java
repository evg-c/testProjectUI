package data;

// Builder для тестовых данных
public class User {
    private String username;
    private String email;
    private String role;
    private int age;

    private User(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;;
        this.role = builder.role;
        this.age = builder.age;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username = "default";
        private String email = "default@test.com";
        private String role = "user";
        private int age = 25;
        public Builder username(String val) {
            this.username = val;
            return this;
        }
        public Builder email(String val) {
            this.email = val;
            return this;
        }
        public Builder role(String val) {
            this.role = val;
            return this;
        }
        public Builder age(int val) {
            this.age = val;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}
