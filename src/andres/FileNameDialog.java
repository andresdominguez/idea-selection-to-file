package andres;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class FileNameDialog extends DialogWrapper {

  private JTextField fileName;

  protected FileNameDialog(@Nullable Project project) {
    super(project);
    init();
    setTitle("Enter a name for the new file");
  }

  @Nullable
  @Override
  public JComponent getPreferredFocusedComponent() {
    return fileName;
  }

  @Nullable
  @Override
  protected ValidationInfo doValidate() {
    String text = fileName.getText();
    if (text.length() == 0) {
      return new ValidationInfo("Enter a file name", fileName);
    }
    return null;
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    fileName = new JTextField();
    panel.add(fileName);
    return panel;
  }

  public String getFileName() {
    return fileName.getText();
  }
}
