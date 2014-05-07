/* ==========================================================
File:        CustomSaveListener.java
Description: Logs time from document save events.
Maintainer:  WakaTime <support@wakatime.com>
License:     BSD, see LICENSE for more details.
Website:     https://wakatime.com/
===========================================================*/

package com.wakatime.intellij.plugin;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileDocumentManagerAdapter;
import com.intellij.openapi.project.Project;

public class CustomSaveListener extends FileDocumentManagerAdapter {

    @Override
    public void beforeDocumentSaving(Document document) {
        String currentFile = FileDocumentManager.getInstance().getFile(document).getPath();
        long currentTime = System.currentTimeMillis() / 1000;
        DataContext dataContext = DataManager.getInstance().getDataContext();
        Project project = DataKeys.PROJECT.getData(dataContext);
        String currentProject = null;
        if (project != null) {
            currentProject = project.getName();
        }
        WakaTime.logFile(currentFile, currentProject, true);
        WakaTime.lastFile = currentFile;
        WakaTime.lastTime = currentTime;
    }
}
