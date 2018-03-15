package First;

public class HelloWorld {
    public String getMessage(){
        return "Hello World!!";
    }

    public String getMessage(String name){
        if (name == null) {
            return getMessage();
        }
        if (name.equals("William")) {
            return "Aw kink!";
        }
        return "Hello "+name;
    }

    public String getMessage(int n){
        String adder = "";
        for (int i = 0; i<n; i++) {
            adder += getMessage();
        }
        return adder;
    }
}
