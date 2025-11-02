import java.io.*;
import java.util.*;

public class Leaderboard {
    private static final String FILE_PATH = "leaderboard.txt";
    private List<PlayerScore> scores;

    public Leaderboard() {
        scores = new ArrayList<>();
        load();
    }

    public List<PlayerScore> getScores() {
        return scores;
    }

    // Thêm điểm mới
    public void addScore(String name, int score) {
        scores.add(new PlayerScore(name, score));
        // Sắp xếp giảm dần
        scores.sort((a, b) -> b.score - a.score);
        // Giữ tối đa 10 điểm
        if (scores.size() > 10) {
            scores = scores.subList(0, 10);
        }
        save();
    }

    // Load từ file TXT
    private void load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    scores.add(new PlayerScore(name, score));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lưu ra file TXT
    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (PlayerScore ps : scores) {
                writer.write(ps.name + "," + ps.score);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class PlayerScore {
        public String name;
        public int score;

        public PlayerScore(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    public void clear() {
        scores.clear();
        save(); // ghi lại file trống
    }
}
