package src.model;

public class User {
    private  String username;
    private  String password;

    //construtor vazio
    public  User(){}

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){return username;}
    public String getPass(){return password;}

    public void setUsername(String username){this.username=username;}
    public void setPass(String password) {this.password=password;}
    
}
