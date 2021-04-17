import com.kernal.*;
public class Main {

    //initial instance
    static  Main running_instance = get_instance();
    public static Main get_instance(){
        return new Main();
    }

    public static void main(String[]args){
            while(true){
                running_instance.gorunning();
            }
    }

    model_table my_model_table=new model_table();
    public  void gorunning(){
        try {
            my_model_table.model_main();
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }
}
