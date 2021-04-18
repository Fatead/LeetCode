package array;

public class RobotSim {


    public int robotSim(int[] commands, int[][] obstacles) {
        int posX = 0, posY = 0;
        //0表示北，1表示东，2表示南，3表示西
        int dir = 0;
        int dis = 0;
        for (int i = 0; i <commands.length ; i++) {
            if(commands[i] == -1){
                dir = (++dir)%4;
            }else if(commands[i] == -2){
                dir = (dir+3)%4;
            }else{
                if(dir == 0){
                    //向北走应该到达的位置
                    int nextPosY = posY + commands[i];
                    //判断两点之间有没有障碍物
                    for (int j = 0; j <obstacles.length ; j++) {
                        if(obstacles[j][0] == posX && obstacles[j][1]>posY && obstacles[j][1]<=nextPosY){
                            nextPosY = Math.min(obstacles[j][1]-1,nextPosY);
                        }
                    }
                    posY = nextPosY;
                }else if(dir == 1){
                    int nextPosX = posX + commands[i];
                    for (int j = 0; j <obstacles.length ; j++) {
                        if(obstacles[j][1] == posY && obstacles[j][0]>posX && obstacles[j][0]<=nextPosX){
                            nextPosX = Math.min(obstacles[j][0]-1,nextPosX);
                        }
                    }
                    posX = nextPosX;
                }else if(dir == 2){
                    int nextPosY = posY - commands[i];
                    //判断两点之间有没有障碍物
                    for (int j = 0; j <obstacles.length ; j++) {
                        if(obstacles[j][0] == posX && obstacles[j][1]>=nextPosY && obstacles[j][1]<posY){
                            nextPosY = Math.max(obstacles[j][1]+1,nextPosY);
                        }
                    }
                    posY = nextPosY;
                }else if(dir == 3){
                    int nextPosX = posX - commands[i];
                    for (int j = 0; j <obstacles.length ; j++) {
                        if(obstacles[j][0] == posY && obstacles[j][0]>=nextPosX && obstacles[j][0]<posX){
                            nextPosX = Math.max(obstacles[j][0]+1,nextPosX);
                        }
                    }
                    posX = nextPosX;
                }
                dis = Math.max(posX*posX + posY*posY,dis);
                System.out.println("posx:" + posX + " posy:" + posY);
            }
        }
        return dis;
    }


    public static void main(String[] args) {
        RobotSim robotSim = new RobotSim();
        int[] commands = {7,-2,-2,7,5};
        int[][] obs = {{-3,2},{-2,1},{0,1},{-2,4},{-1,0},{-2,-3},{0,-3},{4,4},{-3,3},{2,2}};
        System.out.println(robotSim.robotSim(commands,obs));
    }

}