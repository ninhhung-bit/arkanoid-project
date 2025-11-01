import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class OptionsMenu extends JDialog {
    private JSlider volumeSlider;
    private JSlider speedSlider;
    private JButton saveButton;
    private JButton backButton;

    public OptionsMenu(JFrame parent ) {
        super(parent, "Options", true); 
        setLayout(new GridBagLayout());
        setSize(400, 300);
        setLocationRelativeTo(parent);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // xử ký volume
        JLabel volumeLabel = new JLabel("Volume:");
        volumeSlider = new JSlider(0, 200, 100);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        // phần speed
        JLabel speedLabel = new JLabel("Speed:");
        speedSlider = new JSlider(1, 10, 5);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        // buttons
        saveButton = new JButton("Save");
        backButton = new JButton("Back");

        gbc.gridx = 0;        // luôn đặt ở cột 0
        gbc.gridy = 0;        
        add(volumeLabel, gbc);      // hàng 0 là nhãn volume
        gbc.gridy++;                // hàng 1 là thanh trượt volume
        add(volumeSlider, gbc);     
        gbc.gridy++;                // hàng 2 là nhãn speed
        add(speedLabel, gbc);
        gbc.gridy++;                // hàng 3 là thanh trượt speed
        add(speedSlider, gbc);

        JPanel buttonPanel = new JPanel();  // nhóm nút save và back
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        gbc.gridy++;
        add(buttonPanel, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int vol = volumeSlider.getValue();
                int spd = speedSlider.getValue();
                
                // lưu vào game setting
                GameSettings.setVolume(vol);
                GameSettings.setSpeed(spd);

                JOptionPane.showMessageDialog(parent,
                        "Saved!\nVolume: " + vol + "\nSpeed: " + spd,
                        "Settings Saved",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();                   // đóng cửa sổ Options
            }
        });

         backButton.addActionListener(e -> dispose());

    }


}
