package moonc.example.com.useexternaldatabase.models;

public class ObjectCreated {
    int id;
    String name;
    byte[] image;

    public ObjectCreated() {
    }

    public ObjectCreated(int id, String name,byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
