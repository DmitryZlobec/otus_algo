public class Main {


    public static void main(String[] args) {

        FSM fsm = new FSM("AABAABAAABA");
        System.out.println("fsm = " + fsm.search("AAABAABAAABA"));
    }
}