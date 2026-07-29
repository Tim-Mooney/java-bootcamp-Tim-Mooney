//prompt - in examples/loose-examples
//create a java DTO class named UserDto with the following:
//•
//fields: id (long), name (string), email (string), age (integer), active (boolean)
//-private fields
//-no-args and all-args constructors
//-getters and setters
//-toString() method

public class UserDto {
    private long id;
    private String name;
    private String email;
    private Integer age;
    private boolean active;

    public UserDto() {
    }

    public UserDto(long id, String name, String email, Integer age, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", active=" + active +
                '}';
    }
}
