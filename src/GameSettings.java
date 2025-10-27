public class GameSettings {    // class này lưu trữ các giá trị khi mà ta ấn save trong setting
    
    private static int volume = 50;
    private static int speed = 5;

    public static int getVolume() {
        return volume;
    }

    public static void setVolume(int v) {
        volume = v;
        SoundManager.setVolume(v);
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int s) {
        speed = s;
    }

}
