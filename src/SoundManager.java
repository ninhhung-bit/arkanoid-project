import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;



public class SoundManager {
    private static FloatControl volumeControl;
    private static Clip backgroundClip;

     private static AudioInputStream loadAudio(String path) throws Exception {
        // Tải file âm thanh bằng ClassLoader (an toàn, không bị lỗi đường dẫn)
        URL soundURL = SoundManager.class.getResource("/sounds/" + path);
        if (soundURL == null) {
            throw new IOException("Không tìm thấy file âm thanh: " + path);
        }
        return AudioSystem.getAudioInputStream(soundURL);
    }

    /**
     * phat nhac nen.
     */
    public static void playBackground(String path) {
        stopBackground(); 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            volumeControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(GameSettings.getVolume());          // lay volume hien tai
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * tat nhac nen.
     */
    public static void stopBackground() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
        }
    }

    /**
     * phat am thanh hieu ung
     */ 

     public static void playSound(String path) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                SoundManager.class.getResource("/sounds/" + path)
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setClipVolume(control, GameSettings.getVolume());
            clip.start();

        } catch (Exception e) {
             e.printStackTrace();
        }
     }

     /**
      * cap nhat am luong.
      */
    public static void setVolume( int percent) {
        if(volumeControl != null) {
            setClipVolume(volumeControl, percent);
        }
    } 

    private static void setClipVolume(FloatControl control, int percent) {
        float min = control.getMinimum();
        float max = control.getMaximum();
        float gain = (max - min) * (percent / 100f) + min;
        control.setValue(gain);
    }

}
