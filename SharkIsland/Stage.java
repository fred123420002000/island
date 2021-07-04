package SharkIsland;
public enum Stage {
    START   ( 0, "初始化"),
    RUNNING ( 1, "遊戲中"),
    PASS    ( 2, "暫停"),
    OVER    ( 3, "結束"),
    ;
    private int type;
    private String name;    
    private <T> Stage(int type,String name){
        this.type=type;
        this.name=name;
    }
    
    public int getType(){
        return type;
    }        
    
    public String getName(){
        return name;
    }
    
}
