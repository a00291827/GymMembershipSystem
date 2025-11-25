package ie.tus.gym;

/**
 * Sealed superclass.
 * Demonstrates:
 * - Sealed classes
 * - Encapsulation
 * - Inheritance foundation
 */
public sealed abstract class Person permits Member, Trainer {

    private final long id;
    private String name;
    private String email;

    protected Person(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long id() {
        return id;
    }

    public String getName() {
        return name;
    }

    // encapsulation
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    // encapsulation
    public void setEmail(String email) {
        this.email = email;
    }
}
