public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = hash * 31 + id;

        for (int i = 0; i < name.length(); i++) {
            hash = hash * 31 + name.charAt(i);
        }

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MyTestingClass)) {
            return false;
        }

        MyTestingClass other = (MyTestingClass) obj;

        return this.id == other.id && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
