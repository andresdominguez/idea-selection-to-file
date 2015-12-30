package andres;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;

public class SelectionToFileAction extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    Editor editor = e.getData(PlatformDataKeys.EDITOR);
    Project project = e.getProject();

    if (editor == null || project == null) {
      return;
    }

    String selectedText = editor.getSelectionModel().getSelectedText();
    if (selectedText == null) {
      return;
    }

    PsiFile file = e.getData(LangDataKeys.PSI_FILE);
    if (file == null) {
      return;
    }

    FileNameDialog dialog = new FileNameDialog(project);
    dialog.show();
    if (!dialog.isOK()) {
      return;
    }

    String fileName = dialog.getFileName();
    FileType fileType = file.getFileType();

    ApplicationManager.getApplication().runWriteAction(new Runnable() {
      @Override
      public void run() {
        PsiFile newFile = PsiFileFactory.getInstance(project)
            .createFileFromText(fileName, fileType, selectedText);
        file.getContainingDirectory().add(newFile);
      }
    });
  }
}
